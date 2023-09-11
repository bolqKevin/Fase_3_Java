/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityLayer;

/**
 *
 * @author bolqk
 */
public class Employee extends Human{

    private String id_employee;
    
    public String getId_employee() {
        return id_employee;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }
    
    public Employee(String id_employee, String firstName, String LastName, String phone, String email) {
        super(firstName, LastName, phone, email);
        this.id_employee = id_employee;
    }
      public Employee() {
        this.id_employee = "";
    }      
    public Employee(String id_employee) {
        this.id_employee = id_employee;
    }       
    
}
