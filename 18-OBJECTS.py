# class Goys:
#     def __init__(self, nombre, apellido, edad, genero, cargo, patrimonioNeto, propiedades):
#         self.nombre = nombre
#         self.apellido = apellido
#         self.edad = edad
#         self.genero = genero
#         self.cargo = cargo
#         self.patrimonioNeto = patrimonioNeto
#         self.propiedades = propiedades
    
#     def saludar(self):
#         print(f"Mi nombre es {self.nombre} {self.apellido}, tengo {self.edad} años y mi género es {self.genero}, soy el {self.cargo} y tengo un patrimonio neto de {self.patrimonioNeto} y las siguientes propiedades: {', '.join(self.propiedades)}")    
        
# myPresident = Goys("Benjamin", "Netanyahu", 250, "Undefined", "Rey", 90000000000000000, ["20 millones de hécareas en Argentina", "20 mil Bombas nucleares"])      
# myPresident.saludar()

class GlobalIsraelZionBank:
    def __init__(self, nombre, apellido, id, genero, tipoDeCuenta, saldo, TipoPrestamo, ValorPrestamo, estadoCivil, OrientacionPolitica, antecedentes, lealtadEstrellaDelRenfán, EsAptoParaElPrestamo):
        self.nombre = nombre
        self.apellido = apellido
        self.id = id
        self.genero = genero
        self.tipoDeCuenta = tipoDeCuenta
        self.saldo = saldo
        self.TipoPrestamo = TipoPrestamo
        self.ValorPrestamo = ValorPrestamo
        self.estadoCivil = estadoCivil
        self.OrientacionPolitica = OrientacionPolitica
        self.antecedentes = antecedentes
        self.lealtadEstrellaDelRenfán = lealtadEstrellaDelRenfán
        self.EsAptoParaElPrestamo = EsAptoParaElPrestamo
    def informeMossad(self):
        print(f"El Goy en cuestión se llama {self.nombre} {self.apellido}, su identificación es {self.id}, su género es: {self.genero}. Tiene un tipo de cuenta {self.tipoDeCuenta} con un saldo de {self.saldo}. \nTiene un {self.TipoPrestamo} por un valor de: {self.ValorPrestamo}, el fulano tiene un estado civil de {self.estadoCivil}, tiene una orientación política de {self.OrientacionPolitica} y tiene los siguientes antecedentes: {', '.join(self.antecedentes)}.\n \n ¿Es leal a la Estrella del Renfán? {self.lealtadEstrellaDelRenfán}.\n \n ¿Es apto para el préstamo? {self.EsAptoParaElPrestamo} \n")    

formerMinister = GlobalIsraelZionBank("Benjamin", "Netanyahu", "666666666666", "Undefined", "Global", 90000000000000000000000000000000000000000000000000, "Prestamo para desarrollo de armas nucleares", 900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, "Poligámia", "Zionist", ["Background in Politics", "Business Experience"], True, True)
formerMinister.informeMossad()

#El valor por defecto va dentro del constructor         
#50A5DC589719

#NATURAL DISASTER OBJECT 

class Desastre_Natural:
    def __init__(self,lugar, naturaleza, peligrosidad, duracion, cuadras_afectadas):
        self.lugar = lugar
        self.naturaleza = naturaleza
        self.peligosidad = peligrosidad
        self.duracion = duracion
        self.cuadras_afectadas = cuadras_afectadas
        
    def destruir(self):
        print(f"Estoy destrullendo todo a mi paso a través de {self.naturaleza}\n\n")    
    def iniciar(self):
        print(f"El desastre está ubicado en {self.lugar}\n\n")
    def terminar(self):
        if(self.duracion) > 1:
            print(f"La catástrofe tuvo una duración de: {self.duracion} Horas\n\n")
        else:
            print(f"La catástrofe tuvo una duración de: {self.duracion} Hora\n\n")    
    def news(self):
        print(f"En las noticias dicen que el desastre fué {self.peligosidad}\n\n")  
    def reporte(self):
        input("Ingresa la dirección de tu cuadra: ")
        input("Describe la afectación de tu cuadra: ")
   
input_naturaleza = input("Escribas la naturaleza del desastre: ")     
input_lugar = input("Escriba el lugar del desastre: ")
input_duracion = int(input("Escriba la duración del desastre en minutos: "))
input_peligrosidad = input("Escriba que tan peligroso fué: ") 
all_input = Desastre_Natural(input_naturaleza, input_lugar, input_duracion, input_peligrosidad) 

all_input.destruir()
all_input.iniciar()
all_input.terminar()
all_input.news()

# sanJose2026 = Desastre_Natural("San José Del Palmar", "Terremoto", "MUY DEVASTADOR", 2)     
# sanJose2026.destruir()
# sanJose2026.iniciar()
# sanJose2026.terminar()
# sanJose2026.news() 

class libro:
    def __init__(self, autor, titulo, disponible):
        self.autor = autor
        self.titulo = titulo
        self.disponible = disponible
        
    def prestar(self):
        print("Acualmente tenemos disponible el libro (¿COMO VOLVERSE MILLONARIO? | LA GUÍA DE UN JUDÍO ASQUENAZI) \n\n ¿Desea prestar mel libro? Escriba 1 para NO y 0 para NO")
        userChoise = int(input(""))
        if userChoise == 1:
            bookState = False
        else:
            bookState = True            
        if userChoise == 1:
            print("Prestando el Libro \n Esperamos que se vuelva millonario pronto. Recuerda que debes aportar a la conservación de la TIERRA PROMETIDA")
        else:
            print("Usted no quiere volverse RICO mr GOY \n Bye Bye")
    def devolver(self):
        print("Gracias por leer el libro ahora haces partes de la familia de la ESTRELLA DEL RENFÁN")  
        bookState = False
    def mostrar_estado(self):
        if bookState == True:
            print("El libro está disponile")
        else:
            print("El libro fue prestado | Vuelve Más tarde")    
           
bookState = True
all_info = libro() 