nombre = input("Escribe tu nombre: ").lower().strip()
primerApellido = input("Escribe tu primer apellido: ").lower().strip()
segundoApellido = input("Escribe tu segundo apellido: ").lower().strip()
edad = int(input("Ingresa tu edad: ").strip())


print (nombre[0:3]+primerApellido+str((edad+15))[0:2].zfill(2)+"__"+segundoApellido[-3:len(segundoApellido)].title())