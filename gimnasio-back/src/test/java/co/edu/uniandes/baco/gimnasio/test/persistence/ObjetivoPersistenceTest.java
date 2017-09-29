package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
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

/**
 *
 */
@RunWith(Arquillian.class)
public class ObjetivoPersistenceTest {

    @Inject
    private ObjetivoPersistence persistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<ObjetivoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ObjetivoEntity.class.getPackage())
                .addPackage(ObjetivoPersistence.class.getPackage())
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
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ObjetivoEntity entity = factory.manufacturePojo(ObjetivoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void equalsHasTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ObjetivoEntity newEntity = factory.manufacturePojo(ObjetivoEntity.class);
        ObjetivoEntity newEntity2 = factory.manufacturePojo(ObjetivoEntity.class);
        TipoMedidaEntity tipo=factory.manufacturePojo(TipoMedidaEntity.class);
        assertFalse(newEntity.equals(tipo));
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.equals(newEntity2), newEntity.getId().equals(newEntity2.getId()));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        assertEquals((newEntity.hashCode() == newEntity2.hashCode()), newEntity.getId().equals(newEntity2.getId()));
    }
    
    @Test
    public void findByTipo(){
        ObjetivoEntity entity = data.get(0);
        ObjetivoEntity newEntity = persistence.findByTipo(entity.getTipo());
        assertNotNull(newEntity);
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    @Test
    public void createObjetivoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ObjetivoEntity newEntity = factory.manufacturePojo(ObjetivoEntity.class);
        ObjetivoEntity result = persistence.create(newEntity);
        assertNotNull(result);
        ObjetivoEntity entity = em.find(ObjetivoEntity.class, result.getId());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    @Test
    public void geObjetivosTest() {
        List<ObjetivoEntity> list = persistence.findAll();
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
    }

    @Test
    public void getObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        ObjetivoEntity newEntity = persistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    @Test
    public void deleteObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ObjetivoEntity deleted = em.find(ObjetivoEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ObjetivoEntity newEntity = factory.manufacturePojo(ObjetivoEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        ObjetivoEntity resp = em.find(ObjetivoEntity.class, entity.getId());
        assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        assertEquals(newEntity.getTipo(), resp.getTipo());
    }
}
