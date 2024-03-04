/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.weapon;

/**
 *
 * @author duclu
 */
public class OrderDetailDTO {
    private String productID;
    private String orderID;
    private double price;
    private int quantity;

    public OrderDetailDTO() {
        this.productID = "";
        this.orderID = "";
        this.price = 0;
        this.quantity = 0;
    }

    public OrderDetailDTO(String productID, String orderID, double price, int quantity) {
        this.productID = productID;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
