/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityLayer;

/**
 *
 * @author Kevin Bolaños V.
 */
public class Product {
    
    private int productId;
    private String description;
    private double price;
    private int currentStock;
    
        public Product() {
        this.productId = 0;
        this.description = "";
        this.price = 0.00;
        this.currentStock = 0;
    }

    public Product(int productID, String descripcion, double precioUnitario, int cantidad) {
         this.productId = productID;
         this.description = descripcion;
         this.price = precioUnitario;
         this.currentStock = cantidad;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String descroption) {
        this.description = descroption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

}
