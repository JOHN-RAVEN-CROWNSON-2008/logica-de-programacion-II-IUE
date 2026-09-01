# class Petroñeros:
#     def __init__(self, estracto_Social, nivel_Academico, habitosDe_Consumo, probabilidadDe_salirDeLaPobreza, GustosGastronomicos):
#         self.estracto_Social = estracto_Social
#         self.nivel_Academico = nivel_Academico
#         self.habitosDe_Consumo = habitosDe_Consumo
#         self.probabilidadDe_salirDeLaPobreza = probabilidadDe_salirDeLaPobreza
#         self.GustosGastronomicos = GustosGastronomicos
        
class Motoneta:
    def __init__(self, Numero_placa, marca, modelo, cilindraje, color, velocidadActual):
        self.Numero_placa = Numero_placa
        self.marca = marca
        self.modelo = modelo
        self.cilindraje = cilindraje
        self.color = color
        self.velocidadActual = velocidadActual
    def acelerar(self):
        self.velocidadActual = self.velocidadActual + 10
        print(f"La velocidad ha aumentado en 10 Kms/h\n")
    def frenar(self):
        self.velocidadActual = self.velocidadActual - 9
        print(f"La velocidad ha disminuido en 9 Kms/h\n")
    def mostrar_informacion(self):
        print(f"La moto de placa: {self.Numero_placa} | Modelo: {self.modelo} | Cilidraje: {self.cilindraje} | Color: {self.color} | Marca {self.marca} \n Tiene una velocidad actual de: {self.velocidadActual}")   

Moto1 = Motoneta("LLM26H", "Yamaha", 2027, 550, "Verde Nacional", 1)
Moto1.acelerar()
Moto1.frenar()
Moto1.mostrar_informacion()