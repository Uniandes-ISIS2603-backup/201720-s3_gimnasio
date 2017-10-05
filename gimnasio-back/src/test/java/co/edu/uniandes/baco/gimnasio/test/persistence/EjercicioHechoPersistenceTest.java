package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class EjercicioHechoPersistenceTest {
    @Inject
    private EjercicioHechoPersistence EjercicioHechoPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EjercicioHechoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EjercicioHechoEntity.class.getPackage())
                .addPackage(EjercicioHechoPersistence.class.getPackage())
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
        em.createQuery("delete from EjercicioHechoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EjercicioHechoEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        EjercicioHechoEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        EjercicioHechoEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createEjercicioHechoTest() {
        EjercicioHechoEntity newEntity = create();
        EjercicioHechoEntity result = EjercicioHechoPersistence.create(newEntity);
        assertNotNull(result);
        EjercicioHechoEntity entity = em.find(EjercicioHechoEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geEjercicioHechosTest() {
        List<EjercicioHechoEntity> list = EjercicioHechoPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (EjercicioHechoEntity ent : list) {
            boolean found = false;
            for (EjercicioHechoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getEjercicioHechoTest() {
        EjercicioHechoEntity entity = data.get(0);
        EjercicioHechoEntity newEntity = EjercicioHechoPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteEjercicioHechoTest() {
        EjercicioHechoEntity entity = data.get(0);
        EjercicioHechoPersistence.delete(entity.getId());
        EjercicioHechoEntity deleted = em.find(EjercicioHechoEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateEjercicioHechoTest() {
        EjercicioHechoEntity entity = data.get(0);
        EjercicioHechoEntity newEntity = create();
        newEntity.setId(entity.getId());
        EjercicioHechoPersistence.update(newEntity);
        
        EjercicioHechoEntity resp = em.find(EjercicioHechoEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
    }
    
    private void assertEqualsObject(EjercicioHechoEntity a,EjercicioHechoEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFechaInicio()),format.format(b.getFechaInicio()));
        assertEquals(a.getSeriesReales(), b.getSeriesReales());
    }
    
    private EjercicioHechoEntity create(){
        return factory.manufacturePojo(EjercicioHechoEntity.class);
    }
}
