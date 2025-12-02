package com.Supermarket.views;

import com.Supermarket.models.Product;
import com.Supermarket.utils.FileHandler;
import com.Supermarket.utils.Refreshable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class InventoryPanel extends JPanel implements Refreshable {
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, priceField, stockField;
    private JTextField imagePathField;
    private JLabel imagePreviewLabel;
    private List<Product> productList;

    public InventoryPanel() {
        setLayout(new BorderLayout());

        // 1 SetUp table
        String[] columnNames = {"ID", "Nombre", "Precio", "Stock", "Imagen"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);

        loadData();
        setUpLowAlert();

        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        //2. SetUp Form
        JPanel formPanel = new JPanel(new FlowLayout());
        idField = createStyledTextField("ID");
        nameField = createStyledTextField ("Nombre");
        priceField = createStyledTextField ("Precio");
        stockField = createStyledTextField ("Stock");



        JPanel imageControlPanel = new JPanel(new BorderLayout(5, 0));
        imageControlPanel.setBorder(BorderFactory.createTitledBorder("Imagen"));

        imagePathField = new JTextField(15);
        imagePathField.setEditable(false); // El usuario no escribe, selecciona
        imagePathField.setText("resources/images/default.png"); // Valor por defecto

        JButton btnSelectImage = new JButton("...");
        btnSelectImage.setToolTipText("Seleccionar archivo");
        btnSelectImage.addActionListener(e -> selectImage());

        imageControlPanel.add(imagePathField, BorderLayout.CENTER);
        imageControlPanel.add(btnSelectImage, BorderLayout.EAST);

        JButton saveButton = new JButton("Guardar Producto");
        saveButton.setBackground(new Color(46,204,113));
        saveButton.setForeground(Color.WHITE);

        saveButton.addActionListener(e -> handleSaveAction());

        formPanel.add(idField);
        formPanel.add(nameField);
        formPanel.add(priceField);
        formPanel.add(stockField);
        formPanel.add(imageControlPanel);
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.SOUTH);
    }
    private JTextField createStyledTextField(String title) {
        JTextField field = new JTextField(10);
        field.setBorder(BorderFactory.createTitledBorder(title));
        return field;
    }
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();

        // 1. Filtro para que solo se puedan elegir imagenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // --- AQUÍ OCURRE LA MAGIA DE GUARDADO ---

            // 2. Definimos la carpeta destino dentro de TU proyecto
            // Esto creará una carpeta "resources" y dentro "images" en la raíz de tu proyecto
            File destFolder = new File("resources/images");

            // Si la carpeta no existe, la creamos automáticamente
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }

            // 3. Definimos el archivo destino (mismo nombre que el original)
            File destFile = new File(destFolder, selectedFile.getName());

            try {
                // 4. Copiamos el archivo.
                // REPLACE_EXISTING sirve por si seleccionas una foto que ya tenías, la actualiza.
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // 5. Ponemos en el input la ruta RELATIVA (la que guardaremos en la BD)
                // Guardamos "resources/images/foto.png", NO la ruta completa de tu disco C:
                imagePathField.setText("resources/images/" + selectedFile.getName());

                System.out.println("Imagen guardada en: " + destFile.getAbsolutePath());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al copiar la imagen: " + ex.getMessage());
            }
        }
    }

    private void handleSaveAction() {
        try {
            // Validaciones básicas...
            String id = idField.getText().trim();
            if(id.isEmpty()) return;

            Product newProduct = new Product(
                    id,
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    imagePathField.getText() // Tomamos la ruta que generó el selector
            );

            productList.add(newProduct);
            FileHandler.saveProducts(productList);
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Producto guardado!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Verifique los números");
        }
    }

    private void loadData (){
        System.out.println("--- REFRESCANDO TABLA ---"); // 1. Ver si entra al método
        tableModel.setRowCount(0);

        productList = FileHandler.loadProducts();

        // 2. Ver qué recibió del FileHandler
        if (productList == null) {
            System.out.println("ERROR: La lista es NULL");
        } else if (productList.isEmpty()) {
            System.out.println("ALERTA: La lista está VACÍA (FileHandler no leyó nada)");
        } else {
            System.out.println("EXITO: Se encontraron " + productList.size() + " productos.");
        }

        for (Product p : productList){
            System.out.println("   -> Agregando a tabla: " + p.getName()); // 3. Ver si intenta pintar
            tableModel.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice(), p.getStock(), p.getImagePath()
            });
        }
    }

    private void setUpLowAlert(){
        inventoryTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                int stockQuantity = Integer.parseInt(table.getValueAt(row, 3).toString());
                if (stockQuantity < 10) {
                    c.setBackground(Color.RED);
                }else {
                    c.setBackground(Color.WHITE);
                } if (isSelected) c.setBackground(new Color(184,207,229));
                return c;
            }
        });
    }

    private void clearForm(){
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        stockField.setText("");
        imagePathField.setText("resources/images/default.png");
    }

    @Override
    public void refreshData() {
        loadData();
    }
}
