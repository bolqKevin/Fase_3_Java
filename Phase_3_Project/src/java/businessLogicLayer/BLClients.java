/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;

import dataAccessLayer.DALClients;
import entityLayer.Client;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Bolaños V.
 */
public class BLClients {
       //Atributos
    private String _message;

    public String getMessage() {
        return _message;
    }
    //Llamar insertar de acceso a datos
    public void insertClient(Client client) throws Exception{
        DALClients adcliente;
        try {
            adcliente = new DALClients();
            adcliente.insertClient(client);
            _message=adcliente.getMensaje();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
        public List<Client> listClients (String condicion, String orden) throws SQLException, Exception{
        List<Client> resultado = new ArrayList();
        DALClients dalclients;
        try {
            dalclients = new DALClients();
            resultado = dalclients.listClients(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Obtener Cliente
    public Client GetClient(String condition) throws Exception{
        Client client;
        DALClients dalclients;
        try {
            dalclients=new DALClients();
            client=dalclients.GetClient(condition);
        } catch (Exception e) {
            throw e;
        }
    return client;
    }
    
    //Modificar cliente
        public void UpdateClient (Client client) throws Exception{
        DALClients dalclients;
        try {
            dalclients = new DALClients();
            dalclients.UpdateClient(client);
            _message=dalclients.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    }
        
    //Eliminar cliente
    public int DeleteClient (Client client) throws Exception{
        DALClients dalclients;
        int result = -1;
        try {
            dalclients = new DALClients();
            result = dalclients.DeleteClient(client);
            _message=dalclients.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }
    public void MarkClientForDeletion (String clientId) throws Exception{
        DALClients adcliente;
        boolean result = false;
        try {
            adcliente = new DALClients();
            adcliente.MarkClientForDeletion(clientId);
            _message=adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    }  
    public boolean verifyClientIdExists (Client client) throws Exception{
        DALClients adcliente;
        boolean result = false;
        try {
            adcliente = new DALClients();
            result = adcliente.verifyClientIdExists(client);
            _message=adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }
     public boolean verifyClientEmail (String email) throws Exception{
        DALClients adcliente;
        boolean result = false;
        try {
            adcliente = new DALClients();
            result = adcliente.verifyClientEmail(email);
            _message=adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }  
        public boolean verifyClientIdExists (String id) throws Exception{
        DALClients adcliente;
        boolean result = false;
        try {
            adcliente = new DALClients();
            result = adcliente.verifyClientIdExists(id);
            _message=adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }
        
        public boolean verifyClientPhone (String id) throws Exception{
        DALClients adcliente;
        boolean result = false;
        try {
            adcliente = new DALClients();
            result = adcliente.verifyClientPhone(id);
            _message=adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
    return result;
    }
        
}
