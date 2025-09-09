package Class8;

public class Pato extends Animal {
    protected String tipo;


    public Pato(String nombre, String tipo ) {
        super(nombre);
        this.tipo = tipo;
    }

    @Override
    public void hacerSonido() {
        System.out.println("El pato hace Cuac Cuac");
    }

    @Override
    public String toString() {
        return "El nombre del pato es: " + nombre + " y su tipo es: " + tipo;
    }
}
