import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public InventoryPage() {
        setTitle("Pharmacy Management System - Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Column Names
        String[] columnNames = {
            "Inventory ID", "Name", "Description", "Category", "Unit Price",
            "Quantity", "Expiry Date", "Batch Number", "Supplier Information",
            "Reorder Level", "Prescription Required", "Storage Conditions"
        };

        // Initialize the model with column names and no rows
        model = new DefaultTableModel(columnNames, 0);

        // Load data into the model
        loadDataFromFile("C:\\Users\\elmiz\\Downloads\\Pharmacy.txt");

        // Initialize the table with the model
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Add the table to a scroll pane (only one JScrollPane should be used)
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(", ")); // Split the line into parts and add as a row directly
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to read the data file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InventoryPage frame = new InventoryPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
