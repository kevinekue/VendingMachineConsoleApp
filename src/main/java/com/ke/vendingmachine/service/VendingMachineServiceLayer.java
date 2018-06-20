/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.service;

import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface VendingMachineServiceLayer {
    
//    /**
//     *
//     * @param userMoney: The amount of money entered by the User
//     * @param itemPrice: The price of the item that the User is trying to purchase
//     * @return Change: the difference between the amount of money entered by the user and the item's price.
//     */
//    BigDecimal getChange (BigDecimal userMoney, BigDecimal itemPrice);
    
    //List<Item> getAllItems () throw VendingMachinePersistenceException;

    /**
     *
     * @param item
     * @throws WrongItemIDException
     * @throws WrongDollarAmountException
     * @throws com.ke.vendingmachine.dao.VendingMachinePersistenceException
     */
    void makePurchaseCheck (Item item) throws WrongItemIDException, VendingMachinePersistenceException;
    
    /**
     *
     * @param userMoney
     * @param itemPrice
     */
    BigDecimal [] getChange (BigDecimal userMoney, BigDecimal itemPrice) throws WrongDollarAmountException;
    
    
    Item getItem (String itemChoice) throws VendingMachinePersistenceException;
    
//    int updatedInventory(String itemID); //throws VendingMachinePersistenceException;
    public List<Item> getAllItems () throws VendingMachinePersistenceException;
    
    boolean itemStockCheck (Item item)  throws VendingMachinePersistenceException;
    
//    public void soldItem(Item item);
    
    public void sellItem(Item item);
    
}
