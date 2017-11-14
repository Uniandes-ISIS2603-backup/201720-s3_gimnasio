package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.ObjetivoLogic;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class BaseLogicTest {

    @Inject
    private ObjetivoLogic baseLogic;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<ObjetivoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ObjetivoEntity.class.getPackage())
                .addPackage(ObjetivoPersistence.class.getPackage())
                .addPackage(ObjetivoLogic.class.getPackage())
                .addPackage(BusinessLogicException.class.getPackage())
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
        em.createQuery("delete from ObjetivoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ObjetivoEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void createObjetivoTest() {
        try {
            ObjetivoEntity newEntity = create();
            ObjetivoEntity result = baseLogic.create(newEntity);
            ObjetivoEntity entity = em.find(ObjetivoEntity.class, result.getId());
            assertEqualsObject(newEntity, entity);
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
    }

    @Test
    public void geObjetivosTest() {
        try {
            List<ObjetivoEntity> list = baseLogic.findAll();
            assertEquals(data.size(), list.size());
            for (ObjetivoEntity ent : list) {
                boolean found = false;
                for (ObjetivoEntity entity : data) {
                    if (ent.getId().equals(entity.getId())) {
                        found = true;
                    }
                }
                assertTrue(found);
            }
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
    }

    @Test
    public void getObjetivoTest() {
        try {
            ObjetivoEntity entity = data.get(0);
            ObjetivoEntity newEntity = baseLogic.find(entity.getId());
            assertEqualsObject(newEntity, entity);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
        try {
            ObjetivoEntity newEntity = baseLogic.find((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    @Test
    public void deleteObjetivoTest() {
        try {
            ObjetivoEntity entity = data.get(0);
            baseLogic.remove(entity.getId());
            ObjetivoEntity deleted = em.find(ObjetivoEntity.class, entity.getId());
            assertNull(deleted);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
        try {
            baseLogic.remove((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    @Test
    public void updateObjetivoTest() {
        try {
            ObjetivoEntity entity = data.get(0);
            ObjetivoEntity newEntity = create();
            newEntity.setId(entity.getId());
            baseLogic.update(newEntity);

            ObjetivoEntity resp = em.find(ObjetivoEntity.class, entity.getId());
            assertEqualsObject(newEntity, resp);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
        try {
            baseLogic.find((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    private void assertEqualsObject(ObjetivoEntity a, ObjetivoEntity b) {
        assertEquals(a.getDescripcion(), b.getDescripcion());
        assertEquals(a.getTipo(), b.getTipo());
    }

    private ObjetivoEntity create() {
        return factory.manufacturePojo(ObjetivoEntity.class);
    }
}
