/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.controller;

import com.ke.vendingmachine.dao.VendingMachineDao;
import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.dto.Item;
import com.ke.vendingmachine.service.VendingMachineServiceLayer;
import com.ke.vendingmachine.service.WrongDollarAmountException;
import com.ke.vendingmachine.ui.UserIO;
import com.ke.vendingmachine.ui.UserIOConsoleImpl;
import com.ke.vendingmachine.ui.VendingMachineView;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class VendingMachineController {

    UserIO io = new UserIOConsoleImpl();
    //VendingMachineDao dao;
    VendingMachineView view;
    VendingMachineServiceLayer service;
    String userItemChoice;
    int userAppChoice;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws WrongDollarAmountException {
        userAppChoice = view.userAppChoice();
        try {
            if (userAppChoice == 1) {
                do {
                    view.displayWelcome();
                    try {
                        listItems();
                    } catch (FileNotFoundException ex) {
                    }
                    BigDecimal userMoney = new BigDecimal(view.userMoney());
                    userItemChoice = view.userItemChoice();
                    Item item = service.getItem(userItemChoice);
                    viewItemInfo(userItemChoice);

                    try {
                        service.itemStockCheck(item);

                    } catch (VendingMachinePersistenceException ex) {
                        userItemChoice = view.userItemChoice();
                        item = service.getItem(userItemChoice);
                        

                    }
                    potentialItemSale(item, userMoney);

                    try {
                        listItems();
                    } catch (FileNotFoundException ex) {
                        view.displayErrormessage(ex.getMessage());
                    }
                    userAppChoice = view.userAppChoice();
                } while (userAppChoice == 1);
            } else {
                view.displayExitMachine();
            }
        } catch (VendingMachinePersistenceException e) {
            view.displayErrormessage(e.getMessage());
        }

    }

    public void listItems() throws FileNotFoundException, VendingMachinePersistenceException {

        view.displayAllItems(service.getAllItems());
    }

    private void viewItemInfo(String str) throws VendingMachinePersistenceException {

        view.displayItem(service.getItem(str));

    }

    public void potentialItemSale(Item item, BigDecimal userMoney) throws WrongDollarAmountException {
        try {
            BigDecimal[] change = service.getChange(userMoney, item.getItemPrice());

            view.displayPurchaseSuccessMessage(item.getItemID());
            view.displayChange(change);
            service.sellItem(item);

        } catch (WrongDollarAmountException ex) {

            
            view.displayInsufficientFunds();
        }

    }
}

/**
 * Add a sellItem to the service layer??? it will handle the updated inventory and the audit entry.
 */
