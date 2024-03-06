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
public class CheckoutError {
    private String productIDError;
    private String nameError;
    private String priceError;
    private String quantityError;

    public CheckoutError() {
        this.productIDError = "";
        this.nameError = "";
        this.priceError = "";
        this.quantityError = "";
    }

    public CheckoutError(String productIDError, String nameError, String priceError, String quantityError) {
        this.productIDError = productIDError;
        this.nameError = nameError;
        this.priceError = priceError;
        this.quantityError = quantityError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }
    
}
