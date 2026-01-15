import csv
from collections import Counter

numeros = Counter()
estrellas = Counter()

with open("datos_euromillon.csv", encoding="utf-8") as f:
    lector = csv.reader(f)
    next(lector)  # saltar cabecera

    for fila in lector:
        # Números (columnas 1 a 5)
        for n in fila[1:6]:
            if n != "":
                numeros[int(n)] += 1

        # Estrellas (columnas 7 y 8)
        for e in fila[7:9]:
            if e != "":
                estrellas[int(e)] += 1

print(" NÚMEROS MÁS FRECUENTES:")
for num, veces in numeros.most_common(10):
    print(num, "→", veces)

print("\n ESTRELLAS MÁS FRECUENTES:")
for est, veces in estrellas.most_common():
    print(est, "→", veces)