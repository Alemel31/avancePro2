package Class7;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainAsoc {
    public static void main(String[] args) {
        Estudiante est1 = new Estudiante("Maria");
        Estudiante est2 = new Estudiante("Jose");
        Profesor p1 = new Profesor("Ing Inturias");

        p1.ensenar(est1);
        p1.ensenar(est2);

        Universidad u1 = new Universidad("NUR");
        p1.mostrarInfo();

    }
}