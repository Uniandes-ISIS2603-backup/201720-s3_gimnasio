package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.MedicionMaquinaPersistence;
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
public class MedicionMaquinaPersistenceTest {
    @Inject
    private MedicionMaquinaPersistence MedicionMaquinaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<MedicionMaquinaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicionMaquinaEntity.class.getPackage())
                .addPackage(MedicionMaquinaPersistence.class.getPackage())
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
        em.createQuery("delete from MedicionMaquinaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedicionMaquinaEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        MedicionMaquinaEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        MedicionMaquinaEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createMedicionMaquinaTest() {
        MedicionMaquinaEntity newEntity = create();
        MedicionMaquinaEntity result = MedicionMaquinaPersistence.create(newEntity);
        assertNotNull(result);
        MedicionMaquinaEntity entity = em.find(MedicionMaquinaEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geMedicionMaquinasTest() {
        List<MedicionMaquinaEntity> list = MedicionMaquinaPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (MedicionMaquinaEntity ent : list) {
            boolean found = false;
            for (MedicionMaquinaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getMedicionMaquinaTest() {
        MedicionMaquinaEntity entity = data.get(0);
        MedicionMaquinaEntity newEntity = MedicionMaquinaPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteMedicionMaquinaTest() {
        MedicionMaquinaEntity entity = data.get(0);
        MedicionMaquinaPersistence.delete(entity.getId());
        MedicionMaquinaEntity deleted = em.find(MedicionMaquinaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateMedicionMaquinaTest() {
        MedicionMaquinaEntity entity = data.get(0);
        MedicionMaquinaEntity newEntity = create();
        newEntity.setId(entity.getId());
        MedicionMaquinaPersistence.update(newEntity);
        
        MedicionMaquinaEntity resp = em.find(MedicionMaquinaEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
    }
    
    private void assertEqualsObject(MedicionMaquinaEntity a,MedicionMaquinaEntity b){
        assertEquals(a.getMedicionManquina(), b.getMedicionManquina());
    }
    
    private MedicionMaquinaEntity create(){
        return factory.manufacturePojo(MedicionMaquinaEntity.class);
    }
}
