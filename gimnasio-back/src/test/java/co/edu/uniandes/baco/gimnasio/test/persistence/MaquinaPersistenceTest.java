package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.MaquinaPersistence;
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
public class MaquinaPersistenceTest {
    @Inject
    private MaquinaPersistence MaquinaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<MaquinaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MaquinaEntity.class.getPackage())
                .addPackage(MaquinaPersistence.class.getPackage())
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
        em.createQuery("delete from MaquinaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MaquinaEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        MaquinaEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        MaquinaEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createMaquinaTest() {
        MaquinaEntity newEntity = create();
        MaquinaEntity result = MaquinaPersistence.create(newEntity);
        assertNotNull(result);
        MaquinaEntity entity = em.find(MaquinaEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geMaquinasTest() {
        List<MaquinaEntity> list = MaquinaPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (MaquinaEntity ent : list) {
            boolean found = false;
            for (MaquinaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getMaquinaTest() {
        MaquinaEntity entity = data.get(0);
        MaquinaEntity newEntity = MaquinaPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteMaquinaTest() {
        MaquinaEntity entity = data.get(0);
        MaquinaPersistence.delete(entity.getId());
        MaquinaEntity deleted = em.find(MaquinaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateMaquinaTest() {
        MaquinaEntity entity = data.get(0);
        MaquinaEntity newEntity = create();
        newEntity.setId(entity.getId());
        MaquinaPersistence.update(newEntity);
        
        MaquinaEntity resp = em.find(MaquinaEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        MaquinaEntity newEntity = create();
     
         List<TipoMedidaEntity> medidas=new ArrayList<>();
        for(int i=0;i<5;i++)
            medidas.add(factory.manufacturePojo(TipoMedidaEntity.class));
        
        newEntity.setTipoMedida(medidas);
        assertEquals(newEntity.getTipoMedida(), medidas);
    }
    
    private void assertEqualsObject(MaquinaEntity a,MaquinaEntity b){
        assertEquals(a.getInformacion(), b.getInformacion());
    }
    
    private MaquinaEntity create(){
        return factory.manufacturePojo(MaquinaEntity.class);
    }
}
