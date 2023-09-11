/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;
import entityLayer.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin Bolaños V.
 */
public class DALSales {
    
    private static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=VeterinaryManagement;user=sa;password=sa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
public static int createSale(String saleDate, String clientID, String employeeID, String discount, List<Product> productosSeleccionados) throws SQLException {
    int generatedSaleID = -1;
    Connection connection = getConnection();

    try {
        // Insert into SALES 
        String insertSaleQuery = "INSERT INTO SALES (SALEDATE, CLIENTID, EMPLOYEEID, DISCOUNT) VALUES (?, ?, ?, ?)";
        PreparedStatement saleStatement = connection.prepareStatement(insertSaleQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        saleStatement.setString(1, saleDate);
        saleStatement.setString(2, clientID);
        saleStatement.setString(3, employeeID);
        saleStatement.setString(4, discount);
        saleStatement.executeUpdate();

        ResultSet generatedKeys = saleStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedSaleID = generatedKeys.getInt(1);
        }

        // Insert into SALE_DETAIL table
        String insertSaleDetailQuery = "INSERT INTO SALE_DETAIL (SALEID, PRODUCTID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?)";
        PreparedStatement saleDetailStatement = connection.prepareStatement(insertSaleDetailQuery);

        for (Product producto : productosSeleccionados) {
            int productID = producto.getProductId();
            int quantity = producto.getCurrentStock();
            double unitPrice = producto.getPrice();

            saleDetailStatement.setInt(1, generatedSaleID);
            saleDetailStatement.setInt(2, productID);
            saleDetailStatement.setInt(3, quantity);
            saleDetailStatement.setDouble(4, unitPrice);
            saleDetailStatement.executeUpdate();

            // Actualizar existencias en la tabla PRODUCTS
            String updateStockQuery = "UPDATE PRODUCTS SET CURRENTSTOCK = CURRENTSTOCK - ? WHERE PRODUCTID = ?";
            PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery);
            updateStockStatement.setInt(1, quantity);
            updateStockStatement.setInt(2, productID);
            updateStockStatement.executeUpdate();
            updateStockStatement.close();
        }

        generatedKeys.close();
        saleDetailStatement.close();
        saleStatement.close();
    } catch (SQLException e) {
        throw e;
    } finally {
        connection.close();
    }

    return generatedSaleID;
}
}

