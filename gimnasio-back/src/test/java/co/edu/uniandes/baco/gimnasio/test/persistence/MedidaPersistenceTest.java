/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;


import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
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
import org.junit.Assert;
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
public class MedidaPersistenceTest {
   
    @Inject
    private MedidaPersistence medidaPersitence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final List<MedidaEntity> data = new ArrayList<>();
    
        @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedidaEntity.class.getPackage())
                .addPackage(MedidaPersistence.class.getPackage())
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
        em.createQuery("delete from MedidaEntity").executeUpdate();
    }
    
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MedidaEntity entity = factory.manufacturePojo(MedidaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    public MedidaPersistenceTest() {
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
     * Test of crete method, of class MedidaPersistence.
     */
    @Test
    public void createMedidaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedidaEntity newEntity = factory.manufacturePojo(MedidaEntity.class);
        MedidaEntity result = medidaPersitence.crete(newEntity);

       Assert.assertNotNull(result);

        MedidaEntity entity = em.find(MedidaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getMedida(), entity.getMedida());
    }
    
    /**
     * get estado
     */
    @Test
    public void getMedida()
    {
    MedidaEntity entity = data.get(0);
    MedidaEntity newEntity = medidaPersitence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * actualiza un Medida
     */
    @Test
    public void updateMedida()
    {
    MedidaEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    MedidaEntity newEntity = factory.manufacturePojo(MedidaEntity.class);

    newEntity.setId(entity.getId());

    medidaPersitence.update(newEntity);

    MedidaEntity resp = em.find(MedidaEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getId(), resp.getId());  
    }
    /**
     * borra una rutina 
     */
    @Test
    public void deleteMedida()
    {
    MedidaEntity entity = data.get(0);
    medidaPersitence.delete(entity.getId());
    MedidaEntity deleted = em.find(MedidaEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
}
