/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine;

import com.ke.vendingmachine.controller.VendingMachineController;
import com.ke.vendingmachine.dao.VendingMachineAuditDao;
import com.ke.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.ke.vendingmachine.dao.VendingMachineDao;
import com.ke.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.service.VendingMachineServiceImpl;
import com.ke.vendingmachine.service.VendingMachineServiceLayer;
import com.ke.vendingmachine.service.WrongDollarAmountException;
import com.ke.vendingmachine.ui.UserIO;
import com.ke.vendingmachine.ui.UserIOConsoleImpl;
import com.ke.vendingmachine.ui.VendingMachineView;
import java.io.FileNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Owner
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, VendingMachinePersistenceException, WrongDollarAmountException {
//        UserIO myIo = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIo);
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer myService = new VendingMachineServiceImpl(myDao, myAuditDao);
////        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//// Not Made Yet        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao);
//        
//        VendingMachineController controller = new VendingMachineController(myService, myView);
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller  = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
    
}
