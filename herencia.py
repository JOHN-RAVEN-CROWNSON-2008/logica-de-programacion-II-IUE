class Animal:
    def __init__(self, nombre, especie, edad):
        self.nombre = nombre
        self.especie = especie
        self.edad = edad
    def moverse(self):
        print("Me estoy moviendo a mi ritmo")
        
class Dog(Animal):    
    def __init__(self, nombre, especie, edad, peso):
        self.peso = peso
        super().__init__(nombre, especie, edad)
    def Bark(self):
        print(f"\n<----------------------------------> First Children Class Print <---------------------------------->\n\n¡Guau, Guau my friend! \n\nHi my name is {self.nombre} and I'm an {self.especie}, I'm {self.edad} old and I'm currently about {self.peso} Kilograms!\n")    

class Cat(Animal):
    def __init__(self, nombre, alias, especie, edad, wisdom):
        self.alias = alias
        self.wisdom = wisdom
        super().__init__(nombre, especie, edad)
    def cheatingPeople(self):
        print(f"<----------------------------------> Second Children Class Print <---------------------------------->\n\nHi my name is {self.nombre} and I'm a {self.especie} also called as {self.alias}, I'm {self.edad} and I've this great sort of wisdom {self.wisdom}to accomplish my objetives.\n\n")

class Bird(Animal):
    def __init__(self, nombre, especie, edad, Rank, Pride, Gender):
        self.Rank = Rank
        self.Pride = Pride
        self.Gender = Gender
        super().__init__(nombre, especie, edad)
    def flyingAbove(self):
        print(f"<----------------------------------> Third Children Class Print <---------------------------------->\n\nHi my name is {self.nombre} and I'm an {self.especie} I'm {self.edad} old, I'm {self.Rank} of power which means a lot, and I've this great sort pride {self.Pride} to accomplish my objetives.\n\nMy gender is: {self.Gender}\n")  
            
FormerKingOfZion = Dog("Benjamin", "Adenocromous Reptilián", 430, 80)#1 Children object with inheritance 
FormerKingOfZion.Bark()#1 Children object with inheritance call to
FormerMinisterOfZion = Cat("Sean Combs", "Diddy", "Partyous Polemicus", 50, "Able to get a great influence ")#2 Children object with inheritance 
FormerMinisterOfZion.cheatingPeople()#2 Children object with inheritance call to
FormeMrPresidentOfPresidentsOfZion = Bird("Jefrey", "Smarticous Powerticous", "Dead", 5000000000000000000000000, "Being somebody whose power has been able to control entire goverments", 1010101010101)#3 Children object with inheritance 
FormeMrPresidentOfPresidentsOfZion.flyingAbove()#3 Children object with inheritance call to              