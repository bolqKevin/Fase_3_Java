/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityLayer;

/**
 *
 * @author Kevin Bolaños Vásquez
 */

public class Client extends Human{
    
    // Attributes
    private String id_client;
    private String address;
    private String notes;
    private boolean toBeDeleted;
    
    // Constructor
public Client(String id_client, String firstName, String LastName, String address, String phone, String email, String notes, boolean toBeDeleted) {
        super(firstName, LastName, phone, email); 
        this.id_client = id_client;
        this.address = address;
        this.notes = notes;
        this.toBeDeleted = toBeDeleted;
    }
    
    public Client(String id_client, String firstName, String LastName, String address, String phone, String email, String notes) {
        super(firstName, LastName, phone, email); 
        this.id_client = id_client;
        this.address = address;
        this.notes = notes;
    }
    
        public Client() {
        this.id_client = "";
        this.address = "";
        this.notes = "";
        this.toBeDeleted = false;
    }
    
    // Get Set
    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }
    
    
}
