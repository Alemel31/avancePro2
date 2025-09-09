package Class5;

import javax.swing.*;
import java.util.Scanner;

public class EjTryCatch {
    public static void main(String[] args) {
        Scanner dato = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "Bienvenido");
        String palabra = JOptionPane.showInputDialog("Ingrese un número entero: ");
        try {
            int valor = Integer.parseInt(palabra);
            JOptionPane.showMessageDialog(null, "El valor de la palabra es: " + valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es un entero válido");
        } finally {
            System.out.println("Programa finalizado");
        }
    }
}
