package com.Supermarket.views;

import com.Supermarket.models.Employee;
import com.Supermarket.utils.Refreshable;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame (Employee loggerInUser) {
        setTitle("Supermarket - Usuario" + loggerInUser.getUsername() + " ("+loggerInUser.getRole() + ")") ;
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        InventoryPanel inventoryPanel = new InventoryPanel();
        HistoryPanel historyPanel = new HistoryPanel();
        ClientPanel clientPanel = new ClientPanel();
        EmployeePanel employeePanel = new EmployeePanel();
        SalesPanel salesPanel = new SalesPanel();
        tabs.addTab("Inventario", inventoryPanel);
        tabs.addTab("Ventas", salesPanel);
        tabs.addTab("Historial de ventas", historyPanel);
        tabs.addTab("Clientes", clientPanel);
        tabs.addTab("Empleados", employeePanel);


        tabs.addChangeListener(e -> {
            // 1. Obtener el panel que se acaba de seleccionar
            Component selectedComponent = tabs.getSelectedComponent();

            // 2. Verificar si ese panel es "Refrescable"
            if (selectedComponent instanceof Refreshable) {
                // 3. Forzar la actualización
                ((Refreshable) selectedComponent).refreshData();
            }
        });

        add(tabs);
    }
}
