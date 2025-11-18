from tkinter import *
import tkinter.messagebox as box

# Crear la ventana principal de la aplicación
window = Tk()
# Establecer el título de la ventana
window.title( 'Ejemplo de seleccion múltiple' )
# Crear un marco (contenedor) para organizar los widgets
frame = Frame( window )
# Crear variables de tipo entero para almacenar el estado de cada checkbox
var_1 = IntVar()
var_2 = IntVar()
var_3 = IntVar()
var_4 = IntVar()

# Crear tres checkboxes
# variable: vincula el estado del checkbox a una variable
# onvalue/offvalue: valores que se asignan cuando está marcado/desmarcado
book_1 = Checkbutton( frame , text = 'Seguridad y alta disponibilidad' , 
variable = var_1 , onvalue = 1 , offvalue = 0 )
book_2 = Checkbutton( frame , text = 'Servicios de Red e Internet' , 
variable = var_2 , onvalue = 1 , offvalue = 0 )
book_3 = Checkbutton( frame , text = 'Programacion en Python' , 
variable = var_3 , onvalue = 1 , offvalue = 0 )
book_4 = Checkbutton( frame , text = 'Programacion en Java' , 
variable = var_4 , onvalue = 1 , offvalue = 0 )

# Función que se ejecuta cuando el usuario hace clic en el botón
def dialog() :
    str = 'Tu eleccion:'
    # Verificar cada variable y agregar el texto correspondiente si está marcada
    if var_1.get() == 1 : str += '\nSeguridad y alta disponibilidad'
    if var_2.get() == 1 : str += '\nServicios de Red e Internet'
    if var_3.get() == 1 : str += '\nProgramacion en Python'
    if var_4.get() == 1 : str += '\nProgramacion en Java'
    # Mostrar un cuadro de diálogo informativo con las selecciones
    box.showinfo( 'Selection' , str )

# Crear un botón que ejecuta la función dialog cuando se hace clic
btn = Button( frame , text = 'Elige' , command = dialog )
# pack() organiza el botón dentro del frame (lado derecho, con espacio)
btn.pack( side = RIGHT , padx = 5 )
# Organizar los checkboxes en el frame (lado izquierdo)
book_1.pack( side = LEFT )
book_2.pack( side = LEFT )
book_3.pack( side = LEFT )
# Organizar el frame dentro de la ventana principal (con espacios alrededor)
frame.pack( padx = 30, pady = 30 )
# Iniciar el bucle principal de la aplicación
window.mainloop()
