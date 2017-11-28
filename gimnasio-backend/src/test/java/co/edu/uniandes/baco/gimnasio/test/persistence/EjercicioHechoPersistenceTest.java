package co.edu.uniandes.baco.gimnasio.test.persistence;
import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
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
        EjercicioInstanciaEntity b=factory.manufacturePojo(EjercicioInstanciaEntity.class);
        em.persist(b);
        for (int i = 0; i < 3; i++) {
            EjercicioHechoEntity entity = create();
            entity.setEjercicios(b);
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        EjercicioHechoEntity a=data.get(0);
        assertEqualsObject(a, EjercicioHechoPersistence.find(a.getId()));
        
        EjercicioHechoEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        assertEqualsObject(newEntity, newEntity);
        
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
    public void subEnititysTest(){
        EjercicioHechoEntity nuevo=create();
        EjercicioInstanciaEntity ejercicioInstanciaEntity=factory.manufacturePojo(EjercicioInstanciaEntity.class);
        List<MedicionMaquinaEntity> medicionMaquinaEntitys=new LinkedList<>();
        for(int i=0;i<5;i++){
            medicionMaquinaEntitys.add(factory.manufacturePojo(MedicionMaquinaEntity.class));
        }
        nuevo.setEjercicios(ejercicioInstanciaEntity);
        nuevo.setMedicionMaquinaEnt(medicionMaquinaEntitys);
        assertEquals(nuevo.getEjercicios(), ejercicioInstanciaEntity);
        assertEquals(nuevo.getMedicionMaquinaEnt(), medicionMaquinaEntitys);
    }
    
    private void assertEqualsObject(EjercicioHechoEntity a,EjercicioHechoEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFecha()),format.format(b.getFecha()));
        assertEquals(a.getSeriesReales(), b.getSeriesReales());
    }
    
    private EjercicioHechoEntity create(){
        return factory.manufacturePojo(EjercicioHechoEntity.class);
    }
}
