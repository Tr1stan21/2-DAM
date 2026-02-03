import numpy as np
import cv2
import scipy.ndimage as ndi
from PIL import Image

def dilate(mask: np.ndarray, r: int) -> np.ndarray:
    k = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (2*r+1, 2*r+1))
    return cv2.dilate(mask.astype(np.uint8), k, iterations=1).astype(bool)

def extract_cs_clean(path_in="logo.png", path_out="CS_extracted.png"):
    img = Image.open(path_in).convert("RGBA")
    arr = np.array(img)
    rgb = arr[:, :, :3].astype(np.uint8)

    bgr = cv2.cvtColor(rgb, cv2.COLOR_RGB2BGR)
    hsv = cv2.cvtColor(bgr, cv2.COLOR_BGR2HSV)
    H, S, V = hsv[:, :, 0], hsv[:, :, 1], hsv[:, :, 2]
    rgb_f = rgb.astype(np.float32)

    # 1) Core gold (alto-confidence) para pillar solo las letras (sin fondo)
    #    (H más alto para evitar marrones del interior de la cinta)
    gold_core = (H >= 17) & (H <= 32) & (S > 40) & (V > 110)

    lbl, _ = ndi.label(gold_core)
    sizes = np.bincount(lbl.ravel())
    comps = sorted([(i, s) for i, s in enumerate(sizes) if i != 0], key=lambda x: -x[1])
    if len(comps) < 2:
        raise RuntimeError("No se detectaron 2 componentes principales (C y S).")

    # 2) Mantén SOLO las 2 componentes grandes (C y S); el resto suele ser detalle interno de la cinta
    keep_core = (lbl == comps[0][0]) | (lbl == comps[1][0])
    small_core = gold_core & (~keep_core)  # candidatos a “cinta” en dorado

    # 3) Región de borde para conservar antialias real sin incluir glow/fondo
    edge_region = dilate(keep_core, 6)
    bg_mask = ~dilate(keep_core, 18)  # background “limpio” para muestreo

    # 4) Nearest-neighbor matting: estimación local de FG/BG por píxel (sin recolorear el dorado)
    _, inds_fg = ndi.distance_transform_edt(~keep_core, return_indices=True)
    _, inds_bg = ndi.distance_transform_edt(~bg_mask, return_indices=True)

    fg_y, fg_x = inds_fg
    bg_y, bg_x = inds_bg
    fg_ref = rgb_f[fg_y, fg_x]
    bg_ref = rgb_f[bg_y, bg_x]
    obs = rgb_f

    den = fg_ref - bg_ref
    num = obs - bg_ref
    valid = np.abs(den) > 1.0

    alpha_channels = np.zeros_like(num, dtype=np.float32)
    alpha_channels[valid] = num[valid] / (den[valid] + 1e-6)

    alpha_tmp = np.where(valid, alpha_channels, np.nan)
    alpha = np.nanmedian(alpha_tmp, axis=2)
    alpha = np.nan_to_num(alpha, nan=0.0)
    alpha = np.clip(alpha, 0.0, 1.0)

    alpha[keep_core] = 1.0
    alpha[~edge_region] = 0.0

    # corta píxeles prácticamente iguales al BG (evita “restos” del fondo)
    diff_bg = np.max(np.abs(obs - bg_ref), axis=2)
    alpha[(~keep_core) & edge_region & (diff_bg < 3.0)] = 0.0
    alpha[(~keep_core) & edge_region & (V < 35)] = 0.0
    alpha[(~keep_core) & edge_region & (S < 20)] = 0.0

    # 5) Eliminación agresiva del interior tipo cinta (rojizo/marrón) => alpha 0
    R, G, B = rgb[:, :, 0], rgb[:, :, 1], rgb[:, :, 2]
    film_fill = (H <= 18) & (S > 120) & (V > 50) & (V < 220) & (G < 140) & (R > G + 20) & (B < 120)
    alpha[film_fill] = 0.0

    # 6) Elimina “detalle dorado” que pertenezca a la cinta (componentes pequeñas del core)
    alpha[dilate(small_core, 3)] = 0.0

    # 7) Reconstruye RGB desmezclando BG local (minimiza halos/fringing)
    alpha_safe = np.clip(alpha, 1e-3, 1.0)[:, :, None]
    fg_corrected = (obs - (1 - alpha[:, :, None]) * bg_ref) / alpha_safe
    fg_corrected = np.clip(fg_corrected, 0, 255).astype(np.uint8)

    out_a = (alpha * 255).astype(np.uint8)
    out = np.dstack([fg_corrected, out_a])

    # 8) Recorte al bounding box real (solo CS)
    ys, xs = np.where(out_a > 0)
    if len(xs) == 0 or len(ys) == 0:
        raise RuntimeError("Alpha vacío (no se extrajo nada).")

    pad = 2
    x0, x1 = max(xs.min() - pad, 0), min(xs.max() + pad, out.shape[1] - 1)
    y0, y1 = max(ys.min() - pad, 0), min(ys.max() + pad, out.shape[0] - 1)
    out = out[y0:y1+1, x0:x1+1]

    Image.fromarray(out, mode="RGBA").save(path_out)

if __name__ == "__main__":
    extract_cs_clean("logo.png", "CS_extracted.png")
