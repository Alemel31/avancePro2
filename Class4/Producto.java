package Class4;

import java.util.ArrayList;

public class Producto {
    public String nombre;
    private double precio ;
    private int cantidad;


    public Producto (String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // agregar Get y Set
    // click derecho Generate y agregar las variables
    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }


    public static void main(String[] args) {
        ArrayList <Producto> listProductos = new ArrayList<>();
        double totalCompra = 0;
        Producto p1 =new Producto("Leche", 5, 2);
        Producto p2 =new Producto("Pan", 2, 10);
        listProductos.add(p2);
        listProductos.add(p1);
        for (Producto x: listProductos){
            System.out.println("Producto: "+ x.nombre);
            totalCompra += (x.getCantidad()* x.getPrecio());
        }
        System.out.println("Total compra "+ totalCompra);

    }
}
