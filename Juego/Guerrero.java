package Juego;

import Juego.Enemigo;

public class Guerrero extends Personaje {
    public Guerrero(String Nombre, int salud,int dano) {
        super(Nombre, salud, dano);
        arma = new Espada();

    }

    @Override
    public void atacar(Enemigo enemigo) {
        System.out.println(nombre + " ataca con" + arma.getClass().getSimpleName() + enemigo.getNombre());
        enemigo.recibirDanio(getDano());
    }

    @Override
    public void habilidadEspecial() {
        if (getEnergia() >= 10) {
            System.out.println( nombre + " usa 'Furia Berserker'.");
            dano+=15;
            setDano(dano);
            setEnergia(-10);
        } else {
            System.out.println(nombre + " no tiene suficiente energía.");
        }
    }
}
