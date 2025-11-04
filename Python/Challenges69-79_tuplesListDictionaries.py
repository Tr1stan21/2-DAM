#Ej69-70
countryNameTuple = ("Spain", "France", "Germany", "Italy", "Portugal")
print(countryNameTuple)
pais = input("Enter a country name : ")
if pais in countryNameTuple:
    print(pais, "is in the position", countryNameTuple.index(pais), "in the tuple")
position = int(input("Enter a position (0-4): "))
if position in range(0,5):
    print("The country in position", position, "is", countryNameTuple[position])

#Ej71
sports = ["Football", "Spain"]
sports.append(input("Add a sport: "))
print(sorted (sports))

#Ej72
subjects = ["Math", "English", "Science", "History", "Art", "Physical Education"]
print(subjects)
subject = input("Which of these subjects you don't like: ")
if subject in subjects:
    subjects.remove(subject)

#Ej73
food1 = input("Enter one of your fovourite foods: ")
food2 = input("Enter another of your fovourite foods: ")
food3 = input("Enter another of your fovourite foods: ")
food4 = input("Enter another of your fovourite foods: ")

foods = {1: food1, 2: food2, 3: food3, 4: food4}

removeFood = int(input("Enter the number of the food you want to remove (1-4): ")) 
if removeFood in foods:
    del foods[removeFood]
    print (sorted(foods))

#Ej74
colours = ["red", "blue", "green", "black","white","pink","grey", "purple", "yellow", "brown"]
start = int (input ("Enter a starting number (0-4): "))
end = int (input("Enter an end number (5-9): "))
print (colours[start:end])

#Ej75
nums = [123,345,234,765]
for i in nums:
    print (i)
    selection = int (input ("Enter a number from the list: "))
    if selection in nums:
        print (selection, "is in position",nums.index (selection))
    else:
        print("That is not in the list")
#Ej76&77
name1 = input ("Enter a name of somebody you want to invite to your party: ")
name2 = input("Enter another name: ")
name3 = input("Enter a third name: ")
party = [name1, name2,name3]
another = input ("Do you want to invite another (y/n): ")
while another == "y":
    newname = party.append(input ("Enter another name: "))
    another = input ("Do you want to invite another (y/n):")
print("You have", len (party), "people coming to your party")
print (party)
selection = input ("Enter one of the names: ")
print (selection, "is in position", party.index (selection), "on the list")
stillcome = input ("Do you still want them to come (y/n): ")
if stillcome == "n":
    party.remove (selection)
print (party)

#Ej78
tv = ["Task Master", "Top Gear", "The Big Bang Theory", "How I Met Your Mother"]
for i in tv:
    print (i)
print ()
newtv = input("Enter another TV show: ")
position = int (input ("Enter a number between 0 and 3: "))
tv.insert (position, newtv)
for i in tv:
    print (1)

#Ej79
nums = []
num1 = int (input ("Enter a number: "))
num2 = int (input ("Enter another number: "))
num3 = int (input ("Enter another number: "))
nums = [num1, num2, num3]
choice = input ("Do you still want the last number you entered (y/n): ")
if choice != "y":
    nums.remove (num3)
print(nums)