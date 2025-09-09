package Class5;

public class Carro {

    public static void main(String[] args) {
        Carro carro1 = new Carro();
        Carro carro2 = new Carro();

        //carro1.setColor("rojo");
        //carro2.setColor ("rojo");

        if (carro1 == carro2) {
            System.out.println("el mismo");
        }
        else {
            System.out.println("diferentes");
        }

    }
}
