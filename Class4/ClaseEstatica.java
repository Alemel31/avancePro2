package Class4;

public class ClaseEstatica {                // CLASE EXTERNA
    private static int variableEstatica = 20;
    private int variableInstancia = 5;

    static class ClaseAnidadaEstatica {     // Clase interna
        void mostrar (){
            System.out.println(variableEstatica);
            //System.out.println(variableInstancia);
        }
    }

    public static void main(String[] args) {
        //CLASE EXT . clase interna         #name = new    CLASE EXT . clase interna
        ClaseEstatica.ClaseAnidadaEstatica objeto = new ClaseEstatica.ClaseAnidadaEstatica();
        objeto.mostrar();
    }
}
