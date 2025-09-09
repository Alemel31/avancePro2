package Class6;

public class Perro extends Animal{
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
}
