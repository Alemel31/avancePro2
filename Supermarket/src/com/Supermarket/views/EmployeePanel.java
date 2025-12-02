package com.Supermarket.views;

import com.Supermarket.models.Employee;
import com.Supermarket.utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePanel extends JPanel {
    private DefaultTableModel tableModel;
    private List <Employee> employeeList = new ArrayList<>();

    private JTextField idField, userField;
    private JPasswordField passwordField;
    private JComboBox <String> roleSelector;

    public EmployeePanel(){
        setLayout(new BorderLayout(10,10));
        employeeList = FileHandler.loadEmployees();
        // Form de ingreso
        JPanel formPanel = createFormPanel ();
        add(formPanel, BorderLayout.NORTH);

        //tabla de empleados
        String []columns = {"ID", "Usuario", "Rol"};
        tableModel = new DefaultTableModel(columns,0);
        JTable employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        loadTableData();
    }

    private JPanel createFormPanel (){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));

        idField = new JTextField(5);
        idField.setText(String.valueOf(System.currentTimeMillis()).substring(5));
        idField.setEditable(false);
        userField = new JTextField(15);
        passwordField = new JPasswordField(15);
        roleSelector = new JComboBox<>(new String[]{"Cajero", "Admin"});
        JButton saveButton = new JButton("Guardar Empleado");
        saveButton.addActionListener(e -> handleSaveAction());

        panel.add(new JLabel ("ID"));
        panel.add(idField);
        panel.add(new JLabel ("Usuario"));
        panel.add(userField);
        panel.add(new JLabel("Contraseña"));
        panel.add(passwordField);
        panel.add(new JLabel ("Rol"));
        panel.add(roleSelector);
        panel.add(saveButton);

        return panel;
    }

    private void loadTableData(){
        tableModel.setRowCount(0);
        for (Employee emp : employeeList){
            tableModel.addRow(new Object[]{emp.getId(), emp.getUsername(), emp.getPassword(), emp.getRole()});
        }
    }

    private void handleSaveAction(){
        try {
            String id = idField.getText();
            String user = userField.getText().trim();
            String password = new String(passwordField.getText());
            String role = (String) roleSelector.getSelectedItem();

            if (user.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Rellene todos los campos");
                return;
            }
            Employee newEmployee = new Employee(id,user,password,role);
            employeeList.add(newEmployee);
            FileHandler.saveEmployees(employeeList);

            loadTableData();

            idField.setText(String.valueOf(System.currentTimeMillis()).substring(5));
            userField.setText("");
            passwordField.setText("");

            JOptionPane.showMessageDialog(this, "Empleado guardoa con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
