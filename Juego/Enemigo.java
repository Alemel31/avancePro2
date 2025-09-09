package Juego;

public class Enemigo {
    String nombre;
    private int salud;
    private boolean estaVivo;

    public Enemigo(String nombre, int salud) {
        this.nombre = nombre;
        this.salud = salud;
        this.estaVivo = true;
    }

    public String getNombre() {

        return nombre;
    }

    void recibirDanio(int cantidad) {
        if (estaVivo) {
            salud -= cantidad;
            if (salud <= 0) {
                estaVivo = false;
                System.out.println(nombre + " ha sido derrotado.");
            } else {
                System.out.println(nombre + " tiene " + salud + " de salud restante.");
            }
        } else {
            System.out.println(nombre + " ya está derrotado.");
        }
    }

    public int getSalud() {

        return salud;
    }

    public boolean estaVivo() {

        return estaVivo;
    }
}
