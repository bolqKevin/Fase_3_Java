/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;
import dataAccessLayer.DALSuppliers;
import entityLayer.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin Bolaños V.
 */
public class BLSupplier {
    private String _message;

public String getMensaje() {
    return _message;
}

public void insertSupplier(Supplier supplier) throws Exception {
    DALSuppliers adSupplier;
    try {
        adSupplier = new DALSuppliers();
        adSupplier.insertSupplier(supplier);
        _message = adSupplier.getMensaje();
    } catch (Exception ex) {
        throw ex;
    }
}

public List<Supplier> listSuppliers(String condition, String order) throws SQLException, Exception {
    List<Supplier> result = new ArrayList<>();
    DALSuppliers dalSuppliers;
    try {
        dalSuppliers = new DALSuppliers();
        result = dalSuppliers.listSuppliers(condition, order);
    } catch (Exception e) {
        throw e;
    }
    return result;
}

public Supplier getSupplier(String condition) throws Exception {
    Supplier supplier;
    DALSuppliers dalSuppliers;
    try {
        dalSuppliers = new DALSuppliers();
        supplier = dalSuppliers.getSupplier(condition);
    } catch (Exception e) {
        throw e;
    }
    return supplier;
}

public void updateSupplier(Supplier supplier) throws Exception {
    DALSuppliers dalSuppliers;
    try {
        dalSuppliers = new DALSuppliers();
        dalSuppliers.updateSupplier(supplier);
        _message = dalSuppliers.getMensaje();
    } catch (Exception e) {
        throw e;
    }
}

public int deleteSupplier(Supplier supplier) throws Exception {
    DALSuppliers dalSuppliers;
    int result = -1;
    try {
        dalSuppliers = new DALSuppliers();
        result = dalSuppliers.deleteSupplier(supplier);
        _message = dalSuppliers.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return result;
}

public boolean verifySupplierIdExists(Supplier supplier) throws Exception {
    DALSuppliers adSupplier;
    boolean result = false;
    try {
        adSupplier = new DALSuppliers();
        result = adSupplier.verifySupplierIdExists(supplier);
        _message = adSupplier.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return result;
}
}
