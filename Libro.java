package Class2;

public class Libro {
    String titulo;
    String autor;
    int paginas;

    void consulta(){
        System.out.println("Bienvenido, le mostramos el listado de libros disponibles: ");
        System.out.println("++++++++++++++++++++++++++++++");
    }
    void mostrarInfo(){
        System.out.println("Titulo: "+titulo);
        System.out.println("Autor : "+autor);
        System.out.println("Paginas: "+paginas);

    }

    public static void main(String[] args) {
        Libro libro1 = new Libro();
        Libro libro2 = new Libro();


        libro1.titulo = "Don Quijote de la mancha";
        libro1.autor = "Miguel de Cervantes";
        libro1.paginas = 100;


        libro2.titulo = "El águila y la gallina";
        libro2.autor = "Leonardo Boff";
        libro2.paginas = 70;



        libro1.consulta();
        libro1.mostrarInfo();
        libro2.consulta();
        libro2.mostrarInfo();
    }
}
