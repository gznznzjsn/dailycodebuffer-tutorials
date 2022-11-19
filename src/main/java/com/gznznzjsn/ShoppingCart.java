package com.gznznzjsn;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    //logging
    //Authentication & Authorization
    //Sanitize the Data
    public void checkout(String status){
        System.out.println("Checkout method from shopping cart was called");
    }

    public int quantity(){
        return 2;
    }
}
