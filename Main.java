package Class2;

public class Main {
    public static void main(String[] args) {
        Perro doggy1 = new Perro();
        Perro doggy2 = new Perro();

        doggy1.nombre = "Manchas";
        doggy1.edad = 5;
        doggy1.raza = "cunumi";

        doggy2.nombre = "Firulais";
        doggy2.edad = 2;
        doggy2.raza = "bulldog";

        doggy1.dormir();
        doggy1.mostrarInfo();

        doggy2.mostrarInfo();
    }
}
