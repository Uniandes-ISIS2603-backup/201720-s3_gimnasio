/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;


import co.edu.uniandes.baco.gimnasio.persistence.MedidaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author js.palacios437
 */
@RunWith(Arquillian.class)
public class MedidaPersistenceTest {
   
    @Inject
    private MedidaPersistence medidaPersitence;
    
    @PersistenceContext(unitName = "medidaPU")
    private EntityManager em;
    
    public MedidaPersistenceTest() {
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
     * Test of crete method, of class MedidaPersistence.
     */
    @Test
    public void testCrete() throws Exception {
        fail();
    }

    /**
     * Test of update method, of class MedidaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
         fail();
    }

    /**
     * Test of delete method, of class MedidaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
         fail();
    }

    /**
     * Test of find method, of class MedidaPersistence.
     */
    @Test
    public void testFind() throws Exception {
         fail();
    }
    
}
