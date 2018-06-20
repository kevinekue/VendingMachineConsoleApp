/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.dao;

import com.ke.vendingmachine.dto.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface VendingMachineDao {
    
    //no add item needed
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItemInfo(String itemId) throws VendingMachinePersistenceException;
    
    int updateInventory (String itemId) throws FileNotFoundException, VendingMachinePersistenceException;
    
//    public BigDecimal potentialItemSale(Item item, BigDecimal userMoney) throws FileNotFoundException, VendingMachinePersistenceException;
}
