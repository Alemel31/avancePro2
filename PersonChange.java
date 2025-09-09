package Class5;

public class PersonChange {
    private String nombre;
    private String apellido;
    private int edad;

    public PersonChange(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public PersonChange(String nombre) {
        this.nombre  = nombre;
    }
    public void copiarDatos (PersonChange personaDestino){
        personaDestino.setNombre(this.nombre);
        personaDestino.setApellido(this.apellido);
        personaDestino.setEdad(this.edad);
    }
    public void mostrarDatos(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Apellido: " + this.apellido);
        System.out.println("Edad: " + this.edad);
    }
    public static void main(String[] args) {
        PersonChange p1 = new PersonChange("Maria", "Parada", 20);
        PersonChange p2 = new PersonChange("Pedro", "Ortiz", 18);
        p1.mostrarDatos();
        p2.mostrarDatos();

        System.out.println("-----------");

        p1.copiarDatos(p2);
        p2.mostrarDatos();
    }
}
