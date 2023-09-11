/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogicLayer;

import dataAccessLayer.DALPets;
import entityLayer.Pet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Bolaños V.
 */
public class BLPets {
   //Atributos
    private String _message;

    public String getMensaje() {
        return _message;
    }
    
    // Llamar insertar de acceso a datos para mascotas
public void insertPet(Pet pet) throws Exception {
    DALPets adPet;
    try {
        adPet = new DALPets();
        adPet.insertPet(pet);
        _message = adPet.getMensaje();
    } catch (Exception ex) {
        throw ex;
    }
}

// Llamar lista de mascotas
public List<Pet> listPets(String condition, String order) throws SQLException, Exception {
    List<Pet> result = new ArrayList<>();
    DALPets dalPets;
    try {
        dalPets = new DALPets();
        result = dalPets.listPets(condition, order);
    } catch (Exception e) {
        throw e;
    }
    return result;
}

// Llamar a obtener una mascota
public Pet getPet(String condition) throws Exception {
    Pet pet;
    DALPets dalPets;
    try {
        dalPets = new DALPets();
        pet = dalPets.getPet(condition);
    } catch (Exception e) {
        throw e;
    }
    return pet;
}

// Llamar a modificar una mascota
public void updatePet(Pet pet) throws Exception {
    DALPets dalPets;
    try {
        dalPets = new DALPets();
        dalPets.updatePet(pet);
        _message = dalPets.getMensaje();
    } catch (Exception e) {
        throw e;
    }
}

// Llamar a eliminar una mascota
public int deletePet(Pet pet) throws Exception {
    DALPets dalPets;
    int result = -1;
    try {
        dalPets = new DALPets();
        result = dalPets.deletePet(pet);
        _message = dalPets.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return result;
}

// Llamar a verificar si existe el ID de la mascota
public boolean verifyPetIdExists(Pet pet) throws Exception {
    DALPets adPet;
    boolean result = false;
    try {
        adPet = new DALPets();
        result = adPet.verifyPetIdExists(pet);
        _message = adPet.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return result;
}





    
}
