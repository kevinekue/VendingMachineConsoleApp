/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.dao;

import com.ke.vendingmachine.dto.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Owner
 */
public class VendingMachineDaoStub implements VendingMachineDao {

private Map<String, Item> itemsForSale = new HashMap<>();    
private Item onlyItem;


    private List<Item> itemsList = new ArrayList<>();
    
//    public void createTable(){
//        Item item = new Item("1");
//        //item.setItemID("1");
//        item.setItemName("random stuff");
//        item.setItemPrice(new BigDecimal("5"));
//        item.setItemStock(5);
//        itemsForSale.put(item.getItemID(), item);
//        //itemsList.add(item);
//        Item item2 = new Item("2");
//        //item.setItemID("1");
//        item2.setItemName("randomzz stuff");
//        item2.setItemPrice(new BigDecimal("5"));
//        item2.setItemStock(5);
//        itemsForSale.put(item2.getItemID(), item2);
//    }
    
    public VendingMachineDaoStub(){
    onlyItem = new Item("1");
    onlyItem.setItemName("razzle");
    onlyItem.setItemPrice(new BigDecimal("1.50"));
    onlyItem.setItemStock(5);
    itemsList.add(onlyItem);
}
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return itemsList;
    }

    @Override
    public Item getItemInfo(String itemId) throws VendingMachinePersistenceException {
        
        if(itemId.equals(onlyItem.getItemID())){
            return onlyItem;
        } else{
            return null;
        }
    }

    @Override
    public int updateInventory(String itemId) throws FileNotFoundException, VendingMachinePersistenceException {
        
        
        int updatedStock;
        if (onlyItem.getItemStock()>0){
            updatedStock= onlyItem.getItemStock()-1;
            onlyItem.setItemStock(updatedStock);
        }
        else{
            updatedStock = 0;
        }
        return onlyItem.setItemStock(updatedStock);
    }

    
    
}
