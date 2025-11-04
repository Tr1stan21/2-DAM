import csv
import os

file = open ("ventas.csv", "r")
reader = csv.reader(file)

for row in reader:
    print(row)




# Lea el CSV usando csv.DictReader.

# Calcule:
    # Total de ingresos por venta (precio * cantidad).
    # Total general de ingresos.
    # Ingreso total por categoría.
    # Producto más vendido.
    # Promedio de precio por categoría.

# Guarde un nuevo archivo resumen_ventas.csv con columnas:
# categoria,ingresos_totales,promedio_precio,producto_mas_vendido.

# Genere además un archivo ventas_actualizadas.csv con la columna adicional total_venta.