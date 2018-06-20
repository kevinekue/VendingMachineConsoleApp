/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.service;

import com.ke.vendingmachine.dao.VendingMachineAuditDao;
import com.ke.vendingmachine.dao.VendingMachineDao;
import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.dto.Item;
import com.ke.vendingmachine.ui.UserIO;
import com.ke.vendingmachine.ui.UserIOConsoleImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class VendingMachineServiceImpl implements VendingMachineServiceLayer {
    UserIO io = new UserIOConsoleImpl();
    Item item;
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    //USE TO CALCULATE CHANGE.

    /**
     * 
     */
    
//    public get
    @Override
    public BigDecimal [] getChange(BigDecimal userMoney, BigDecimal itemPrice) throws WrongDollarAmountException {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal ("0.10");
        BigDecimal nickel = new BigDecimal ("0.05");
        BigDecimal penny = new BigDecimal ("0.01");
        BigDecimal change = userMoney.subtract(itemPrice);
        if (change.compareTo(BigDecimal.ZERO) == -1){
            throw new WrongDollarAmountException("Insufficient funds.");
        }
        else {           
        //io.print("Your change is "+ change);
        
        BigDecimal[] quarters = change.divideAndRemainder(quarter);
        change = quarters [1];
        BigDecimal [] dimes = change.divideAndRemainder(dime);
        change = dimes[1];
        BigDecimal [] nickels = change.divideAndRemainder(nickel);
        change = nickels [1];
        BigDecimal [] pennies = change.divideAndRemainder(penny);        
        BigDecimal [] changeArray = {quarters[0], dimes[0], nickels[0], pennies[0]};
        return changeArray;}
    }

    @Override
    public void makePurchaseCheck(Item item) throws WrongItemIDException, VendingMachinePersistenceException {
        if (dao.getItemInfo(item.getItemID())== null){
            throw new WrongItemIDException ("Error: Wrong item ID. ItemID "+ item.getItemID()+" isn't recognized.");
        }
        
    }

    @Override
    public Item getItem(String itemChoice) throws VendingMachinePersistenceException {
        return dao.getItemInfo(itemChoice);
    }


    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    /**
     *
     * @param item
     * @return
     * @throws VendingMachinePersistenceException
     */
    @Override
    public boolean itemStockCheck(Item item) throws VendingMachinePersistenceException { 
           
        int stock =  item.getItemStock();
      
        if (stock <= 0 ){
            
            throw new VendingMachinePersistenceException ("We're out of stock for this item, please enter another item ID. ");
            
        } else{
            return true;
        }
    }    
    

    
    @Override
    public void sellItem(Item item){
        
        try {
            dao.updateInventory(item.getItemID());
        } catch (VendingMachinePersistenceException | FileNotFoundException ex) {
            try {
                dao.getAllItems();
            } catch (VendingMachinePersistenceException ex1) {
                //Keep on getting error messages. look into it.
            }
        }
        

        
    }
}
