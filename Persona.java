package Class5;

public class Persona {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void intercambiarPersona(Persona otraPersona) { //temp = temporal
        String temp;
        temp = otraPersona.getNombre();
        otraPersona.setNombre (this.nombre) ;
        this.nombre = temp;
    }

    public static void main(String[] args) {
        Persona p1 = new Persona("Mateo");
        Persona p2 = new Persona("Brian");

        System.out.println(p1.getNombre());
        System.out.println(p2.getNombre());

        p1.intercambiarPersona(p2);

        System.out.println("+++++++++");
        System.out.println(p1.getNombre());
        System.out.println(p2.getNombre());

    }
}
