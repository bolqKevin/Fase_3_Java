/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;

import entityLayer.Employee;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Bolaños V.
 */
public class DALEmployees {
        private Connection _cnn;
    private String _message;

    public String getMensaje() {
        return _message;
    }
    //Constructor
    public DALEmployees() throws Exception{
        try{
            _cnn = connectionClass.getConnection();
        }catch( Exception ex) { 
            throw ex; 
        }    
    }
    
    public boolean verifyEmployeeIdExists(String id) throws SQLException {
    boolean result = false;
    try {
        String query = "SELECT COUNT(*) FROM EMPLOYEES WHERE EMPLOYEEID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setString(1, id);
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
    
public List<Employee> listEmployees(String condicion, String orden) throws SQLException {
    List<Employee> employeeList = new ArrayList<>();

    try {
        Statement stm = _cnn.createStatement();
        String sentencia = "SELECT EMPLOYEEID, FIRSTNAME, LASTNAME, CONTACTNUMBER, EMAIL, BIRTHDATE, STARTDATE, SALESHISTORY FROM EMPLOYEES";

        if (!condicion.equals("")) {
            sentencia = String.format("%s WHERE %s", sentencia, condicion);
        }

        if (!orden.equals("")) {
            sentencia = String.format("%s ORDER BY %s", sentencia, orden);
        }

        ResultSet rs = stm.executeQuery(sentencia);

        while (rs.next()) {
            String id = rs.getString("EMPLOYEEID");
            String firstName = rs.getString("FIRSTNAME");
            String lastName = rs.getString("LASTNAME");
            String contactNumber = rs.getString("CONTACTNUMBER");
            String email = rs.getString("EMAIL");

            Employee employee = new Employee(id, firstName, lastName, contactNumber, email);
            employeeList.add(employee);
        }

        rs.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }

    return employeeList;
}    
}
