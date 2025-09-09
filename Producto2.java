package Class3;

import java.util.ArrayList;

public class Producto2 {
    public static void main(String[]args){
        double totalCompra=0;
        Producto p1=new Producto("leche",5,2);
        Producto p2=new Producto("Pan",2,10);
        ArrayList<Producto> listaproductos = new ArrayList<>();
        listaproductos.add(p2);
        listaproductos.add(p1);
        for(Producto x:listaproductos){
            System.out.println("Producto: "+ x.nombre);
            totalCompra += (x.getCantidad()*x.getPrecio());

        }
        System.out.println("Total: "+totalCompra);


    }
}
