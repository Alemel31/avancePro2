package com.Supermarket.views;

import com.Supermarket.models.Sale;
import com.Supermarket.utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JTable historyTable;
    private DefaultTableModel tableModel;

    public  HistoryPanel() {
        setLayout(new BorderLayout());
        // titulo
        JLabel title = new JLabel("Historia de ventas", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(title, BorderLayout.NORTH);

        //tabla
        String [] columns = {"Sale ID", "Fecha", "Detalles del producto", "Total (Bs)"};
        tableModel = new DefaultTableModel(columns, 0);
        historyTable = new JTable(tableModel);

        //ancho de las columnas por el detalle
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(300);

        add(new JScrollPane(historyTable), BorderLayout.CENTER);

        // boton de actualizar
        JButton refreshButton = new JButton("Refresh History");
        refreshButton.addActionListener(e -> loadHistory ());
        add(refreshButton, BorderLayout.SOUTH);

        // carga de datos al iniciar
        loadHistory();
    }

    private void loadHistory() {
        tableModel.setRowCount(0);
        List <Sale> sales = FileHandler.loadSales();
        for (Sale s : sales){
            tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getDate(),
                    s.getDetails(),
                    s.getTotal()
            });
        }
    }

}
