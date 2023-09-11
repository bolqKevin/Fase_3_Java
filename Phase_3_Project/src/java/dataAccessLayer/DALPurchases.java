/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Kevin Bolaños V.
 */
public class DALPurchases {
    
     private static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=VeterinaryManagement;user=sa;password=sa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
public static int createPurchase(String purchaseDate, int supplierId, DefaultTableModel model) throws SQLException {
    int generatedPurchaseId = -1;
    Connection connection = getConnection();

    try {
        String insertPurchaseQuery = "INSERT INTO PURCHASES (PURCHASEDATE, SUPPLIERID) VALUES (?, ?)";
        PreparedStatement purchaseStatement = connection.prepareStatement(insertPurchaseQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        purchaseStatement.setString(1, purchaseDate);
        purchaseStatement.setInt(2, supplierId);
        purchaseStatement.executeUpdate();

        ResultSet generatedKeys = purchaseStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedPurchaseId = generatedKeys.getInt(1);
        }

        String insertPurchaseDetailQuery = "INSERT INTO PURCHASE_DETAILS (PURCHASEID, PRODUCTID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?)";
        PreparedStatement purchaseDetailStatement = connection.prepareStatement(insertPurchaseDetailQuery);

        for (int row = 0; row < model.getRowCount(); row++) {
            int productId = (int) model.getValueAt(row, 0);
            int quantity = (int) model.getValueAt(row, 5);
            double unitPrice = Double.parseDouble((String) model.getValueAt(row, 6));

            purchaseDetailStatement.setInt(1, generatedPurchaseId);
            purchaseDetailStatement.setInt(2, productId);
            purchaseDetailStatement.setInt(3, quantity);
            purchaseDetailStatement.setDouble(4, unitPrice);
            purchaseDetailStatement.executeUpdate();

            // Update current stock and reorder quantity in the PRODUCTS table
            String updateStockQuery = "UPDATE PRODUCTS SET CURRENTSTOCK = CURRENTSTOCK + ?, REORDERQUANTITY = ? WHERE PRODUCTID = ?";
            PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery);
            updateStockStatement.setInt(1, quantity);
            updateStockStatement.setInt(2, quantity);
            updateStockStatement.setInt(3, productId);
            updateStockStatement.executeUpdate();
            updateStockStatement.close();

            // Update initial stock to match the current stock
            String updateInitialStockQuery = "UPDATE PRODUCTS SET INITIALSTOCK = CURRENTSTOCK WHERE PRODUCTID = ?";
            PreparedStatement updateInitialStockStatement = connection.prepareStatement(updateInitialStockQuery);
            updateInitialStockStatement.setInt(1, productId);
            updateInitialStockStatement.executeUpdate();
            updateInitialStockStatement.close();
        }

        generatedKeys.close();
        purchaseDetailStatement.close();
        purchaseStatement.close();
    } catch (SQLException e) {
        throw e;
    } finally {
        connection.close();
    }

    return generatedPurchaseId;
}
}
