public class ProgramaCuentaBancaria{
    public static void main(String[] args){
        System.out.println("Hello");
        CuentaBancaria cuenta1 = new CuentaBancaria("Peter",20, "Corriente", "Bancolombia", 1212);
        cuenta1.depsositar(300);
    }
}

class CuentaBancaria{
    String titular;
    int saldo;
    String tipoCuenta;
    String banco;
    double ID;

    public CuentaBancaria(String titular, int saldo, String tipoCuenta, String banco, int  ID){
        this.titular = titular;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.banco = banco;
        this.ID = ID;
    }

    public void depsositar(double Valor){
        saldo += Valor;
        System.out.println("El saldo total es" + Valor);
    }

    public void retirar(double Monto){

    }

    public void mostrarSaldo(){

    }

    public void mostrarInformacionCuenta(){

    }
}

