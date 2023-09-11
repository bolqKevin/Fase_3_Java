/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccessLayer;

import config.Config;
import entityLayer.Client;
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
 * @author Kevin Bolaños Vásquez
 */
public class DALClients {
    private Connection _cnn;
    private String _message;

    public String getMensaje() {
        return _message;
    }
    //Constructor
    public DALClients() throws Exception{
        try{
            String url = Config.getConnectionString();
              _cnn=DriverManager.getConnection(url);
              
        }catch( Exception ex) { 
            throw ex; 
        }    
    }

    public DALClients(Connection _cnn, String _mensaje) {
        this._cnn = _cnn;
        this._message = _message;
    }
    
    public void insertClient(Client client) throws Exception{ //Insertar en la tabla d Clientes 
    String query = "INSERT INTO Clients (clientId, firstName, lastName, address, phone, email, notes, to_be_deleted) VALUES (?, ?, ?, ? ,? ,? ,?, ?)";
    try {
        PreparedStatement ps = _cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, client.getId_client());  
        ps.setString(2, client.getFirstName());
        ps.setString(3, client.getLastName());
        ps.setString(4, client.getAddress());
        ps.setString(5, client.getPhone());
        ps.setString(6, client.getEmail());
        ps.setString(7, client.getNotes());
        client.setToBeDeleted(false);
        ps.setBoolean(8, client.isToBeDeleted());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next()){
            _message = "Cliente guardado de forma satisfactoria";
        }
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
    }
    finally{
        _cnn = null;
    }
    }//Insertar Cliente
    
    
        //Listar clientes
    
 public List<Client> listClients(String condicion, String orden) throws SQLException {
    List<Client> clientList = new ArrayList<>();

    try {
        Statement stm = _cnn.createStatement();
        String sentencia = "SELECT CLIENTID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, EMAIL, NOTES FROM CLIENTS";

        // Agregar una condición WHERE para excluir los registros con TO_BE_DELETED = 1
        if (!condicion.equals("")) {
            sentencia = String.format("%s WHERE %s AND TO_BE_DELETED = 0", sentencia, condicion);
        } else {
            sentencia = String.format("%s WHERE TO_BE_DELETED = 0", sentencia);
        }

        if (!orden.equals("")) {
            sentencia = String.format("%s ORDER BY %s", sentencia, orden);
        }

        ResultSet rs = stm.executeQuery(sentencia);

        while (rs.next()) {
            String id = rs.getString("CLIENTID");
            String name = rs.getString("FIRSTNAME");
            String lastname = rs.getString("LASTNAME");
            String address = rs.getString("ADDRESS");
            String phone = rs.getString("PHONE");
            String email = rs.getString("EMAIL");
            String notes = rs.getString("NOTES");

            Client client_ = new Client(id, name, lastname, address, phone, email, notes);
            clientList.add(client_);
        }

        rs.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }

    return clientList;
}
        
 public void MarkClientForDeletion(String clientId) throws SQLException {
    try {
        // Verificar si el cliente existe antes de marcarlo para la eliminación
        PreparedStatement checkStatement = _cnn.prepareStatement("SELECT COUNT(*) FROM CLIENTS WHERE CLIENTID = ?");
        checkStatement.setString(1, clientId);
        ResultSet checkResult = checkStatement.executeQuery();

        if (checkResult.next()) {
            // El cliente existe, por lo que podemos marcarlo para su eliminación
            PreparedStatement updateStatement = _cnn.prepareStatement("UPDATE CLIENTS SET TO_BE_DELETED = 1 WHERE CLIENTID = ?");
            updateStatement.setString(1, clientId);
            updateStatement.executeUpdate();
            updateStatement.close();
        } else {
           _message = "Error inesperado";
        }

        checkResult.close();
        checkStatement.close();
    } catch (Exception ex) {
        _message = "El Cliente tiene citas o facturas pendientes";
        throw ex;
    } finally {
        _cnn = null;
    }
} 
        
   //Obtener cliente
        public Client GetClient(String condition) throws SQLException{ //Obetenr Cliente con condicion where
        Client client = new Client();
        
        ResultSet rs=null;
        try {
            Statement stm= _cnn.createStatement();
            String query = "SELECT CLIENTID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, EMAIL, NOTES FROM CLIENTS";
            if(!condition.equals("")){
                query = String.format("%s where %s",query, condition);
            }
            rs= stm.executeQuery(query);
            if(rs.next()){
                client.setId_client(rs.getString(1));
                client.setFirstName(rs.getString(2));
                client.setLastName(rs.getString(3));
                client.setAddress(rs.getString(4));
                client.setPhone(rs.getString(5));
                client.setEmail(rs.getString(6));
                client.setNotes(rs.getString(7));
            }
        } catch (Exception e) {throw e;}
        finally {_cnn = null;}
        return client;
    }
     
        public int UpdateClient (Client client) throws SQLException{ //Update en Clientes
        int resultado = 0;
        try {
        String sentencia = "UPDATE CLIENTS SET FIRSTNAME = ?, LASTNAME= ?, ADDRESS= ?, PHONE= ?, EMAIL= ?, NOTES= ? WHERE CLIENTID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(sentencia);
        pstm.setString(1, client.getFirstName());
        pstm.setString(2, client.getLastName());
        pstm.setString(3, client.getAddress());
        pstm.setString(4, client.getPhone());
        pstm.setString(5, client.getEmail());
        pstm.setString(6, client.getNotes());
        pstm.setString(7, client.getId_client());
        resultado = pstm.executeUpdate();
        if(resultado>0){
          _message = "Cliente Modificado con exito";
        }
        } catch (Exception ex) {
            throw ex;
        }
       finally {_cnn = null;}
 
        return resultado;
    }
        
       public int DeleteClient(Client client) throws SQLException{ //Delete en Clientes
        int result = 0;
        try {
            String query = "DELETE FROM CLIENTS WHERE CLIENTID = ?";
            PreparedStatement pstm = _cnn.prepareStatement(query);
            pstm.setString(1, client.getId_client());
            result =pstm.executeUpdate();
            if(result>0){
            _message = "Cliente eliminado con exito";
            }
        } catch (Exception e) {
            throw e;
        }
        finally{_cnn=null;}
        return result;
    }
        
        public boolean verifyClientIdExists(Client client) throws SQLException{ //Verificar dato existente (Con entidad)
        boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM CLIENTS WHERE CLIENTID = ?";
            PreparedStatement pstm = _cnn.prepareStatement(query);
            pstm.setString(1, client.getId_client());
            ResultSet resultSet =pstm.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = count > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        finally{_cnn=null;}
        
            if(result){
                return true;
            }else{
                return false;
            }
    }
        
        public boolean verifyClientIdExists(String id) throws SQLException{ //Verificar dato existente 
        boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM CLIENTS WHERE CLIENTID = ?";
            PreparedStatement pstm = _cnn.prepareStatement(query);
            pstm.setString(1, id);
            ResultSet resultSet =pstm.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = count > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        finally{_cnn=null;}
        
            if(result){
                return true;
            }else{
                return false;
            }
    }
       public boolean verifyClientEmail(String email) throws SQLException{ //Verificar dato existente 
        boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM CLIENTS WHERE EMAIL = ?";
            PreparedStatement pstm = _cnn.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet resultSet =pstm.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = count > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        finally{_cnn=null;}
        
            if(result){
                return true;
            }else{
                return false;
            }
    }
        
        public boolean verifyClientPhone(String phone) throws SQLException{ //Verificar dato existente 
        boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM CLIENTS WHERE PHONE = ?";
            PreparedStatement pstm = _cnn.prepareStatement(query);
            pstm.setString(1, phone);
            ResultSet resultSet =pstm.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = count > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        finally{_cnn=null;}
        
            if(result){
                return true;
            }else{
                return false;
            }
    }
        
        
}
