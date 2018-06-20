/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.dao;

import java.io.IOException;

/**
 *
 * @author Owner
 */
public class VendingMachinePersistenceException extends Exception {

    private String ex;

    public VendingMachinePersistenceException(String message) {
        super(message);
    }
    
    public VendingMachinePersistenceException (String message, Throwable cause){
                super(message, cause);
    }

    
}
