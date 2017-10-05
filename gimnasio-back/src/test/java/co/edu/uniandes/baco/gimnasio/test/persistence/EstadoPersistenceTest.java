package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
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
public class EstadoPersistenceTest {
    @Inject
    private EstadoPersistence EstadoPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EstadoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoEntity.class.getPackage())
                .addPackage(EstadoPersistence.class.getPackage())
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
        em.createQuery("delete from EstadoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EstadoEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        EstadoEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        EstadoEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createEstadoTest() {
        EstadoEntity newEntity = create();
        EstadoEntity result = EstadoPersistence.create(newEntity);
        assertNotNull(result);
        EstadoEntity entity = em.find(EstadoEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geEstadosTest() {
        List<EstadoEntity> list = EstadoPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (EstadoEntity ent : list) {
            boolean found = false;
            for (EstadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getEstadoTest() {
        EstadoEntity entity = data.get(0);
        EstadoEntity newEntity = EstadoPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteEstadoTest() {
        EstadoEntity entity = data.get(0);
        EstadoPersistence.delete(entity.getId());
        EstadoEntity deleted = em.find(EstadoEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateEstadoTest() {
        EstadoEntity entity = data.get(0);
        EstadoEntity newEntity = create();
        newEntity.setId(entity.getId());
        EstadoPersistence.update(newEntity);
        
        EstadoEntity resp = em.find(EstadoEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        EstadoEntity newEntity = create();
        UsuarioEntity usuarioEntity=factory.manufacturePojo(UsuarioEntity.class);
         List<MedidaEntity> medidas=new ArrayList<>();
        for(int i=0;i<5;i++)
            medidas.add(factory.manufacturePojo(MedidaEntity.class));
        
        newEntity.setUsuario(usuarioEntity);
        newEntity.setMedidas(medidas);
        assertEquals(newEntity.getUsuario(), usuarioEntity);
        assertEquals(newEntity.getMedidas(), medidas);
    }
    
    private void assertEqualsObject(EstadoEntity a,EstadoEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFecha()),format.format(b.getFecha()));
    }
    
    private EstadoEntity create(){
        return factory.manufacturePojo(EstadoEntity.class);
    }
}
