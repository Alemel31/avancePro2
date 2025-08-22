package Class4;

public class ClaseNoEstatica {              // CLASE EXTERNA
    private int variableInstancia = 30;
    static int variableEstatica = 10;

    class ClaseInterna {                    //  clase interna
        void mostrar (){
            System.out.println(variableInstancia);
            System.out.println(variableEstatica);
        }
    }

    public static void main(String[] args) {
        ClaseNoEstatica objetoExterno = new ClaseNoEstatica();

        ClaseNoEstatica.ClaseInterna objetoInterno = objetoExterno.new ClaseInterna();
    }
}
