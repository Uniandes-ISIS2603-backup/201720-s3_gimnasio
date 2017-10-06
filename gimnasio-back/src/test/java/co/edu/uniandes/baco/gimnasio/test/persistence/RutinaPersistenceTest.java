package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.RutinaPersistence;
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
public class RutinaPersistenceTest {
    @Inject
    private RutinaPersistence RutinaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<RutinaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RutinaEntity.class.getPackage())
                .addPackage(RutinaPersistence.class.getPackage())
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
        em.createQuery("delete from RutinaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            RutinaEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        RutinaEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(UsuarioEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        RutinaEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createRutinaTest() {
        RutinaEntity newEntity = create();
        RutinaEntity result = RutinaPersistence.create(newEntity);
        assertNotNull(result);
        RutinaEntity entity = em.find(RutinaEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geRutinasTest() {
        List<RutinaEntity> list = RutinaPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (RutinaEntity ent : list) {
            boolean found = false;
            for (RutinaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getRutinaTest() {
        RutinaEntity entity = data.get(0);
        RutinaEntity newEntity = RutinaPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteRutinaTest() {
        RutinaEntity entity = data.get(0);
        RutinaPersistence.delete(entity.getId());
        RutinaEntity deleted = em.find(RutinaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateRutinaTest() {
        RutinaEntity entity = data.get(0);
        RutinaEntity newEntity = create();
        newEntity.setId(entity.getId());
        RutinaPersistence.update(newEntity);
        
        RutinaEntity resp = em.find(RutinaEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        RutinaEntity newEntity = create();
        UsuarioEntity usuario=factory.manufacturePojo(UsuarioEntity.class);
        List<EjercicioEntity> ejercicios=new ArrayList<>();
        for(int i=0;i<5;i++)
            ejercicios.add(factory.manufacturePojo(EjercicioEntity.class));
        
        newEntity.setUsuario(usuario);
        newEntity.setEjercicios(ejercicios);
        assertEquals(newEntity.getUsuario(),usuario);
        assertEquals(newEntity.getEjercicios(), ejercicios);
    }
    
    private void assertEqualsObject(RutinaEntity a,RutinaEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFechaFinal()),format.format(b.getFechaFinal()));
        assertEquals(format.format(a.getFechaInicio()),format.format(b.getFechaInicio()));
    }
    
    private RutinaEntity create(){
        return factory.manufacturePojo(RutinaEntity.class);
    }
}
