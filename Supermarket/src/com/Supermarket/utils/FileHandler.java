package com.Supermarket.utils;

import com.Supermarket.models.Client;
import com.Supermarket.models.Employee;
import com.Supermarket.models.Product;
import com.Supermarket.models.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String SEPARATOR = ",";
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final String SALES_FILE = "sales.txt";
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String CLIENTS_FILE = "clients.txt";

    //? List type parameter indicates that the method can accept a List of any type.
    private static void saveList (List<?> list, String filePath){
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))){
            for (Object item : list){
                if (item instanceof Product){
                    pw.println(((Product) item).toTxtFormat());
                } else if (item instanceof Sale){
                    pw.println(((Sale)item).toTxtFormat());
                } else if (item instanceof Employee) {
                    pw.println(((Employee) item).toTxtFormat());
                } else if (item instanceof Client) {
                    pw.println(((Client)item).toTxtFormat());

                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en "+filePath + ": " + e.getMessage());
        }
    }

    // persistencia de prodctos
    public static void saveProducts (List <Product> products){
        saveList(products, INVENTORY_FILE);
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        File inventoryFile = new File(INVENTORY_FILE);

        if (!inventoryFile.exists()) {
            System.out.println("⚠ El archivo inventory.txt no existe.");
            return products; // Retorna lista vacía pero no nula
        }

        try (BufferedReader br = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Evitamos líneas vacías
                if (line.trim().isEmpty()) continue;

                // Debug: Ver qué está leyendo
                System.out.println("Leyendo producto: " + line);

                // Usamos el SEPARATOR que definimos arriba (ahora debería ser ";")
                String[] parts = line.split(SEPARATOR);

                // Usamos >= 5 por seguridad
                if (parts.length >= 5) {
                    try {
                        String id = parts[0].trim();
                        String name = parts[1].trim();

                        // IMPORTANTE: El .trim() aquí es vital.
                        // Si llega " 10.5 ", Double.parseDouble falla en algunas versiones sin trim.
                        double price = Double.parseDouble(parts[2].trim());

                        // IMPORTANTE: Integer.parseInt(" 50 ") SIEMPRE falla si hay espacios.
                        int stock = Integer.parseInt(parts[3].trim());

                        String imagePath = parts[4].trim();

                        products.add(new Product(id, name, price, stock, imagePath));

                    } catch (NumberFormatException e) {
                        System.err.println("❌ Error de formato numérico en la línea: " + line);
                        System.err.println("   -> Asegúrate de que precio y stock sean solo números.");
                    }
                } else {
                    System.out.println("⚠ Línea con formato incorrecto (faltan columnas): " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error de lectura del archivo: " + e.getMessage());
        }

        System.out.println("Total productos cargados: " + products.size());
        return products;
    }

    // persistencia de empleados
    public  static void saveEmployees (List <Employee> employees){
        saveList(employees, EMPLOYEES_FILE);
    }

    public static List <Employee> loadEmployees (){
        List <Employee> employees = new ArrayList<>();
        File employeeFile = new File(EMPLOYEES_FILE);

        if (!employeeFile.exists()){

            Employee defaultAdmin = new Employee("1", "admin", "1234", "Admin");
            employees.add(defaultAdmin);
            saveEmployees(employees);
            return employees;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(employeeFile))){
            String line;
            while ((line = br.readLine()) !=null){
                String [] parts = line.split(SEPARATOR);
                if (parts.length == 4){
                    employees.add(new Employee(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        }catch (IOException e){
            System.err.println("Error al cargar empleados :" + e.getMessage());
        }
        return employees;
    }

    // persistencia de clientes

    public static void saveClients (List <Client> clients){
        saveList(clients, CLIENTS_FILE);
    }

    public static List <Client> loadClients (){
        List <Client> clients = new ArrayList<>();
        File clientFile = new File(CLIENTS_FILE);

        if (!clientFile.exists()){
            Client defaultClient = new Client("C-0002","Maria Flores","123456");
            clients.add(defaultClient);
            saveClients(clients);
            return clients;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(clientFile))){
            String line;
            while ((line = br.readLine()) !=null){
                String [] parts = line.split(SEPARATOR);
                if (parts.length == 3){
                    clients.add(new Client(parts[0], parts[1], parts[2]));
                }
            }

        } catch (IOException e){
            System.err.println("Error al cargar clientes :" + e.getMessage());
        }
        return clients;
    }

    // persistencia de ventas (historial)

    public static void saveSales (Sale sale){
        try (PrintWriter pw = new PrintWriter(new FileWriter(SALES_FILE, true))){
            pw.println(sale.toTxtFormat());
        } catch (IOException e) {
            System.err.println("Erros al guardar venta" + e.getMessage());
        }
    }

    public static List <Sale> loadSales () {
        List<Sale> sales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 4) {
                    String id = parts[0];
                    String date = parts[1];
                    String details = parts[2];
                    double total = Double.parseDouble(parts[3]);
                    sales.add(new Sale(id, date, details, total));
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo de ventas no encontrado ");
        }catch (IOException e){
            System.err.println("Error de lectura: "+ e.getMessage());
        }
        return sales;
    }

}
