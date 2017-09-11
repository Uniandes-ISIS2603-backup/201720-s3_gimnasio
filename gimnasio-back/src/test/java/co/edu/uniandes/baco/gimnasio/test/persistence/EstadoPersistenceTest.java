/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.MedidaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.palacios437
 */

@RunWith(Arquillian.class)
public class EstadoPersistenceTest {
    
    @Inject
    private EstadoPersistence estadoPersistence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final List<EstadoEntity> data = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoEntity.class.getPackage())
                .addPackage(EstadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    @SuppressWarnings("CallToPrintStackTrace")
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from EstadoEntity").executeUpdate();
    }
    
    private void insertData() {
      PodamFactory factory = new PodamFactoryImpl();
       for (int i = 0; i < 3; i++) {
            EstadoEntity entity = factory.manufacturePojo(EstadoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
       }
    public EstadoPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    

    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class EstadoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class EstadoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class EstadoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class EstadoPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }
    
}
