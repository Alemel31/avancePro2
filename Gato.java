package Class6;

public class Gato extends Animal {
    protected String color;

    public Gato(String nombre, String color) {
        super(nombre);
        this.color = color;
    }

    @Override
    public void hacerSonido() {
        System.out.println("El gato dice Miau Miau");
    }

    @Override
    public String toString() {
        return "El nombre del gato es: " + nombre + ", y su color es: " + color;
    }
}
