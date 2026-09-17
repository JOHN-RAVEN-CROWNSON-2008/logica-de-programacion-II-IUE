import java.util.Scanner;

public class CuentaBancaria {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Instanciamos la cuenta con datos iniciales
        Cuenta miCuenta = new Cuenta("Juan Pérez", 1000.0, "Ahorros", "Bancolombia", "1017234567");

        int opcion = 0;

        // El bucle se ejecuta mientras el usuario no elija salir (opción 4)
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

    // Clase interna declarada como static para poder instanciarla directamente desde main
    static class Cuenta {
        String titular;
        double saldo;
        String tipoCuenta;
        String banco;
        String id;

        public Cuenta(String titular, double saldo, String tipoCuenta, String banco, String id) {
            this.titular = titular;
            this.saldo = saldo;
            this.tipoCuenta = tipoCuenta;
            this.banco = banco;
            this.id = id;
        }

        public void depositar(double valor) {
            if (valor > 0) {
                saldo += valor;
                System.out.println("Depósito exitoso de $" + valor);
                mostrarSaldo();
            } else {
                System.out.println("El monto a depositar debe ser mayor a 0.");
            }
        }

        public void retirar(double monto) {
            if (monto <= 0) {
                System.out.println("El monto a retirar debe ser mayor a 0.");
            } else if (monto > saldo) {
                System.out.println("Fondos insuficientes. Su saldo actual es: $" + saldo);
            } else {
                saldo -= monto;
                System.out.println("Retiro exitoso de $" + monto);
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
}