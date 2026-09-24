import java.util.Scanner;

//Scanner se define como una clase a partir del cual crearemos objetos, un ejemplo de método es opcion = teclado.nextInt(); (Métodos de Scanner find?)

public class CuentaBancaria {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        //Todo lo que se ejecuta en la clase CuentaBancaria, es por medio del (Main) == Ejecución de la clase 
        Cuenta miCuenta = new Cuenta("Juan Pérez", "Ahorros", "Bancolombia", "1017234567");
        //Creamos el objeto "Mi cuenta" a partir de la clase Cuenta
        int opcion = 0;

        do {
            System.out.println("\n--- CAJERO AUTOMÁTICO ---");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Mostrar información de la cuenta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el monto a depositar: ");
                    double deposito = teclado.nextDouble();
                    miCuenta.depositar(deposito);
                    break;

                case 2:
                    System.out.print("Ingrese el monto a retirar: ");
                    double retiro = teclado.nextDouble();
                    miCuenta.retirar(retiro);
                    break;

                case 3:
                    miCuenta.mostrarInformacionCuenta();
                    break;

                case 4:
                    System.out.println("Gracias por usar el cajero. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 4);
    }

}//Puedo definier la clase cuenta tanto dentro del Clase principal como por fuera, pero por dentro debe ser declarada como Static, lo estándar es por fuera.
//Shift + Tab para organizar el código

//En Java el constructor debe llamarse igual que la Clase
class Cuenta {
    String titular;
    double saldo;
    String tipoCuenta;
    String banco;
    String id;

    public Cuenta(String titular, String tipoCuenta, String banco, String id) {//Los parámetros dentro del paretesís son obligatorios para crear el objeto | Los parámetros dentro del paréntesis son obligatorios, pero puedo definir otros no obligatorios
        this.titular = titular;
        this.saldo = 0;
        this.tipoCuenta = tipoCuenta;
        this.banco = banco;
        this.id = id;
    }
    //El double o void son los que me dicen que va a retornar el método, si vacío o que tipo de dato
    public void depositar(double valor) {//Defino los argumentos o parámetros de entrada
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito exitoso de $" + valor);
            mostrarSaldo();
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

    public void retirar(double valor) {
        if (valor <= 0) {
            System.out.println("El valor a retirar debe ser mayor a 0.");
        } else if (valor > saldo) {
            System.out.println("Fondos insuficientes. Su saldo actual es: $" + saldo);
        } else {
            saldo -= valor;
            System.out.println("Retiro exitoso de $" + valor);
            mostrarSaldo();
        }
    }

    public void mostrarSaldo() {
        System.out.println("Saldo disponible: $" + saldo);
    }

    public void mostrarInformacionCuenta() {
        System.out.println("\n--- DETALLES DE LA CUENTA ---");
        System.out.println("Banco: " + banco);
        System.out.println("Titular: " + titular);
        System.out.println("Documento/ID: " + id);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo actual: $" + saldo);
    }
}