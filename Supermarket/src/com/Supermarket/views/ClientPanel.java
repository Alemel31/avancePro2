package com.Supermarket.views;

import com.Supermarket.models.Client;
import com.Supermarket.utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientPanel extends JPanel {
    private DefaultTableModel tableModel;
    private List<Client> clientList = new ArrayList<>();

    private JTextField idField, nameField, phoneField;

    public  ClientPanel() {
        setLayout(new BorderLayout(10,10));
        // Cargar al lista de clientes
        clientList = FileHandler.loadClients();
        // Form de ingreso
        JPanel formPanel = createFormPanel ();
        add(formPanel, BorderLayout.NORTH);

        // tabla de clientes
        String [] columns = {"ID", "Nombre", "Telefono"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable clientTable = new JTable(tableModel);
        add(new JScrollPane(clientTable), BorderLayout.CENTER);

        // Cargar datos al iniciar
       loadTableData ();
    }

    private JPanel createFormPanel (){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

        idField = new JTextField(5);
        nameField =new JTextField(15);
        phoneField = new JTextField(7);
        JButton saveButton = new JButton("Guardar Cliente");
        saveButton.addActionListener(e -> handleSaveAction());

        panel.add(new JLabel("ID Cliente:"));
        panel.add(idField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);
        panel.add(new JLabel("Telefono:"));
        panel.add(phoneField);
        panel.add(saveButton);

        return panel;
    }

    private void loadTableData (){
        tableModel.setRowCount(0);
        for (Client cli : clientList){
            tableModel.addRow(new Object[]{cli.getId(), cli.getName(), cli.getPhone()});
        }
    }

    private void handleSaveAction (){
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();

            if (name.isEmpty() || phone.isEmpty()){
                JOptionPane.showMessageDialog(this, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Client newClient= new Client(id, name, phone);
            clientList.add(newClient);
            FileHandler.saveClients(clientList);

            loadTableData();

            idField.setText(String.valueOf(System.currentTimeMillis()).substring(5));
            nameField.setText("");
            phoneField.setText("");

            JOptionPane.showMessageDialog(this, "Cliente guardado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al guardad cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
