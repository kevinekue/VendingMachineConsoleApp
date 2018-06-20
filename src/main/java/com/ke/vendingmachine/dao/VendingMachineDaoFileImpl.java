/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.dao;

import com.ke.vendingmachine.dto.Item;
import com.ke.vendingmachine.ui.UserIO;
import com.ke.vendingmachine.ui.UserIOConsoleImpl;
import com.ke.vendingmachine.ui.VendingMachineView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Owner
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    //public static final VendingMachineView = view;
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    private static VendingMachineView view;
    Item item;
    private Map<String, Item> itemsForSale = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    //format.setParseBigDecimal(true);

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            //format.setParseBigDecimal(true);
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException ex) {
            throw new VendingMachinePersistenceException("-_- Could not load roster data into memory.", ex);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemStock(Integer.parseInt(currentTokens[3]));

            itemsForSale.put(currentItem.getItemID(), currentItem);
        }
        scanner.close();
    }

    private void writeInventory() throws VendingMachinePersistenceException, FileNotFoundException, IOException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save Stock data.", e);
        }

        List<Item> itemsForSale = this.getAllItems();
        for (Item currentItem : itemsForSale) {
            // Try to update itemStock before writing it to the file after a purchase
            out.println(currentItem.getItemID() + DELIMITER + currentItem.getItemName() + DELIMITER + currentItem.getItemPrice() + DELIMITER + currentItem.getItemStock());

            out.flush();
        }
        out.close();
    }

    /**
     *
     * @return @throws FileNotFoundException
     */
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {

        loadInventory();
        //No message yet.

        return new ArrayList<Item>(itemsForSale.values());
    }

    @Override
    public Item getItemInfo(String itemId) throws VendingMachinePersistenceException {

        return itemsForSale.get(itemId);

    }

    @Override
    public int updateInventory(String itemID) throws VendingMachinePersistenceException {

        Item item = itemsForSale.get(itemID);
        int updatedStock;
        if (item.getItemStock() > 0) {
            updatedStock = item.getItemStock() - 1;
            item.setItemStock(updatedStock);
        } else {
            updatedStock = 0;
        }

        try {
            writeInventory();
        } catch (IOException | VendingMachinePersistenceException ex) {
            throw new VendingMachinePersistenceException("couldn't write the updated inventory to the audit log", ex);
        }

        return item.setItemStock(updatedStock);
    }

}
