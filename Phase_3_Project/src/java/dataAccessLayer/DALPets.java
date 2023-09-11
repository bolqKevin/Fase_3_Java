/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessLayer;

import entityLayer.Pet;
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
public class DALPets {
    private Connection _cnn;
    private String _message;

    public String getMensaje() {
        return _message;
    }
    //Constructor
    public DALPets() throws Exception{
        try{
            _cnn = connectionClass.getConnection();
    
        }catch( Exception ex) { 
            throw ex; 
        }    
    }

    public DALPets(Connection _cnn, String _mensaje) {
        this._cnn = _cnn;
        this._message = _message;
    }    
    public void insertPet(Pet pet) throws SQLException {
    String query = "INSERT INTO PETS (PET_NAME, SPECIES, BREED, BIRTHDATE, GENDER, CLIENTID) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = _cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, pet.getPetName());
        ps.setString(2, pet.getSpecies());
        ps.setString(3, pet.getBreed());
        ps.setString(4, pet.getBirthDate());
        ps.setString(5, pet.getGender());
        ps.setString(6, pet.getClientId());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            _message = "Mascota guardada de forma satisfactoria";
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    } finally {
        _cnn = null;
    }
}

public List<Pet> listPets(String condition, String orderBy) throws SQLException {
    List<Pet> pets = new ArrayList<>();
    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT PETID, PET_NAME, SPECIES, BREED, BIRTHDATE, GENDER, CLIENTID FROM PETS";
        
        if (!condition.isEmpty()) {
            query = String.format("%s where %s",query, condition);
        }
        
        if (!orderBy.isEmpty()) {
            query = String.format("%s ORDER BY %s", query, orderBy);
        }
        
        ResultSet rs = stm.executeQuery(query);
        
        while (rs.next()) {
            int petId = rs.getInt("PETID");
            String petName = rs.getString("PET_NAME");
            String species = rs.getString("SPECIES");
            String breed = rs.getString("BREED");
            String birthDate = rs.getString("BIRTHDATE");
            String gender = rs.getString("GENDER");
            String clientId = rs.getString("CLIENTID");
            
            Pet pet = new Pet(petId, petName, species, breed, birthDate, gender, clientId);
            pets.add(pet);
        }
        
        rs.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }
    
    return pets;
}

public Pet getPet(String condition) throws SQLException {
    Pet pet = new Pet();
    ResultSet rs = null;
    try {
        Statement stm = _cnn.createStatement();
        String query = "SELECT PETID, PET_NAME, SPECIES, BREED, BIRTHDATE, GENDER, CLIENTID FROM PETS";
        if (!condition.isEmpty()) {
            query = String.format("%s WHERE %s", query, condition);
        }
        rs = stm.executeQuery(query);
        if (rs.next()) {
            pet.setPetId(rs.getInt("PETID"));
            pet.setPetName(rs.getString("PET_NAME"));
            pet.setSpecies(rs.getString("SPECIES"));
            pet.setBreed(rs.getString("BREED"));
            pet.setBirthDate(rs.getString("BIRTHDATE"));
            pet.setGender(rs.getString("GENDER"));
            pet.setClientId(rs.getString("CLIENTID"));
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return pet;
}

public int updatePet(Pet pet) throws SQLException {
    int result = 0;
    try {
        String query = "UPDATE PETS SET PET_NAME = ?, SPECIES = ?, BREED = ?, BIRTHDATE = ?, GENDER = ?, CLIENTID = ? WHERE PETID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setString(1, pet.getPetName());
        pstm.setString(2, pet.getSpecies());
        pstm.setString(3, pet.getBreed());
        pstm.setString(4, pet.getBirthDate());
        pstm.setString(5, pet.getGender());
        pstm.setString(6, pet.getClientId());
        pstm.setInt(7, pet.getPetId());
        result = pstm.executeUpdate();
        if (result > 0) {
            _message = "Mascota modificada con éxito";
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }
    return result;
}

public int deletePet(Pet pet) throws SQLException {
    int result = 0;
    try {
        String query = "DELETE FROM PETS WHERE PETID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setInt(1, pet.getPetId());
        result = pstm.executeUpdate();
        if (result > 0) {
            _message = "Mascota eliminada con éxito";
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return result;
}

public boolean verifyPetIdExists(Pet pet) throws SQLException {
    boolean result = false;
    try {
        String query = "SELECT COUNT(*) FROM PETS WHERE PETID = ?";
        PreparedStatement pstm = _cnn.prepareStatement(query);
        pstm.setInt(1, pet.getPetId());
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
