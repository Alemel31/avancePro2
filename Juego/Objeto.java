package Juego;

public class Objeto {
    String nombre;
    int curacion;

    public Objeto(String nombre, int curacion) {
        this.nombre = nombre;
        this.curacion = curacion;
    }
    // llamar a este metodo necesito un personaje
    public void usar(Personaje personaje) {
        int saludRestaurada = personaje.getSalud() + curacion;  // te crea una variable saludRestaurada de mi personaje obtengo su salud + la curacion
        if (saludRestaurada > personaje.getSaludMaxima()) {    // este nuevo valor es > que su salud maxima
            saludRestaurada = personaje.getSaludMaxima();       // no permite que se pase el limite de la saludMaxima
        }
        personaje.setSalud(saludRestaurada);                    // el nuevo valor de su salud despues de la curacion
        System.out.println(personaje.nombre + " usa " + nombre + " y recupera " + curacion + " de salud.");
    }
}
