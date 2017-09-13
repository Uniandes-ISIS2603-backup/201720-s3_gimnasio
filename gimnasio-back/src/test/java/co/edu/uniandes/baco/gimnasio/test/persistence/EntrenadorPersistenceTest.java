/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author m.sicard10
 */
@RunWith(Arquillian.class)
public class EntrenadorPersistenceTest {
    
        /**
     * Inyección de la dependencia a la clase EntrenadorPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EntrenadorPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
      /**
     *
     */
    private List<EntrenadorEntity> data = new ArrayList<EntrenadorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive a = ShrinkWrap.create(JavaArchive.class)
                .addPackage(EntrenadorEntity.class.getPackage())
                .addPackage(EntrenadorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        return a;
    }
    


   
    
    
    private void clearData() {
        em.createQuery("delete from EntrenadorEntity").executeUpdate();
    }

    

    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EntrenadorEntity entity = factory.manufacturePojo(EntrenadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    public EntrenadorPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class EntrenadorPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        
    PodamFactory factory = new PodamFactoryImpl();
    EntrenadorEntity newEntity = factory.manufacturePojo(EntrenadorEntity.class);
    EntrenadorEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    EntrenadorEntity entity = em.find(EntrenadorEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class EntrenadorPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    EntrenadorEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    EntrenadorEntity newEntity = factory.manufacturePojo(EntrenadorEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    EntrenadorEntity resp = em.find(EntrenadorEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class EntrenadorPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    EntrenadorEntity entity = data.get(0);
    persistence.delete(entity.getId());
    EntrenadorEntity deleted = em.find(EntrenadorEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class EntrenadorPersistence.
     */
    @Test
    public void testFind() throws Exception {
    EntrenadorEntity entity = data.get(0);
    EntrenadorEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class EntrenadorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<EntrenadorEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (EntrenadorEntity ent : list) {
        boolean found = false;
        for (EntrenadorEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }
    
}
