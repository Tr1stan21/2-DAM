#Math
import math

#Ej27
numero = float(input("Introduce un número con muchos decimales: "))
print(numero * 2)


#Ej28
numero = float(input("Introduce un número con muchos decimales: "))
respuesta = numero * 2
print(respuesta)
print(round(respuesta, 2))


#Ej29
numero = int(input("Introduce un número mayor que 500: "))
respuesta = math.sqrt(numero)
print(round(respuesta, 2))


#Ej30
print(round(math.pi, 5))


#Ej31
radio = int(input("Introduce el radio del círculo: "))
area = math.pi * (radio ** 2)
print(area)


#Ej32
radio = int(input("Introduce el radio del círculo: "))
profundidad = int(input("Introduce la profundidad: "))
area = math.pi * (radio ** 2)
volumen = area * profundidad
print(round(volumen, 3))


#Ej33
numero1 = int(input("Introduce un número: "))
numero2 = int(input("Introduce otro número: "))
resultado1 = numero1 // numero2
resultado2 = numero1 % numero2
print(numero1, "dividido entre", numero2, "es", resultado1, "y sobra", resultado2)


#Ej34
print("1) Cuadrado")
print("2) Triángulo")
print()
seleccion_menu = int(input("Introduce un número: "))
if seleccion_menu == 1:
    lado = int(input("Introduce la longitud de un lado: "))
    area = lado * lado
    print("El área de la figura elegida es", area)
elif seleccion_menu == 2:
    base = int(input("Introduce la longitud de la base: "))
    altura = int(input("Introduce la altura del triángulo: "))
    area = (base * altura) / 2
    print("El área de la figura elegida es", area)
else:
    print("Opción incorrecta seleccionada")



# For
#Ej35
name = input("Type in your name: ")
for i in range (0,3):
    print (name)
#Ej36

name = input("Type in your name: ")
number = int(input ("Enter a number: "))
for i in range (0, number):
    print (name)
    
#Ej37
name = input("Enter your name: ")
for i in name:
    print(i)

#Ej38
num = int(input ("Enter a number: "))
name = input("Enter your name: ")
for x in range (0, num) :
    for i in name:
        print (i)
#Ej39
num = int(input ("Enter a number between 1 and 12: "))
for i in range (1, 13):
    answer = i * num
    print (i, "x", num, "=", answer)

#Ej40
num = int (input ("Enter a number below 50: "))
for i in range (50, num-1, -1):
    print (i)

#Ej41
name = input("Enter your name: ")
num = int (input("Enter a number: "))
if num < 10:
    for i in range (0, num) :
        print(name)
else:
    for i in range (0,3):
        print("Too high")

#Ej42
total =0
for i in range (0,5):
    num = int(input ("Enter a number: "))
    ans = input ("Do you want this number included? (y/n) ")
if ans == "y":
    total = total + num
    print (total)

#Ej43
direction = input ("Do you want to count up or down? (u/d) ")
if direction == "u":
    num = int (input("What is the top number? "))
    for i in range (1, num+1):
        print(i)
elif direction == "d":
    num = int (input ("Enter a number below 20: "))
    for i in range (20, num-1, -1):
        print (i)
else:
    print("I don't understand")

#Ej44
num = int (input ("How many friends do you want to invite to the party? "))
if num < 10:
    for i in range (0,num):
        name = input("Enter a name: ")
        print (name, "has been invited")
else:
    print("Too many people")