class User:
    def __init__(self, name, lastName, NickName, Id, Plan):
        self.name = name
        self.lastName = lastName
        self.NickName = NickName
        self.Id = Id
        self.Plan = Plan
    def DefaultMessage(self):
        print(f"Hello World")

class Lawyer(User):    
    def __init__(self, name, lastName, NickName, Id, Plan, SigmaTool):
        self.SigmaTool = SigmaTool
        super().__init__(name, lastName, NickName, Id, Plan) 
    def LawlessActions(self):
        print(f"\nUser Info: Name == {self.name} {self.lastName} | Nick Name == {self.NickName} | ID: {self.Id} | Plan: {self.Plan} | Uses Sigma Tool: {self.SigmaTool}\n")    

User1 = Lawyer("Jeffrey", "Epstein", "DominarPro+", 1597534862486, "Gemini Ultra", True)   
User1.LawlessActions()     