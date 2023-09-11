/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;
import dataAccessLayer.DALProducts;
import entityLayer.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin Bolaños V.
 */
public class BLProducts {
     private String _message;

    public String getMessage() {
        return _message;
    }


    public List<Product> listProducts(String condition, String order) throws SQLException, Exception {
        List<Product> result = new ArrayList();
        DALProducts dalproducts;
        try {
            dalproducts = new DALProducts();
            result = dalproducts.listProducts(condition, order);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public Product getProduct(String condition) throws Exception {
        Product product;
        DALProducts dalproducts;
        try {
            dalproducts = new DALProducts();
            product = dalproducts.getProduct(condition);
        } catch (Exception e) {
            throw e;
        }
        return product;
    }

   
}
