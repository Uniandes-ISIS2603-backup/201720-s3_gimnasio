package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioInstanciaPersistence;
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
public class EjercicioInstanciaPersistenceTest {

    @Inject
    private EjercicioInstanciaPersistence EjercicioInstanciaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EjercicioInstanciaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EjercicioInstanciaEntity.class.getPackage())
                .addPackage(EjercicioInstanciaPersistence.class.getPackage())
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
        em.createQuery("delete from EjercicioInstanciaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EjercicioInstanciaEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void equalsHasTest() {
        EjercicioInstanciaEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());

        BaseEntity tipo = (BaseEntity) factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo = null;
        assertFalse(newEntity.equals(tipo));

        EjercicioInstanciaEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));

        newEntity2.setId(newEntity.getId() + 1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(), newEntity2.hashCode());
    }

    @Test
    public void createEjercicioInstanciaTest() {
        EjercicioInstanciaEntity newEntity = create();
        EjercicioInstanciaEntity result = EjercicioInstanciaPersistence.create(newEntity);
        assertNotNull(result);
        EjercicioInstanciaEntity entity = em.find(EjercicioInstanciaEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geEjercicioInstanciasTest() {
        List<EjercicioInstanciaEntity> list = EjercicioInstanciaPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (EjercicioInstanciaEntity ent : list) {
            boolean found = false;
            for (EjercicioInstanciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getEjercicioInstanciaTest() {
        EjercicioInstanciaEntity entity = data.get(0);
        EjercicioInstanciaEntity newEntity = EjercicioInstanciaPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteEjercicioInstanciaTest() {
        EjercicioInstanciaEntity entity = data.get(0);
        EjercicioInstanciaPersistence.delete(entity.getId());
        EjercicioInstanciaEntity deleted = em.find(EjercicioInstanciaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateEjercicioInstanciaTest() {
        EjercicioInstanciaEntity entity = data.get(0);
        EjercicioInstanciaEntity newEntity = create();
        newEntity.setId(entity.getId());
        EjercicioInstanciaPersistence.update(newEntity);

        EjercicioInstanciaEntity resp = em.find(EjercicioInstanciaEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }

    @Test
    public void subEnititysTest() {
        EjercicioInstanciaEntity newEntity = create();
        RutinaEntity rutina = factory.manufacturePojo(RutinaEntity.class);
        EjercicioEntity ejercicio = factory.manufacturePojo(EjercicioEntity.class);
        List<EjercicioHechoEntity> loHecho = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            loHecho.add(factory.manufacturePojo(EjercicioHechoEntity.class));
        }

        newEntity.setEjercicio(ejercicio);
        newEntity.setRutina(rutina);
        newEntity.setEjerciciosHechos(loHecho);
        assertEquals(newEntity.getEjercicio(), ejercicio);
        assertEquals(newEntity.getRutina(), rutina);
        assertEquals(newEntity.getEjerciciosHechos(), loHecho);
    }

    private void assertEqualsObject(EjercicioInstanciaEntity a, EjercicioInstanciaEntity b) {
        assertEquals(a.getCumplimiento(), b.getCumplimiento());
        assertEquals(a.getDuracion(), b.getDuracion());
        assertEquals(a.getEfectividad(), b.getEfectividad());
        assertEquals(a.getRepeticionesPorParticion(), b.getRepeticionesPorParticion());
        assertEquals(a.getSeries(), b.getSeries());
        assertEquals(a.getTamanioParticiones(), b.getTamanioParticiones());
    }

    private EjercicioInstanciaEntity create() {
        return factory.manufacturePojo(EjercicioInstanciaEntity.class);
    }
}
