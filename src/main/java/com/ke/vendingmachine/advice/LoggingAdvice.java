/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.advice;

import com.ke.vendingmachine.dao.VendingMachineAuditDao;
import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.service.WrongDollarAmountException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Owner
 */
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    
    public LoggingAdvice(VendingMachineAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object [] args = jp.getArgs();
        
        String auditEntry = jp.getSignature().getName()+" : ";
        for(Object currentArg : args){
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e){
            System.err.println("Error: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void afterThrowingStuff(JoinPoint jp , VendingMachinePersistenceException ex ){
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + ex;
        for (Object currentArg : args) {
            auditEntry += currentArg;
            
            try{
                auditDao.writeAuditEntry("EXCEPTION:" + auditEntry );
            } catch(Exception e){
                System.err.println("Give it another shot..maybe");
            }
        }
    }
    
     public void afterThrowingStuff2(JoinPoint jp , WrongDollarAmountException ex1 ){
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + ex1;
        for (Object currentArg : args) {
            auditEntry += currentArg;
            
            try{
                auditDao.writeAuditEntry("EXCEPTION:" + auditEntry );
            } catch(Exception e){
                System.err.println("Give it another shot..maybe");
            }
        }
    }
            
}
