package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
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

@RunWith(Arquillian.class)
public class EjercicioPersistenceTest {
    @Inject
    private EjercicioPersistence ejercicioPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

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
        for (int i = 0; i < 3; i++) {
            EjercicioEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void createEjercicioTest() {
        EjercicioEntity newEntity = create();
        EjercicioEntity result = ejercicioPersistence.create(newEntity);
        assertNotNull(result);
        EjercicioEntity entity = em.find(EjercicioEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
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
        assertEqualsObject(newEntity, entity);
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
        EjercicioEntity newEntity = create();
        newEntity.setId(entity.getId());
        ejercicioPersistence.update(newEntity);
        
        EjercicioEntity resp = em.find(EjercicioEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void equalsHasTest() {
        EjercicioEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        EjercicioEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void subEnititysTest(){
        EjercicioEntity newEntity = create();
        RutinaEntity rutina=factory.manufacturePojo(RutinaEntity.class);
        List<ObjetivoEntity> objetivos=new ArrayList<>();
        for(int i=0;i<5;i++)
            objetivos.add(factory.manufacturePojo(ObjetivoEntity.class));
        List<MaquinaEntity> maquinas=new ArrayList<>();
        for(int i=0;i<5;i++)
            maquinas.add(factory.manufacturePojo(MaquinaEntity.class));
        
        newEntity.setRutina(rutina);
        newEntity.setObjetivosEjercicio(objetivos);
        newEntity.setMaquinas(maquinas);
        assertEquals(newEntity.getRutina(), rutina);
        assertEquals(newEntity.getObjetivosEjercicio(),objetivos);
        assertEquals(newEntity.getMaquinas(), maquinas);
    }
    
    private void assertEqualsObject(EjercicioEntity a,EjercicioEntity b){
        assertEquals(a.getExplicacion(), b.getExplicacion());
        assertEquals(a.getDuracion(), b.getDuracion());
        assertEquals(a.getSeries(), b.getSeries());
        assertEquals(a.getTamanioParticiones(), b.getTamanioParticiones());
        assertEquals(a.getRepeticionesPorParticion(), b.getRepeticionesPorParticion());
        assertEquals(a.getTipo(), b.getTipo());
    }
    
    private EjercicioEntity create(){
        return factory.manufacturePojo(EjercicioEntity.class);
    }
}
