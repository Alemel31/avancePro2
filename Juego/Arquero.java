package Juego;

public class Arquero extends Personaje {
    public Arquero(String Nombre, int salud,int dano) {
        super(Nombre, salud, dano);

    }

    @Override
    public void atacar(Enemigo enemigo) {
        System.out.println("🏹 El " + nombre + " dispara una flecha a " + enemigo.getNombre());
        enemigo.recibirDanio(getDano());
    }

    @Override
    public void habilidadEspecial() {
        if (getEnergia() >= 10) {
            System.out.println("El" + nombre + " usa 'Disparo Preciso' para atacar con mayor precisión.");
            setDano(getDano() + 7);
            setEnergia(getEnergia() - 10);
        } else {
            System.out.println(nombre + " no tiene suficiente energía.");
        }
    }
}
