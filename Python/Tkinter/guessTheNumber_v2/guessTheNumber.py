import random
import os


while True:
    radomNum = random.randint(0,100)

    try:
        num = int(input("\nIngresa un número entre 0 y 100 para jugar, o algo no numérico para salir: "))
    except ValueError:
        print("¡Hasta luego!")
        break

    if num < 0 or num > 100:
        print("¡Hasta luego!")
        break

    file = open(os.path.join(os.path.dirname(os.path.abspath(__file__)), "log.txt"), "w")
    file.write("NúmeroRonda;IntentoNúmero\n")
    round = 1
    file.write(str(round)+";"+ str(num)+"\n")

    while 0 <= num <= 100:
        if num < radomNum:
            print("El número es mayor")
        elif num > radomNum:
            print("El número es menor")
        else:
            print("Felicidades, has adivinado el número")
            break

        num = int(input("Ingrese un número del 0 al 100: "))
        round += 1
        file.write(str(round)+";"+ str(num)+"\n")

    file.close()

    file = open(os.path.join(os.path.dirname(os.path.abspath(__file__)), "log.txt"), "r")
    print("\nRegistro de juego:")
    print(file.read())
