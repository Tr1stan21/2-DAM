#Ej 12
numl = int (input ("Primer número: "))
num2 = int(input ("Segundo número: "))
if numl > num2:
    print (num2, numl)
else:
    print (numl, num2)

#Ej 13
num = int(input("Inseerta un número mayor que 20"))
if num >= 20:
    print("Too high")
else: 
    print("Thank you")

#Ej 14
num = int (input ("Escribe un número entre 10 y 20: "))
if num >= 10 and num <= 20:
    print("Thank you")
else:
    print ("Incorrect answer")

#Ej 15
color = input ("Escribe tu color favorito")
if color == "red" or color == "RED" or color == "Red":
    print("I like red too.")
else:
    print("I don't like", color, "I prefer red")

#Ej16
raining = input("Is it raining? ")
raining = str.lower(raining)
if raining == "yes":
    windy = input("Is it windy? ")
    windy = str.lower (windy)
    if windy == "yes":
        print("It is too windy for an umberella")
    else:
        print ("Take an umberella")
else:
    print ("Enjoy your day")

#Ej17
edad = int (input ("Tu edad: "))
if edad >= 18:
    print ("You can vote")
elif edad == 17:
    print ("You can learn to drive")
elif edad == 16:
    print ("You can buy a lottery ticket")
else:
    print ("You can go Trick-or-Treating")

#Ej18
num = int (input("Inserta un número: "))
if num < 10:
    print("Too low")
elif num >= 10 and num <= 20:
    print("Correct")
else:
    print ("Too high")

#Ej19
num = input("Ingresa 1, 2 o 3: ")
if num == "1":
    print("Thank you")
elif num == "2":
    print("Well done")
elif num == "3":
    print("Correct")
else:
    print("Error message")