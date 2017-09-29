package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioPersistence;
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
public class EjercicioPersistenceTest {

    @Inject
    private EjercicioPersistence ejercicioPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<EjercicioEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EjercicioEntity.class.getPackage())
                .addPackage(EjercicioPersistence.class.getPackage())
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
        em.createQuery("delete from EjercicioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EjercicioEntity entity = factory.manufacturePojo(EjercicioEntity.class);

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
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);
        EjercicioEntity newEntity2 = factory.manufacturePojo(EjercicioEntity.class);
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.equals(newEntity2), newEntity.getId().equals(newEntity2.getId()));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        assertEquals((newEntity.hashCode() == newEntity2.hashCode()), newEntity.getId().equals(newEntity2.getId()));
    }

    @Test
    public void createEjercicioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);
        EjercicioEntity result = ejercicioPersistence.create(newEntity);
        assertNotNull(result);
        EjercicioEntity entity = em.find(EjercicioEntity.class, result.getId());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        assertEquals(newEntity.getDuracion(), entity.getDuracion());
        assertEquals(newEntity.getSeries(), entity.getSeries());
        assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }

    @Test
    public void geEjerciciosTest() {
        List<EjercicioEntity> list = ejercicioPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (EjercicioEntity ent : list) {
            boolean found = false;
            for (EjercicioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        EjercicioEntity newEntity = ejercicioPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        assertEquals(newEntity.getDuracion(), entity.getDuracion());
        assertEquals(newEntity.getSeries(), entity.getSeries());
        assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }

    @Test
    public void deleteEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        ejercicioPersistence.delete(entity.getId());
        EjercicioEntity deleted = em.find(EjercicioEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);
        newEntity.setId(entity.getId());
        ejercicioPersistence.update(newEntity);
        EjercicioEntity resp = em.find(EjercicioEntity.class, entity.getId());
        entity = resp;
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        assertEquals(newEntity.getDuracion(), entity.getDuracion());
        assertEquals(newEntity.getSeries(), entity.getSeries());
        assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }
}
