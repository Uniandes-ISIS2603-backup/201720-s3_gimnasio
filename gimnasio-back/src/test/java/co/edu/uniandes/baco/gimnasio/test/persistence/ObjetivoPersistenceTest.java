package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
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
public class ObjetivoPersistenceTest {
    @Inject
    private ObjetivoPersistence ObjetivoPersistence;

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
    public void equalsHasTest() {
        ObjetivoEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(UsuarioEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        ObjetivoEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createObjetivoTest() {
        ObjetivoEntity newEntity = create();
        ObjetivoEntity result = ObjetivoPersistence.create(newEntity);
        assertNotNull(result);
        ObjetivoEntity entity = em.find(ObjetivoEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geObjetivosTest() {
        List<ObjetivoEntity> list = ObjetivoPersistence.findAll();
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
        ObjetivoEntity newEntity = ObjetivoPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        ObjetivoPersistence.delete(entity.getId());
        ObjetivoEntity deleted = em.find(ObjetivoEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        ObjetivoEntity newEntity = create();
        newEntity.setId(entity.getId());
        ObjetivoPersistence.update(newEntity);
        
        ObjetivoEntity resp = em.find(ObjetivoEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        ObjetivoEntity newEntity = create();
        List<UsuarioEntity> usuarios=new ArrayList<>();
        for(int i=0;i<5;i++)
            usuarios.add(factory.manufacturePojo(UsuarioEntity.class));
        List<AtributoDeCalidadEntity> atributos=new ArrayList<>();
        for(int i=0;i<5;i++)
            atributos.add(factory.manufacturePojo(AtributoDeCalidadEntity.class));
        
        newEntity.setUsuarios(usuarios);
        newEntity.setAtributos(atributos);
        assertEquals(newEntity.getUsuarios(),usuarios);
        assertEquals(newEntity.getAtributos(), atributos);
    }
    
    private void assertEqualsObject(ObjetivoEntity a,ObjetivoEntity b){
        assertEquals(a.getDescripcion(),b.getDescripcion());
        assertEquals(a.getTipo(),b.getTipo());
    }
    
    private ObjetivoEntity create(){
        return factory.manufacturePojo(ObjetivoEntity.class);
    }
}
