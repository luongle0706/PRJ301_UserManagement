/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.weapon;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duclu
 */
public class Cart {
    private Map<String, Weapon> cart;

    public Cart() {
    }

    public Cart(Map<String, Weapon> cart) {
        this.cart = cart;
    }

    public Map<String, Weapon> getCart() {
        return cart;
    }

    public void setCart(Map<String, Weapon> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Cart{" + "cart=" + cart + '}';
    }
    
    public boolean add(Weapon weapon) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(weapon.getId())) {
            int currentQuantity = this.cart.get(weapon.getId()).getQuantity();
            weapon.setQuantity(currentQuantity + weapon.getQuantity());
        }
        this.cart.put(weapon.getId(), weapon);
        check = true;
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                check = true;
            }
        }
        } catch (Exception e) {
            
        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                Weapon weapon = this.cart.get(id);
                weapon.setQuantity(quantity);
                this.cart.replace(id, weapon);
                check = true;
            }
        }
        } catch (Exception e) {
            
        }
        return check;
    }
    
    
}
