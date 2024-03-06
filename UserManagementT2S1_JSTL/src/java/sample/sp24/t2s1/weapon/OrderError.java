/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t2s1.weapon;

/**
 *
 * @author duclu
 */
public class OrderError {
    private String orderIDError;
    private String userIDError;
    private String orderDateError;
    private String totalError;
    private String fullNameError;
    private String phoneNumberError;
    private String addressError;
    private String emailError;
    private String paymentMethodError;

    public OrderError() {
        this.orderIDError = "";
        this.userIDError = "";
        this.orderDateError = "";
        this.totalError = "";
        this.fullNameError = "";
        this.phoneNumberError = "";
        this.addressError = "";
        this.emailError = "";
        this.paymentMethodError = "";
    }

    public OrderError(String orderIDError, String userIDError, String orderDateError, String totalError, String fullNameError, String phoneNumberError, String addressError, String emailError, String paymentMethodError) {
        this.orderIDError = orderIDError;
        this.userIDError = userIDError;
        this.orderDateError = orderDateError;
        this.totalError = totalError;
        this.fullNameError = fullNameError;
        this.phoneNumberError = phoneNumberError;
        this.addressError = addressError;
        this.emailError = emailError;
        this.paymentMethodError = paymentMethodError;
    }

    public String getOrderIDError() {
        return orderIDError;
    }

    public void setOrderIDError(String orderIDError) {
        this.orderIDError = orderIDError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getOrderDateError() {
        return orderDateError;
    }

    public void setOrderDateError(String orderDateError) {
        this.orderDateError = orderDateError;
    }

    public String getTotalError() {
        return totalError;
    }

    public void setTotalError(String totalError) {
        this.totalError = totalError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPaymentMethodError() {
        return paymentMethodError;
    }

    public void setPaymentMethodError(String paymentMethodError) {
        this.paymentMethodError = paymentMethodError;
    }
    
    public boolean checkStringLength(String attribute, int lower, int higher) {
        boolean check = true;
        if (attribute.length() < lower || attribute.length() > higher) {
            check = false;
        }
        return check;
    }
}
