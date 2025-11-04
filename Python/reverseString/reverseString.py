import os

string = input("\nIngresa una palabra para saber su inverso y si es palindromo, o exit para salir: ")
file = open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"log.txt"), "w")
file.write("OriginalWord;ReversedWord;IsPalindrome\n")
file.close()

while string.lower() != "exit":
    file = open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"log.txt"), "a")
    reversed = string[::-1]
    file.write(string + ";" + reversed + ";" + ("Yes" if string == reversed else "No") + "\n")
    file.close()

    print(reversed)
    print("La palabra es un palíndromo." if string == reversed else "La palabra no es un palíndromo.")

    string = input("\nIngresa una palabra para saber su inverso y si es palindromo, o exit para salir: ")

print("¡Hasta luego!")