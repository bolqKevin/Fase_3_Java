/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityLayer;

/**
 *
 * @author Kevin Bolaños V.
 */
public class Human 
{
    private String firstName;
    private String LastName;
    private String phone;
    private String email;
    
    public Human(String firstName, String LastName, String phone, String email) {
        this.firstName = firstName;
        this.LastName = LastName;
        this.phone = phone;
        this.email = email;
    }
    
    public Human() {
        this.firstName = "";
        this.LastName = "";
        this.phone = "";
        this.email = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
