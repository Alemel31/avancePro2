package com.Supermarket.views;

import com.Supermarket.models.Product;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProductRenderer extends JPanel implements ListCellRenderer<Product>{

    private JLabel productNameLabel;
    private JLabel productPriceLabel;
    private JLabel productImageLabel;

    public  ProductRenderer(){
        setLayout(new BorderLayout(10,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel textPanel = new JPanel(new GridLayout(2, 1)); // 2 filas, 1 columna
        textPanel.setOpaque(false);

        productNameLabel = new JLabel();
        productNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Nombre grande

        productPriceLabel = new JLabel();
        productPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Subtitulo pequeño
        //productPriceLabel.setForeground(Color.gray);

        textPanel.add(productNameLabel);
        textPanel.add(productPriceLabel);

        productImageLabel = new JLabel();
        productImageLabel.setPreferredSize(new Dimension(50, 50)); // Tamaño fijo para la foto

        // 3. Agregar todo al panel principal
        add(textPanel, BorderLayout.CENTER); // Texto a la izquierda/centro
        add(productImageLabel, BorderLayout.EAST);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            // A. Configurar textos
            productNameLabel.setText(value.getName());

            // Lógica visual para el stock
            if (value.getStock() < 10) {
                productPriceLabel.setText("Bs. " + value.getPrice() + " (¡Solo quedan " + value.getStock() + "!)");
                productPriceLabel.setForeground(Color.RED);
            } else {
                productPriceLabel.setText("Precio Unitario: Bs. " + value.getPrice());
                productPriceLabel.setForeground(isSelected ? Color.WHITE : new Color(0xFF0B8D88, true));
            }

            // B. Configurar Imagen
            // IMPORTANTE: Redimensionar imágenes en tiempo real puede ser lento si son muchas.
            // Idealmente las imágenes ya deberían estar pequeñas, pero esto funcionará:
            String path = value.getImagePath();
            if (path != null && !path.isEmpty() && new File(path).exists()) {
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                productImageLabel.setIcon(new ImageIcon(img));
            } else {
                productImageLabel.setIcon(null); // O poner un icono por defecto
            }
        }

        // C. Manejo de colores de Selección (Azul cuando pasas el mouse)
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            productNameLabel.setForeground(list.getSelectionForeground());
            // El priceLabel ya lo manejamos arriba para que sea blanco al seleccionar
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            productNameLabel.setForeground(Color.BLACK);
        }

        // Optimización de renderizado
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);

        return this;
    }
}
