package Class6;

public class Estudiante extends Persona {  // palabra reservada extends
    private int matricula;
    private String carrera;
    public Estudiante(String nombre, int edad, int matricula, String carrera) {
        super(nombre, edad);
        this.matricula = matricula;
        this.carrera = carrera;
    }
    public String informacion (){
        return "Nombre: " + nombre + "| Carrera: " + carrera;
    }
}
