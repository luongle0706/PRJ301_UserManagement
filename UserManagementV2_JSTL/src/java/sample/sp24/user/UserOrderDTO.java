/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 *
 * @author duclu
 */
public class UserOrderDTO {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
    private LocalDateTime now = LocalDateTime.now();  
    private String orderID;
    private String userID;
    private String orderDate;
    private double total;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String email;
    private String paymentMethod;

    public UserOrderDTO() {
        this.orderID = "";
        this.userID = "";
        this.orderDate = "";
        this.total = 0;
        this.fullName = "";
        this.phoneNumber = "";
        this.address = "";
        this.email = "";
        this.paymentMethod = "";
    }

    public UserOrderDTO(String userID, double total, String fullName, String phoneNumber, String address, String email, String paymentMethod) {
        this.orderID = orderIDGenerator(10);
        this.userID = userID;
        this.orderDate = dtf.format(now);
        this.total = total;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.paymentMethod = paymentMethod;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public final String orderIDGenerator(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) //filter unicode characters
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
