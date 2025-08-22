package Class4;

import java.util.Scanner;

public class EjemploArray {
    public static void main(String[] args) {
        Scanner dato = new Scanner(System.in);
        System.out.println("Que cantidad de notas desea ingresar: ");
        int numNotas = dato.nextInt();
        int [] notas = new int[numNotas];
        for (int x = 0; x < numNotas; x++){
            System.out.println("Ingrese nota "+ (x+1));
            notas [x] = dato.nextInt ();
        }
        for (int x : notas)
            System.out.println (x);
    }
}
