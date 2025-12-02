package com.Supermarket.views;

import com.Supermarket.models.Employee;
import com.Supermarket.utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;

    public  LoginFrame() {
        setTitle("Supermarket - Login");
        setSize (400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel formPanel = new JPanel(new GridLayout(3,2,5,5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));

        userField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Ingresar");

        formPanel.add(new JLabel("Usuario:"));
        formPanel.add(userField);
        formPanel.add(new JLabel("Contrasena:"));
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> attemptLogin ());
    }

    private void attemptLogin() {
        System.out.println("--- INICIANDO INTENTO DE LOGIN ---");

        String usernameInput = userField.getText().trim();
        String passwordInput = new String(passwordField.getPassword()); // Sin trim() en contraseña por si acaso

        System.out.println("Usuario ingresado: '" + usernameInput + "'");
        System.out.println("Contraseña ingresada: '" + passwordInput + "'");

        // 1. Verificar si la lista se carga
        List<Employee> employees = FileHandler.loadEmployees();

        if (employees == null) {
            System.out.println("ERROR CRÍTICO: La lista de empleados es NULL.");
            JOptionPane.showMessageDialog(this, "Error: No se pudo cargar la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Cantidad de empleados cargados desde archivo: " + employees.size());

        Employee loggedInUser = null;

        // 2. Recorrer y mostrar qué se está comparando
        for (Employee emp : employees) {
            String empUser = emp.getUsername();
            String empPass = emp.getPassword();

            // Imprimimos lo que hay en el archivo para este empleado
            System.out.println("Comparando con empleado del archivo -> Usuario: '" + empUser + "' | Pass: '" + empPass + "'");

            // Chequeo de nulos para evitar crash si el archivo tiene líneas mal formadas
            if (empUser == null || empPass == null) {
                System.out.println("   -> SALTADO: Datos incompletos en este registro.");
                continue;
            }

            // Comparación
            if (empUser.equals(usernameInput) && empPass.equals(passwordInput)) {
                System.out.println("   -> ¡COINCIDENCIA ENCONTRADA!");
                loggedInUser = emp;
                break;
            } else {
                System.out.println("   -> No coincide.");
            }
        }

        System.out.println("----------------------------------");

        if (loggedInUser != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + loggedInUser.getUsername(), "Acceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
            new MainFrame(loggedInUser).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
