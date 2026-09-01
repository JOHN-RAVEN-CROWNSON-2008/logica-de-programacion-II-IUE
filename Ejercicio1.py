"""x = 20
y = 10

def suma(a,b):
    global x
    print(f"X dentro de la función es: {x}")
    x = a+b
    print(f"X dentro de la función es: {x}")
    return x

res = suma(y, x)
print(f"El resultado de la operación es: {res}")
print(f"X fuera de función es: {x}")"""

x = 10 

"""def funcion_exterior():
    x = 20
    def funcion_interior():
        nonlocal x
        x = 30
        print(f"x dentro de la función interior = {x}")
    funcion_interior()
    print(f"X dentro de la función es: {x}")
funcion_exterior()
print(f"X fuera de función_exterior: {x}")

print()"""

#Paso de tipos inmutables
# def modificar(x):
#     x = x + 10 
#     print(f"X dentro de la función: {x}")
# a = 5 
# modificar(a)
# print("Fuera de la función", a)

# #Paso de tipos mutables
# def modificar_lista(lst):
#     lst.append(100)
# mi_lista = [1, 2, 3]
# modificar_lista(mi_lista)
# print("Fuera de la función:", mi_lista)    
        
def modificar_lista(lst):
    lst = lst.copy()
    lst.append("Petro")
    print("Lista dentro de la función:", lst)
    
My_List = ["Gustavo", "Francisco"]
modificar_lista(My_List)
print("Fuera de la función:", My_List)                    