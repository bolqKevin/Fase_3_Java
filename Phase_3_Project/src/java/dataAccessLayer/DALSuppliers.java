/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;
import entityLayer.Supplier;
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
public class DALSuppliers {
    private Connection _cnn;
private String _message;

public String getMensaje() {
    return _message;
}

public DALSuppliers() throws Exception {
    try {
        _cnn = connectionClass.getConnection();
    } catch (Exception ex) {
        throw ex;
    }
}

public DALSuppliers(Connection _cnn, String _message) {
    this._cnn = _cnn;
    this._message = _message;
}

public void insertSupplier(Supplier supplier) throws SQLException {
    String query = "INSERT INTO SUPPLIERS (FIRSTNAME, LASTNAME, CONTACTNAME, CONTACTNUMBER, EMAIL, ADDRESS_) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = _cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, supplier.getFirstNameSupplier());
        ps.setString(2, supplier.getLastNameSupplier());
        ps.setString(3, supplier.getContactNameSupplier());
        ps.setString(4, supplier.getContactNumber());
        ps.setString(5, supplier.getEmailSupplier());
        ps.setString(6, supplier.getAddress());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            _message = "Proveedor guardado de forma satisfactoria";
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    } finally {
        _cnn = null;
    }
}

public List<Supplier> listSuppliers(String condition, String orderBy) throws SQLException {
    List<Supplier> suppliers = new ArrayList<>();
    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT SUPPLIERID, FIRSTNAME, LASTNAME, CONTACTNAME, CONTACTNUMBER, EMAIL, ADDRESS_ FROM SUPPLIERS";

        if (!condition.isEmpty()) {
            query = String.format("%s WHERE %s", query, condition);
        }

        if (!orderBy.isEmpty()) {
            query = String.format("%s ORDER BY %s", query, orderBy);
        }

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            int supplierId = rs.getInt("SUPPLIERID");
            String firstName = rs.getString("FIRSTNAME");
            String lastName = rs.getString("LASTNAME");
            String contactName = rs.getString("CONTACTNAME");
            String contactNumber = rs.getString("CONTACTNUMBER");
            String email = rs.getString("EMAIL");
            String address = rs.getString("ADDRESS_");

            Supplier supplier = new Supplier(supplierId, firstName, lastName, contactName, contactNumber, email, address);
            suppliers.add(supplier);
        }

        rs.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }

    return suppliers;
}

public Supplier getSupplier(String condition) throws SQLException {
    Supplier supplier = new Supplier();
    ResultSet rs = null;
    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT SUPPLIERID, FIRSTNAME, LASTNAME, CONTACTNAME, CONTACTNUMBER, EMAIL, ADDRESS_ FROM SUPPLIERS";
        if (!condition.isEmpty()) {
            query = String.format("%s WHERE %s", query, condition);
        }
        rs = stm.executeQuery(query);
        if (rs.next()) {
            supplier.setSupplierId(rs.getInt("SUPPLIERID"));
            supplier.setFirstNameSupplier(rs.getString("FIRSTNAME"));
            supplier.setLastNameSupplier(rs.getString("LASTNAME"));
            supplier.setContactNameSupplier(rs.getString("CONTACTNAME"));
            supplier.setContactNumber(rs.getString("CONTACTNUMBER"));
            supplier.setEmailSupplier(rs.getString("EMAIL"));
            supplier.setAddress(rs.getString("ADDRESS_"));
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return supplier;
}

public int updateSupplier(Supplier supplier) throws SQLException {
    int result = 0;
    try {
        String query = "UPDATE SUPPLIERS SET FIRSTNAME = ?, LASTNAME = ?, CONTACTNAME = ?, CONTACTNUMBER = ?, EMAIL = ?, ADDRESS_ = ? WHERE SUPPLIERID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setString(1, supplier.getFirstNameSupplier());
        pstm.setString(2, supplier.getLastNameSupplier());
        pstm.setString(3, supplier.getContactNameSupplier());
        pstm.setString(4, supplier.getContactNumber());
        pstm.setString(5, supplier.getEmailSupplier());
        pstm.setString(6, supplier.getAddress());
        pstm.setInt(7, supplier.getSupplierId());
        result = pstm.executeUpdate();
        if (result > 0) {
            _message = "Proveedor modificado con éxito";
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }
    return result;
}

public int deleteSupplier(Supplier supplier) throws SQLException {
    int result = 0;
    try {
        String query = "DELETE FROM SUPPLIERS WHERE SUPPLIERID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setInt(1, supplier.getSupplierId());
        result = pstm.executeUpdate();
        if (result > 0) {
            _message = "Proveedor eliminado con éxito";
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return result;
}

public boolean verifySupplierIdExists(Supplier supplier) throws SQLException {
    boolean result = false;
    try {
        String query = "SELECT COUNT(*) FROM SUPPLIERS WHERE SUPPLIERID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setInt(1, supplier.getSupplierId());
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            result = count > 0;
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return result;
}
}
