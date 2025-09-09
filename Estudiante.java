package Class3;

public class Estudiante {
    public static int contador = 0;
    public String nombre;
    private int registro;
    private int edad;

    public Estudiante(String nombre) {
        contador++;
        this.nombre = nombre;
    }

    public Estudiante(int registro, int edad) {
        this.registro = registro;
        this.edad = edad;
    }

    public Estudiante(String nombre, int registro, int edad) {
        this.nombre = nombre;
        this.registro = registro;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Registro: " + registro);
        System.out.println("Edad: " + edad);
        System.out.println("---------------");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public int getEdad() {
        return edad;

    }

    public void setEdad(int edad) {

        if (edad > 10 && edad < 100) {
            this.edad = edad;

        } else {
            System.out.println("Edad invalido, ingrese una edad valida");
        }
    }


}
