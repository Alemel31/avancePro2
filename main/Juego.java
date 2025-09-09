package main;

import Juego.*;

public class Juego {
    public static void main(String[] args) {
        Personaje[] personajes = {              // estamos creando e instanciando a los personajes con salud y dano
                new Guerrero("Mario el guerrero", 100, 20),
                new Mago("Raquel la hechizera ", 80, 15),
                new Arquero("Marcelo el arquero", 90, 18)
        };

        Enemigo enemigo = new Enemigo("Dragón", 150);   // creo un nuevo enemigo
        Objeto pocion = new Objeto("Poción Curativa", 20); // estoy creando una pocion
        //  POLIMORFISMO
        for (Personaje p : personajes) {        //Array objeto Personaje para recorrer el for
            ejecutarTurno(p, enemigo, pocion);  //personaje, enemigo, pocion
        }

        System.out.println("¡El combate ha terminado!");
    }
    private static void ejecutarTurno(Personaje p, Enemigo enemigo, Objeto pocion) {
        p.mostrarEstado();                  // estado del personaje su vida, energia y salud
        p.mover();
        p.atacar(enemigo);
        p.habilidadEspecial();
        p.subirNivel();
        pocion.usar(p);                     // me voy a curar
        p.mostrarEstado();                  // veo el nuevo estado de salud y energia
        System.out.println("-----------");
        if (!enemigo.estaVivo()) {
            System.out.println("¡El combate ha terminado! El " + enemigo.getNombre() + " ha sido derrotado.");
        } else {
            System.out.println("El " + enemigo.getNombre() + " sigue en pie con " + enemigo.getSalud() + " de salud.");
        }
    }
}