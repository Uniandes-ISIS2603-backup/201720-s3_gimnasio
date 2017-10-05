package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.AtributoDeCalidadPersistence;
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
public class AtributoDeCalidadPersistenceTest {
    @Inject
    private AtributoDeCalidadPersistence AtributoDeCalidadPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<AtributoDeCalidadEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AtributoDeCalidadEntity.class.getPackage())
                .addPackage(AtributoDeCalidadPersistence.class.getPackage())
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
        em.createQuery("delete from AtributoDeCalidadEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AtributoDeCalidadEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        AtributoDeCalidadEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        AtributoDeCalidadEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createAtributoDeCalidadTest() {
        AtributoDeCalidadEntity newEntity = create();
        AtributoDeCalidadEntity result = AtributoDeCalidadPersistence.create(newEntity);
        assertNotNull(result);
        AtributoDeCalidadEntity entity = em.find(AtributoDeCalidadEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geAtributoDeCalidadsTest() {
        List<AtributoDeCalidadEntity> list = AtributoDeCalidadPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (AtributoDeCalidadEntity ent : list) {
            boolean found = false;
            for (AtributoDeCalidadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getAtributoDeCalidadTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        AtributoDeCalidadEntity newEntity = AtributoDeCalidadPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteAtributoDeCalidadTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        AtributoDeCalidadPersistence.delete(entity.getId());
        AtributoDeCalidadEntity deleted = em.find(AtributoDeCalidadEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateAtributoDeCalidadTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        AtributoDeCalidadEntity newEntity = create();
        newEntity.setId(entity.getId());
        AtributoDeCalidadPersistence.update(newEntity);
        
        AtributoDeCalidadEntity resp = em.find(AtributoDeCalidadEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        AtributoDeCalidadEntity newEntity = create();
        ObjetivoEntity objetivo=factory.manufacturePojo(ObjetivoEntity.class);
        TipoMedidaEntity tipoMedidaEntity=factory.manufacturePojo(TipoMedidaEntity.class);
        
        newEntity.setTipoMedida(tipoMedidaEntity);
        newEntity.setObjetivo(objetivo);
        assertEquals(newEntity.getTipoMedida(),tipoMedidaEntity);
        assertEquals(newEntity.getObjetivo(),objetivo);
    }
    
    private void assertEqualsObject(AtributoDeCalidadEntity a,AtributoDeCalidadEntity b){
        assertEquals(a.getRegresion(),b.getRegresion());
    }
    
    private AtributoDeCalidadEntity create(){
        return factory.manufacturePojo(AtributoDeCalidadEntity.class);
    }
}
