package Class7;

public class MainAgreg {
    public static void main(String[] args) {
        Universidad uni = new Universidad("NUR");

        Estudiante e1 = new Estudiante("Carlos");
        Estudiante e2 = new Estudiante("Ana");
        Profesor p1 = new Profesor("Ing Inturias");

        uni.agregarEstudiante(e1);
        uni.agregarEstudiante(e2);
        uni.agregarProfesor(p1);

        uni.mostrarEstudiantes();
        uni.mostrarProfesores();
    }
}
