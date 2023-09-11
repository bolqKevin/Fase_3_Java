/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityLayer;

/**
 *
 * @author Kevin Bolaños V.
 */
public class Supplier {

    private int supplierId;
    private String firstNameSupplier;
    private String lastNameSupplier;
    private String contactNameSupplier;
    private String contactNumber;
    private String emailSupplier;
    private String address;
    

        public Supplier(int supplierId, String firstNameSupplier, String lastNameSupplier, String contactNameSupplier, String contactNumber, String emailSupplier, String address) {
        this.supplierId = supplierId;
        this.firstNameSupplier = firstNameSupplier;
        this.lastNameSupplier = lastNameSupplier;
        this.contactNameSupplier = contactNameSupplier;
        this.contactNumber = contactNumber;
        this.emailSupplier = emailSupplier;
        this.address = address;
    }
        public Supplier() {
        this.supplierId = 0;
        this.firstNameSupplier = "";
        this.lastNameSupplier = "";
        this.contactNameSupplier = "";
        this.contactNumber = "";
        this.emailSupplier = "";
        this.address = "";
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getFirstNameSupplier() {
        return firstNameSupplier;
    }

    public void setFirstNameSupplier(String firstNameSupplier) {
        this.firstNameSupplier = firstNameSupplier;
    }

    public String getLastNameSupplier() {
        return lastNameSupplier;
    }

    public void setLastNameSupplier(String lastNameSupplier) {
        this.lastNameSupplier = lastNameSupplier;
    }

    public String getContactNameSupplier() {
        return contactNameSupplier;
    }

    public void setContactNameSupplier(String contactNameSupplier) {
        this.contactNameSupplier = contactNameSupplier;
    }
    
    public String getEmailSupplier() {
        return emailSupplier;
    }

    public void setEmailSupplier(String emailSupplier) {
        this.emailSupplier = emailSupplier;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
