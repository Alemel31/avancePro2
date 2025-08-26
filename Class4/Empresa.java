import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private List<Departamento> departamentos;

    public Empresa() {
        departamentos = new ArrayList<>();
    }

    public static class Departamento {
        private String nombre;
        private int numeroEmpleados;

        public Departamento(String nombre, int numeroEmpleados) {
            this.nombre = nombre;
            this.numeroEmpleados = numeroEmpleados;
        }

        public String getNombre() {
            return nombre;
        }

        public int getNumeroEmpleados() {
            return numeroEmpleados;
        }

        @Override
        public String toString() {
            return "Departamento: " + nombre + ", Empleados: " + numeroEmpleados;
        }
    }


    // Mostrar todos los departamentos
    public void mostrarDepartamentos() {
        for (Departamento d : departamentos) {
            System.out.println(d);
        }
    }

    // Método para agregar un departamento
    public void agregarDepartamento(String nombre, int numeroEmpleados) {
        Departamento d = new Departamento(nombre, numeroEmpleados);
        departamentos.add(d);
    }


    public static void main(String[] args) {
        Empresa miEmpresa = new Empresa();
        miEmpresa.agregarDepartamento("Recursos Humanos", 5);
        miEmpresa.agregarDepartamento("TI", 10);
        miEmpresa.agregarDepartamento("Finanzas", 3);

        miEmpresa.mostrarDepartamentos();
    }
}
