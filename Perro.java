package Class2;

public class Perro {
    String nombre;
    String raza;
    int edad;

    void ladrar (){
        System.out.println("Ladrando");
    }
    void dormir(){
        System.out.println("El doggy esta durmiendo");
    }
    //crear un metodo para presentarme las caracteristicas de mi perro **void mostrarInfo** METODO
    void mostrarInfo(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Raza: "+raza);
        System.out.println("Edad: "+edad);
    }

}
