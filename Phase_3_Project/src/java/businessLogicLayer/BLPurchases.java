/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;

import dataAccessLayer.DALPurchases;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
/**
 *
 * @author Kevin Bolaños V.
 */
public class BLPurchases {
    
      public int createPurchase(String purchaseDate, int supplierId, DefaultTableModel model) throws SQLException {
        try {
            return DALPurchases.createPurchase(purchaseDate, supplierId, model);
        } catch (SQLException e) {
            throw e;
        }
    }
}
