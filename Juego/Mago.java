package Juego;

import Juego.Enemigo;

public class Mago extends Personaje {
    public Mago(String Nombre, int salud,int dano) {
        super(Nombre, salud, dano);

    }

    @Override
    public void atacar(Enemigo enemigo) {
        System.out.println("✨ El " + nombre + " lanza un hechizo a " + enemigo.getNombre());
        enemigo.recibirDanio(getDano());
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("🧙 El " + nombre + " usa 'Rayo Mágico' para dañar a todos los enemigos.");
        setDano(getDano() +5 );
    }
}
