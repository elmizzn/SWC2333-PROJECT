import javax.swing.table.DefaultTableModel;

public class InventoryDataModel {
    private static DefaultTableModel model = null;

    public static DefaultTableModel getModel() {
        if (model == null) {
            model = new DefaultTableModel(new String[]{
                "Inventory ID", "Name", "Description", "Category", "Unit Price",
                "Quantity", "Expiry Date", "Batch Number", "Supplier Information",
                "Reorder Level", "Prescription Required", "Storage Conditions"
            }, 0);
            // Load initial data from a file or database
        }
        return model;
    }

    public static void addRow(Object[] rowData) {
        getModel().addRow(rowData);
    }

    public static void updateRow(int row, Object[] rowData) {
        for (int i = 0; i < getModel().getColumnCount(); i++) {
            getModel().setValueAt(rowData[i], row, i);
        }
    }

    public static void removeRow(int row) {
        getModel().removeRow(row);
    }
}
