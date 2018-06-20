/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.service;

import com.ke.vendingmachine.dao.VendingMachineAuditDao;
import com.ke.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.ke.vendingmachine.dao.VendingMachineDao;
import com.ke.vendingmachine.dao.VendingMachineDaoStub;
import com.ke.vendingmachine.dao.VendingMachinePersistenceException;
import com.ke.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class VendingMachineServiceLayerTest {
    private VendingMachineServiceLayer service;
    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStub();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        
        service = new VendingMachineServiceImpl (dao, auditDao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makePurchaseCheck method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testMakePurchaseCheck() throws Exception {
        //assertNonNull(service.makePurchaseCheck(onlyItem));
        
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayer.
     * @throws com.ke.vendingmachine.dao.VendingMachinePersistenceException
     */
    @Test
    public void testGetChangeTrue() throws VendingMachinePersistenceException {
        BigDecimal userMoney = new BigDecimal ("2.00");
        BigDecimal itemCost = new BigDecimal ("1.5");
        BigDecimal [] array = {new BigDecimal ("2"), new BigDecimal ("0"),new BigDecimal ("0"),new BigDecimal ("0")};
        Assert.assertArrayEquals(array,service.getChange(userMoney, itemCost));
    }
    
    public void testGetChangeFalse() throws VendingMachinePersistenceException {
        BigDecimal userMoney = new BigDecimal ("1.00");
        BigDecimal itemCost = new BigDecimal ("1.5");
        BigDecimal [] array = {new BigDecimal ("2"), new BigDecimal ("0"),new BigDecimal ("0"),new BigDecimal ("0")};
        Assert.assertNotEquals(array,service.getChange(userMoney, itemCost));
    }

    
//    @Test
//    public void testUpdatedInventory() {
//        assertEquals(4, service.updatedInventory("1"));
//    }

    
    
    
}
