package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.GimnasioPersistence;
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
public class GimnasioPersistenceTest {
    @Inject
    private GimnasioPersistence GimnasioPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<GimnasioEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GimnasioEntity.class.getPackage())
                .addPackage(GimnasioPersistence.class.getPackage())
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
        em.createQuery("delete from GimnasioEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            GimnasioEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        GimnasioEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        GimnasioEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
      
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createGimnasioTest() {
        GimnasioEntity newEntity = create();
        GimnasioEntity result = GimnasioPersistence.create(newEntity);
        assertNotNull(result);
        GimnasioEntity entity = em.find(GimnasioEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geGimnasiosTest() {
        List<GimnasioEntity> list = GimnasioPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (GimnasioEntity ent : list) {
            boolean found = false;
            for (GimnasioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getGimnasioTest() {
        GimnasioEntity entity = data.get(0);
        GimnasioEntity newEntity = GimnasioPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteGimnasioTest() {
        GimnasioEntity entity = data.get(0);
        GimnasioPersistence.delete(entity.getId());
        GimnasioEntity deleted = em.find(GimnasioEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateGimnasioTest() {
        GimnasioEntity entity = data.get(0);
        GimnasioEntity newEntity = create();
        newEntity.setId(entity.getId());
        GimnasioPersistence.update(newEntity);
        
        GimnasioEntity resp = em.find(GimnasioEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    private void assertEqualsObject(GimnasioEntity a,GimnasioEntity b){
        assertEquals(a.getDuenio(),b.getDuenio());
        assertEquals(a.getName(), b.getName());
        assertEquals(a.getNit(), b.getNit());
    }
    
    private GimnasioEntity create(){
        return factory.manufacturePojo(GimnasioEntity.class);
    }
}
