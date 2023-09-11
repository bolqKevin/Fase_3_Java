/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;

import dataAccessLayer.DALClients;
import dataAccessLayer.DALEmployees;
import entityLayer.Client;
import entityLayer.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Bolaños V.
 */
public class BLEmployees {
    private String _message;

    public String getMessage() {
        return _message;
    }
           
    public boolean verifyEmployeeIdExists (String id) throws Exception{
        DALEmployees adlEmployees;
        boolean result = false;
        try {
            adlEmployees = new DALEmployees();
            result = adlEmployees.verifyEmployeeIdExists(id);
            _message=adlEmployees.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }
    
       public List<Employee> listEmployee (String condicion, String orden) throws SQLException, Exception{
        List<Employee> resultado = new ArrayList();
        DALEmployees dalclients;
        try {
            dalclients = new DALEmployees();
            resultado = dalclients.listEmployees(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
