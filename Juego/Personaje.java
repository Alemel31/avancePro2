package Juego;

import Juego.Enemigo;

public abstract class Personaje {
    protected String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    protected int dano;
    private int energia;
    protected Arma arma;

    public Personaje(String nombre, int salud, int dano) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.dano = dano;
        this.nivel = 1;
        this.energia = 100;
    }

    public abstract void atacar(Enemigo enemigo);

    public void mover() {
        System.out.println(nombre + " se mueve.");
    }

    public abstract void habilidadEspecial();

    public void subirNivel() {
        nivel++;
        saludMaxima += 10;
        salud = saludMaxima;
        dano += 5;
        System.out.println(nombre + " ha subido de nivel a " + nivel + "!");
    }

    void recibirDanio(int cantidad) {
        salud -= cantidad;
        if (salud <= 0) {
            System.out.println(nombre + " ha sido derrotado.");
        } else {
            System.out.println(nombre + " tiene " + salud + " de salud restante.");
        }
    }

    void curar(int cantidad) {
        salud += cantidad;
        if (salud > saludMaxima) {
            salud = saludMaxima;
        }
        System.out.println(nombre + " se cura y tiene ahora " + salud + " de salud.");
    }

    public void mostrarEstado() {
        System.out.println("Estado de " + nombre + ": Nivel " + nivel + ", Salud " + salud + "/" + saludMaxima + ", Daño " + dano);
    }

    protected int getDano() {
        return dano;
    }

    protected void setDano(int nuevoDano) {
        this.dano = nuevoDano;
    }

    protected int getEnergia() {
        return energia;
    }

    protected void setEnergia(int nuevaEnergia) {
        this.energia = nuevaEnergia;
    }

    protected int getSalud() {
        return salud;
    }

    protected void setSalud(int nuevaSalud) {
        this.salud = nuevaSalud;
    }

    protected int getSaludMaxima() {
        return saludMaxima;
    }

    protected void setSaludMaxima(int nuevaSaludMaxima) {
        this.saludMaxima = nuevaSaludMaxima;
    }
}
