/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.ui;

import com.ke.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Owner
 */
public class VendingMachineView {
    private final UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public void displayWelcome(){
        io.print("== Welcome to the Vending Machine ==");
    }
    
    public void displayExitMachine(){
        io.print("== Thank you for shopping with us today! ==");
    }
    
    public String userMoney(){
        return io.readString("Please enter a $ amount: ");
    }
    
    public String userItemChoice(){
        return io.readString("Enter the ID# of your preferred item: ");
    }
    
    public int userAppChoice(){
        return io.readInt("Would you like to make a purchase today? Enter '1' to enter the application, or '2' to exit the marketplace", 1, 2);
    }
    
    public void displayAllItems(List<Item> itemID){
        for (Item currentItem : itemID){
            io.print(currentItem.getItemID() + " "+ currentItem.getItemName()+" $"+ currentItem.getItemPrice()+ " "+currentItem.getItemStock());
        }
    }
    
    public void displayItem(Item currentItem){
         io.print(currentItem.getItemID() + " "+ currentItem.getItemName()+" $"+ currentItem.getItemPrice()+ " "+currentItem.getItemStock());     
    }
    
    public void displayOutOfStock(){
        io.print("Sorry, we're out of stock  for this item");
    }
   
    public void displayInsufficientFunds(){
        io.print(" Insufficient Funds! \n ");
    }
    
//    public void displayPurchaseSuccessMessage(String itemID, BigDecimal change){
//        io.print("You've purchased one "+ itemID+" and your change is "+ change +" \n");
//    }
    
    public void displayPurchaseSuccessMessage(String itemID){
        io.print("You've purchased one Item ID: " + itemID+ " \n");
    }

    public void displayErrormessage (String errorMsg){
        io.print("=== Error ===");
        io.print(errorMsg);
    }
    
    public void displayChange( BigDecimal [] change){
        io.print("You're getting back: ");
        io.print(change[0].toPlainString()+ " quarters" );
        
        
        io.print(change[1].toPlainString()+" dimes" );
        
        
        io.print(change[2].toPlainString() + " nickels, and ");
        
        io.print( change[3].toPlainString()+" pennies \n");
        
        //BigDecimal [] changeArray = {quarters[0], dimes[0], nickels[0], pennies[0]};
    }
}
