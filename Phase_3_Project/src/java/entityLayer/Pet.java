/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityLayer;

/**
 *
 * @author Kevin Bolaños V.
 */
public class Pet {
    
   // Attributes
    private int petId;
    private String petName;
    private String species;
    private String breed;
    private String birthDate;
    private String gender;
    private String clientId;
    
    //Constructors
        public Pet(int petId, String petName, String species, String breed, String birthDate, String gender, String clientId) {
        this.petId = petId;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.gender = gender;
        this.clientId = clientId;
    }
    
        public Pet() {
        this.petId = 0;
        this.petName = "";
        this.species = "";
        this.breed = "";
        this.birthDate = "";
        this.gender = "";
        this.clientId = "";
    }
        
    //Get Set
 public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }        
}
