/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachine.dao;

import com.ke.vendingmachine.dto.Item;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws VendingMachinePersistenceException {
        List<Item> itemsList = dao.getAllItems();
//        for (Item item : itemsList){
//            dao.
//            //dao.removeStudent(student.getStudentId());
//        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(10, dao.getAllItems().size());
    }

    /**
     * Test of getItemInfo method, of class VendingMachineDao.
     */
    @Test
    public void testGetItemInfo() throws Exception {
        assertNotNull(dao.getItemInfo("8"));
    }
    
    @Test
    public void testGetItemInfoFalse() throws Exception {
        assertNull(dao.getItemInfo("25"));
    }

    /**
     * Test of updateInventory method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        //dao.updateInventory("4");
        assertEquals(17, dao.updateInventory("4"));
    }

//    public class VendingMachineDaoImpl implements VendingMachineDao {
//
//        public List<Item> getAllItems() throws VendingMachinePersistenceException {
//            return null;
//        }
//
//        public Item getItemInfo(String itemId) throws VendingMachinePersistenceException {
//            return null;
//        }
//
//        public int updateInventory(String itemId) throws FileNotFoundException, VendingMachinePersistenceException {
//            return 0;
//        }
//    }
    
}
