package Class8;

public class Perro extends Animal implements AccionesObligatorias{
    protected String raza;
    public Perro(String nombre, String raza) {
        super(nombre);
        this.raza = raza;
    }

    @Override
    public void hacerSonido() {
        System.out.println("El Perro hace Wau Wau");
    }

    @Override
    public String toString() {
        return "El nombre del perro es: " + nombre + ", y su raza es : " + raza;
    }

    @Override
    public void comer() {

    }

    @Override
    public void dormir() {

    }

    @Override
    public void respirar() {

    }

    @Override
    public void jugar() {

    }
}
