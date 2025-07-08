import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Juego de Colisión - Esquiva los enemigos");
        JuegoProyecto juegoProyecto = new JuegoProyecto();
        frame.add(juegoProyecto);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}