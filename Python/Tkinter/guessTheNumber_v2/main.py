# This code is generated using PyUIbuilder: https://pyuibuilder.com

import os
import tkinter as tk
from tkinter import ttk


BASE_DIR = os.path.dirname(os.path.abspath(__file__))
FONT = "Segoe UI"
BG_MAIN = "#F3EFE7"
BG_CARD = "#F8F5EF"
FG_TEXT = "#222222"
FG_ACCENT = "#8B2525"
BTN_BG = "#5E6B71"
BTN_BG_ACTIVE = "#4C585E"
BTN_FG = "#FFFFFF"

main = tk.Tk()
main.title("Adivina el número")
main.config(bg=BG_MAIN)
main.geometry("672x420")
main.resizable(False, False)

# Centrar contenido en la columna 0
main.columnconfigure(0, weight=1)

style = ttk.Style(main)
style.theme_use("clam")

# Título
style.configure(
    "title.TLabel",
    background=BG_MAIN,
    foreground=FG_TEXT,
    font=(FONT, 28, "bold"),
)
title = ttk.Label(main, text="¡Adivina el número!", style="title.TLabel", anchor="center")
title.grid(row=0, column=0, pady=(24, 8), sticky="n")

# Descripción
style.configure(
    "description.TLabel",
    background=BG_MAIN,
    foreground=FG_TEXT,
    font=(FONT, 14),
)
description = ttk.Label(
    main,
    text="Pienso en un valor entre 1 y 100.",
    style="description.TLabel",
    anchor="center",
)
description.grid(row=1, column=0, pady=(0, 14), sticky="n")

# Intentos restantes
style.configure(
    "tries_left.TLabel",
    background=BG_MAIN,
    foreground=FG_TEXT,
    font=(FONT, 14, "bold"),
)
tries_left = ttk.Label(
    main,
    text="Intentos restantes: 10",
    style="tries_left.TLabel",
    anchor="center",
)
tries_left.grid(row=2, column=0, pady=(0, 18), sticky="n")

# Frame de entrada + botón
frame = tk.Frame(main, bg=BG_CARD)
frame.grid(row=3, column=0, pady=(0, 20))
frame.columnconfigure(0, weight=1)
frame.columnconfigure(1, weight=0)

# Entrada
style.configure(
    "entry.TEntry",
    fieldbackground="#FFFFFF",
    foreground=FG_TEXT,
    font=(FONT, 16),
)
entry = ttk.Entry(frame, style="entry.TEntry", justify="center")
entry.grid(row=0, column=0, padx=(20, 10), pady=16, ipady=4, sticky="we")

# Botón
style.configure(
    "button.TButton",
    background=BTN_BG,
    foreground=BTN_FG,
    font=(FONT, 14, "bold"),
    borderwidth=0,
    focusthickness=3,
    focuscolor=BTN_BG,
)
style.map(
    "button.TButton",
    background=[("active", BTN_BG_ACTIVE)],
    foreground=[("active", BTN_FG)],
)
button = ttk.Button(frame, text="Jugar", style="button.TButton")
button.grid(row=0, column=1, padx=(10, 20), pady=16, ipady=2)

# Info (mayor/menor/correcto)
style.configure(
    "info.TLabel",
    background=BG_MAIN,
    foreground=FG_ACCENT,
    font=(("%s" % FONT), 16, "bold"),
)
info = ttk.Label(
    main,
    text="Prueba un número más alto",
    style="info.TLabel",
    anchor="center",
)
info.grid(row=4, column=0, pady=(0, 24), sticky="n")

main.mainloop()