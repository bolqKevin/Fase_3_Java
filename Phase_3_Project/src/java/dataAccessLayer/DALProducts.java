/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;

import entityLayer.Pet;
import entityLayer.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Bolaños V.
 */
public class DALProducts {
    
    private Connection _cnn;
    private String _message;

    public String getMensaje() {
        return _message;
    }
    
        public DALProducts() throws Exception{
        try{
           _cnn = connectionClass.getConnection();
        }catch( Exception ex) { 
            throw ex; 
        }    
    }

public List<Product> listProducts(String condition, String order) throws SQLException {
    List<Product> products = new ArrayList();

    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT PRODUCTID, PRODUCTNAME, DESCRIPTION_, PRICE, INITIALSTOCK, CURRENTSTOCK, REORDERQUANTITY FROM PRODUCTS";

        if (!condition.equals("")) {
            query = String.format("%s WHERE %s", query, condition);
        }

        if (!order.equals("")) {
            query = String.format("%s ORDER BY %s", query, order);
        }

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("PRODUCTID");
            String description = rs.getString("DESCRIPTION_");
            double price = rs.getDouble("PRICE");
            int currentStock = rs.getInt("CURRENTSTOCK");

            Product product = new Product(id, description, price, currentStock);
            products.add(product);
        }

        rs.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }

    return products;
}

public Product getProduct(String condition) throws SQLException {
    Product product = new Product();

    ResultSet rs = null;
    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT PRODUCTID, DESCRIPTION_, PRICE, CURRENTSTOCK FROM PRODUCTS";
        if (!condition.equals("")) {
            query = String.format("%s WHERE %s", query, condition);
        }
        rs = stm.executeQuery(query);
        if (rs.next()) {
            product.setProductId(rs.getInt(1));
            product.setDescription(rs.getString(2));
            product.setPrice(rs.getDouble(3));
            product.setCurrentStock(rs.getInt(4));
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return product;
}

}