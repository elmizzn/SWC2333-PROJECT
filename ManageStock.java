import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;

public class ManageStock extends JFrame {

    private JPanel contentPane;
    private JTextField[] textFields;
    private JTable table;
    private DefaultTableModel model;
    private String filePath = "C:\\Users\\elmiz\\Downloads\\Pharmacy.txt";

    public ManageStock() {
        setTitle("Pharmacy Management System - Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        String[] columnNames = {
            "ID", "Name", "Description", "Category", "Unit Price", "Quantity",
            "Expiry Date", "Batch Number", "Supplier Info", "Reorder Level",
            "Prescription Required", "Storage Conditions"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        textFields = new JTextField[columnNames.length];

        for (int i = 0; i < columnNames.length; i++) {
            formPanel.add(new JLabel(columnNames[i]));
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
        }
        contentPane.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::addData);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::deleteData);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::updateData);
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this::resetData);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(resetButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                updateTextFieldsFromTable();
            }
        });

        loadDataFromFile();
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(", "));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void writeDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString() + (col < model.getColumnCount() - 1 ? ", " : ""));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addData(ActionEvent e) {
        Vector<String> row = new Vector<>();
        for (JTextField field : textFields) {
            row.add(field.getText());
        }
        model.addRow(row);
        writeDataToFile();
    }

    private void deleteData(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            writeDataToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            for (int i = 0; i < textFields.length; i++) {
                model.setValueAt(textFields[i].getText(), selectedRow, i);
            }
            writeDataToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetData(ActionEvent e) {
        for (JTextField field : textFields) {
            field.setText("");
        }
    }

    private void updateTextFieldsFromTable() {
        int selectedRow = table.getSelectedRow();
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText(table.getValueAt(selectedRow, i).toString());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ManageStock frame = new ManageStock();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
