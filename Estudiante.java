package Class7;

public class Estudiante {
    private String nombre;
    public Estudiante(String nombre) {
        this.nombre = nombre;
    }
    // ASOCIACION
    public String getNombre() {
        return nombre;
    }
    // ASOC Y AGREGACION
    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre);
    }
}
