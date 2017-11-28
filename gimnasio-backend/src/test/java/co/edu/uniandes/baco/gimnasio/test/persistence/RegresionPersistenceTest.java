package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.RegresionPersistence;
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
public class RegresionPersistenceTest {
    @Inject
    private RegresionPersistence RegresionPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<RegrecionEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegrecionEntity.class.getPackage())
                .addPackage(RegresionPersistence.class.getPackage())
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
        em.createQuery("delete from RegrecionEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            RegrecionEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        RegrecionEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        RegrecionEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
 
    @Test
    public void getRegresionTest() {
        RegrecionEntity entity = data.get(0);
        RegrecionEntity newEntity = RegresionPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }
    
    @Test
    public void subEnititysTest(){
        RegrecionEntity newEntity = create();
        EjercicioInstanciaEntity objetivo=factory.manufacturePojo(EjercicioInstanciaEntity.class);
        TipoMedidaEntity tipoMedidaEntity=factory.manufacturePojo(TipoMedidaEntity.class);
        
        newEntity.setTipoMedida(tipoMedidaEntity);
        newEntity.setEjercicio(objetivo);
        assertEquals(newEntity.getTipoMedida(),tipoMedidaEntity);
        assertEquals(newEntity.getEjercicio(),objetivo);
    }
    
    private void assertEqualsObject(RegrecionEntity a,RegrecionEntity b){
        assertEquals(a.getRegresion(),b.getRegresion(),0);
    }
    
    private RegrecionEntity create(){
        return factory.manufacturePojo(RegrecionEntity.class);
    }
}
