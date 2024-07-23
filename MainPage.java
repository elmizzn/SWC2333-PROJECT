import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class MainPage extends JFrame {

    private JPanel contentPane;
    private String username;
    private DefaultTableModel dataModel;  // Declare the table model

    public MainPage(String username) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.username = username;
        setTitle("Pharmacy Management System - Main");
        setBounds(100, 100, 850, 650);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 230, 140));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        setupUI();
        initializeDataModel(); // Initialize and load data
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 221, 613);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\elmiz\\Downloads\\Users128.png"));
        lblNewLabel.setBounds(47, 23, 133, 128);
        panel.add(lblNewLabel);

        JLabel helloLabel = new JLabel("Hi, " + username);
        helloLabel.setBounds(35, 165, 138, 28);
        helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(helloLabel);

        JButton inventoryViewButton = new JButton("Inventory View");
        inventoryViewButton.setBounds(47, 220, 123, 28);
        inventoryViewButton.addActionListener(e -> openInventoryView());
        panel.add(inventoryViewButton);

        JButton manageStockButton = new JButton("Manage Stock");
        manageStockButton.setBounds(47, 301, 123, 28);
        manageStockButton.addActionListener(e -> openManageStock());
        panel.add(manageStockButton);

        JButton printReportButton = new JButton("Print Report");
        printReportButton.setBounds(45, 373, 123, 28);
        printReportButton.addActionListener(this::printReport);
        panel.add(printReportButton);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBounds(45, 446, 123, 28);
        logOutButton.addActionListener(e -> logOut());
        panel.add(logOutButton);
    }

    private void initializeDataModel() {
        String[] columnNames = {"ID", "Name", "Description", "Category", "Unit Price", "Quantity",
                                 "Expiry Date", "Batch Number", "Supplier Info", "Reorder Level",
                                 "Prescription Required", "Storage Conditions"};
        dataModel = new DefaultTableModel(columnNames, 0);
        loadDataModel();
    }

    private void loadDataModel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\elmiz\\Downloads\\Pharmacy.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataModel.addRow(line.split(", "));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printReport(ActionEvent event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\elmiz\\Downloads\\report.txt"))) {
            for (int i = 0; i < dataModel.getRowCount(); i++) {
                for (int j = 0; j < dataModel.getColumnCount(); j++) {
                    writer.write(dataModel.getValueAt(i, j).toString() + (j < dataModel.getColumnCount() - 1 ? ", " : ""));
                }
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Report saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openInventoryView() {
        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.setVisible(true);
    }

    private void openManageStock() {
        ManageStock manageStock = new ManageStock();
        manageStock.setVisible(true);
    }

    private void logOut() {
        dispose();
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainPage frame = new MainPage("Guest");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
