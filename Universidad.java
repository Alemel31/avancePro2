package Class7;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private String nombre;
    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;

    public Universidad (String nombre){
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante){
        estudiantes.add(estudiante);
    }

    public void agregarProfesor (Profesor profesor){
        profesores.add(profesor);
    }
    public void mostrarEstudiantes(){
        System.out.println("Universidad: " + nombre);
        for (Estudiante e : estudiantes){
            e.mostrarInfo();
        }
    }
    public void mostrarProfesores(){
        System.out.println("-----------------------");
        System.out.println("Universidad: " + nombre);
        for (Profesor p : profesores){
            p.mostrarInfo();
        }
    }
}
