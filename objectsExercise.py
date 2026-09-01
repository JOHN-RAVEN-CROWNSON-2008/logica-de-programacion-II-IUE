class Libro:
    def __init__(self, autor, titulo):
        self.autor = autor
        self.titulo = titulo
        self.disponible = True
        
    def prestar(self):
        print(f"Acualmente tenemos disponible el libro {self.titulo} de {self.autor} \n\n ¿Desea prestar mel libro? Escriba 1 para NO y 0 para NO")
        userChoise = int(input(""))
        if userChoise == 1:
            self.disponible = False
        else:
            self.disponible = True            
        if userChoise == 1:
            print("Prestando el Libro \nEsperamos que lo disfrute")
        else:
            print("Usted no quiere volverse RICO mr GOY \nBye Bye")
    def devolver(self):
        print("Gracias por leer el libro")  
        self.disponible = False
    def mostrar_estado(self):
        if self.disponible == True:
            print("El libro está disponile")
        else:
            print("El libro fue prestado | Vuelve Más tarde")    
           
available_book = Libro("Gabriel García Marquez", "100 Años de soledad")

available_book.prestar()
available_book.devolver()
available_book.mostrar_estado()