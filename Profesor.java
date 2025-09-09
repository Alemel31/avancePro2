package Class7;

public class Profesor {
    private String nombre;
    public Profesor(String nombre) {
        this.nombre = nombre;
    }
    // ASOCIACION
    public void ensenar (Estudiante estudiante){
        System.out.println(nombre + " está en clase con "+estudiante.getNombre());
    }
    // AGREGACION
    public void mostrarInfo() {
        System.out.println("Profesor : " + nombre);
    }

}
