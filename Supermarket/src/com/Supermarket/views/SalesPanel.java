package com.Supermarket.views;

import com.Supermarket.models.Product;
import com.Supermarket.models.Sale;
import com.Supermarket.utils.FileHandler;
import com.Supermarket.utils.Refreshable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;


public class SalesPanel extends JPanel implements Refreshable {
    private JTextField numberField;
    private DefaultTableModel cartModel;
    private JComboBox<Product> productDropdown; // El Dropdown avanzado
    private JLabel totalLabel;

    // Lista maestra del inventario
    private List<Product> inventoryList;


    public SalesPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inventoryList = FileHandler.loadProducts();

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Nueva Venta"));

        productDropdown = new JComboBox<>();
        productDropdown.setRenderer(new ProductRenderer()); // AQUÍ USAMOS EL RENDERIZADOR
        // Llenamos el dropdown
        refreshDropdown();

        numberField = new JTextField(5);
        JButton addButton = new JButton("Agregar");
        addButton.setBackground(new Color(52, 152, 219)); // Azul
        addButton.setForeground(Color.WHITE);

        // Layout (GridBag para control total)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Label Producto y Dropdown
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        topPanel.add(new JLabel("Seleccionar Producto:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; // El dropdown se estira
        gbc.gridwidth = 3; // Ocupa 3 columnas
        topPanel.add(productDropdown, gbc);

        // Fila 1: Label Cantidad, Input y Botón
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; gbc.gridwidth = 1;
        topPanel.add(new JLabel("Cantidad:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        topPanel.add(numberField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        topPanel.add(addButton, gbc);

        // --- PANEL CENTRAL (Tabla Carrito) ---
        cartModel = new DefaultTableModel(new String[]{"Producto", "Cantidad", "Precio Unit.", "Subtotal"}, 0);
        JTable cartTable = new JTable(cartModel);
        cartTable.setRowHeight(25); // Filas un poco más altas
        JScrollPane tableScroll = new JScrollPane(cartTable);


        // --- PANEL INFERIOR (Total y Confirmar) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        totalLabel = new JLabel("Total: Bs. 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalLabel.setForeground(new Color(44, 62, 80));

        JButton checkoutButton = new JButton("CONFIRMAR VENTA");
        checkoutButton.setBackground(new Color(46, 204, 113)); // Verde
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.setPreferredSize(new Dimension(220, 50));

        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);

        // Agregamos tpdo al panel PRincipal
        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // EVENTOS
        addButton.addActionListener(e -> handleAddToCart());
        checkoutButton.addActionListener(e -> handleCheckout());
    }

    private void refreshDropdown() {
        productDropdown.removeAllItems();
        for (Product p : inventoryList) {
            productDropdown.addItem(p);
        }
    }

    private void handleAddToCart() {
        Product selected = (Product) productDropdown.getSelectedItem();
        if (selected == null) return;

        try {
            int quantity = Integer.parseInt(numberField.getText());

            // Validaciones
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0");
                return;
            }
            if (quantity > selected.getStock()) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente. Solo quedan: " + selected.getStock());
                return;
            }

            // Lógica de negocio
            double subtotal = quantity * selected.getPrice();

            // Agregar a la tabla visual
            cartModel.addRow(new Object[]{
                    selected.getName(),
                    quantity,
                    selected.getPrice(),
                    String.format("%.2f", subtotal).replace(",", ".")
            });

            // Restar stock TEMPORALMENTE en memoria
            selected.setStock(selected.getStock() - quantity);

            // Actualizar interfaz
            updateTotal();
            numberField.setText("");
            productDropdown.repaint(); // Para que el renderizador muestre el nuevo stock en rojo si bajó mucho

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un número válido");
        }
    }

    private void handleCheckout() {
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmar venta?", "Checkout", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // 1. GUARDAR CAMBIOS DE STOCK (Archivo inventory.txt)
        FileHandler.saveProducts(inventoryList);

        // 2. CREAR REGISTRO DE VENTA
        StringBuilder details = new StringBuilder();
        double totalSale = 0;

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            String name = cartModel.getValueAt(i, 0).toString();
            String qty = cartModel.getValueAt(i, 1).toString();
            String sub = cartModel.getValueAt(i, 3).toString();

            details.append(qty).append("x ").append(name).append(" | ");
            totalSale += Double.parseDouble(sub);
        }

        String saleId = "V-" + System.currentTimeMillis();
        String date = LocalDate.now().toString();

        // Crear objeto Sale (Asegúrate que tu modelo Sale acepte double o haz cast a int)
        Sale newSale = new Sale(saleId, date, details.toString(), (int)totalSale);

        // 3. GUARDAR VENTA (Archivo sales.txt)
        FileHandler.saveSales(newSale);

        // 4. LIMPIEZA
        cartModel.setRowCount(0);
        updateTotal();
        JOptionPane.showMessageDialog(this, "¡Venta Exitosa! Inventario actualizado.");
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            total += Double.parseDouble(cartModel.getValueAt(i, 3).toString());
        }
        totalLabel.setText("Total: Bs. " + String.format("%.2f", total));
    }

    @Override
    public void refreshData() {
        inventoryList = FileHandler.loadProducts();
        refreshDropdown();
        numberField.setText("");
    }
}
