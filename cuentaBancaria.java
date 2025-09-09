package Class3;

public class cuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public cuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }
    public void depositar (double cantidad){
        saldo += cantidad;
    }
    public void retirar (double cantidad){
        if (saldo >= cantidad){
            saldo -= cantidad;
        }else {
            System.out.println("Saldo insuficiente");
        }
    }
    public void mostrarInfo(){
        System.out.println(numeroCuenta);
        System.out.println(titular);
        System.out.println(saldo);
    }

    public static void main(String[] args) {
        cuentaBancaria usuario1 = new cuentaBancaria("15461315", "Abigail Parada", 100);
    }
}
