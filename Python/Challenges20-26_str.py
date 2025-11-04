# #Ej20
# name = input("Enter your first name: ")
# length = len(name)
# print (length)

# #Ej21
# firstname = input("Enter your first name:")
# surname = input ("Enter your surname: ")
# name = firstname+" " + surname
# print (name)
# print (len(name))

# #Ej22
# firstname = input ("Enter your first name in lowercase: ")
# surname = input("Enter your surname in lowercase: " )
# name = firstname.title() + " "+ surname.title()
# print (name)

# #Ej23
# phrase = input("Enter the first line of a nursery rhyme: ")
# length = len (phrase)
# print("This has", length, "letters in it")
# start = int(input("Enter a starting number: "))
# end = int(input ("Enter an end number: "))
# part = (phrase[start:end])
# print (part)

# #Ej24
# word = input("Enter a word: ")
# print (word.upper())

# #Ej25
# name = input("Enter your first name: ")
# if len (name)< 5:
#     surname = input ("Enter your surname: ")
#     name = name+surname
#     print (name.upper())
# else:
#     print (name.lower())


#Ej26
word = input("Please enter a word: ").lower()
if word[0] == 'a' or word[0] == 'e' or word[0] == 'i' or word[0] == 'o' or word[0] == 'u':
    print(word+"way")
else:
    print(word[1:len(word)]+word[0]+"ay") 