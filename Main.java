package Class6;

public class Main {
    public static void main(String[] args) {
        Estudiante p1 = new Estudiante("Juan", 18, 212108671, "Ing Civil");

        System.out.println(p1.informacion());
        // Get Class --> devuelve el object
        System.out.println(p1.getClass().getSimpleName());
        System.out.println("-------------------");

        Perro cachu = new Perro("Cachuchin", "Callejero");


        cachu.hacerSonido();
        // cachu solo imprime paquete y clase y donde esta orientada la clase dentro del sistema
        // cambia cuando utilizamos To String con el Override eso ya va de forma mas especifica
        System.out.println(cachu);
        System.out.println("-------------------");

        Gato michi  = new Gato("Michi", "Blanco");
        michi.hacerSonido();
        System.out.println(michi);
        System.out.println("-------------------");

        Pato pio   = new Pato("Pio", "Vuela");
        pio.hacerSonido();
        System.out.println(pio);
        System.out.println("-------------------");

        Object objeto1 = "Hola Mundo";
        Object objeto2 = 123;
        Object objeto3 = 45.6;

        if (objeto1 instanceof String) {
            System.out.println("Objeto1 es una cadena");
        }
        if (objeto2 instanceof Integer){
            System.out.println("Objeto2 es un entero");
        }
        if (objeto3 instanceof Double){
            System.out.println("Objeto3 es un decimal");
        }
        System.out.println("------Instance Of---------");
        System.out.println(cachu instanceof Perro);



    }
}
