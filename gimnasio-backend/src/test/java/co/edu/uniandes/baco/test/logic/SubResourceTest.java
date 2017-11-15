package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
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
public class SubResourceTest {

    @Inject
    private EstadoLogic baseLogic;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EstadoEntity> data = new ArrayList<>();
    private final List<UsuarioEntity> padres= new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoEntity.class.getPackage())
                .addPackage(EstadoPersistence.class.getPackage())
                .addPackage(EstadoLogic.class.getPackage())
                .addPackage(BusinessLogicException.class.getPackage())
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
        for (int j = 0; j < 4; j++) {
            UsuarioEntity objetivoEntity= factory.manufacturePojo(UsuarioEntity.class);
            em.persist(objetivoEntity);
            padres.add(objetivoEntity);
            for (int i = 0; i < 3; i++) {
                EstadoEntity entity = create();
                entity.setUsuario(em.find(UsuarioEntity.class, objetivoEntity.getId()));
                em.persist(entity);
                objetivoEntity.getEstados().add(entity);
                data.add(entity);
            }
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void createEstadoTest() {
        try {
            EstadoEntity newEntity = create();
            UsuarioEntity user= padres.get(0);
            EstadoEntity result = baseLogic.create(user.getId(), newEntity);
            EstadoEntity entity = em.find(EstadoEntity.class, result.getId());
            assertEqualsObject(newEntity, entity);
            assertEquals(user, newEntity.getUsuario());
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
    }
    
    @Test
    public void updateEstadoTest() {
        try {
            EstadoEntity newEntity = create();
            UsuarioEntity user= padres.get(0);
            newEntity.setId(user.getEstados().get(0).getId());
            EstadoEntity result = baseLogic.update(user.getId(), newEntity);
            EstadoEntity entity = em.find(EstadoEntity.class, result.getId());
            assertEqualsObject(newEntity, entity);
            assertEquals(user, result.getUsuario());
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
    }

    @Test
    public void geEstadosTest() {
        try {
            UsuarioEntity user= padres.get(0);
            List<EstadoEntity> real=em.find(UsuarioEntity.class, user.getId()).getEstados();
            assertNotEquals(0, real.size());
            List<EstadoEntity> list = baseLogic.findAll(user.getId());
            assertNotEquals(0, list.size());
            assertEquals(real, list);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
    }

    @Test
    public void getEstadoTest() {
        try {
            EstadoEntity entity = data.get(0);
            UsuarioEntity user= entity.getUsuario();
            EstadoEntity newEntity = baseLogic.find(entity.getId());
            assertEquals(newEntity.getUsuario(), user);
            assertTrue(baseLogic.findAll(user.getId()).contains(entity));
            newEntity = baseLogic.find(user.getId(),entity.getId());
            assertEqualsObject(newEntity, entity);
            try{
                user= padres.get(1);
                baseLogic.find(user.getId(),entity.getId());
                fail("debereia fallar");
            }catch (BusinessLogicException ex){}
        } catch (BusinessLogicException ex) {
            fail("no debe dar error"+ ex.getMessage() + "\n"+ ex.getStackTrace()[0]);
        }
        try {
            EstadoEntity newEntity = baseLogic.find((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    @Test
    public void deleteEstadoTest() {
        try {
            EstadoEntity entity = data.get(0);
            baseLogic.remove(entity.getUsuario().getId(),entity.getId());
            EstadoEntity deleted = em.find(EstadoEntity.class, entity.getId());
            assertNull(deleted);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
        try {
            baseLogic.remove((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    private void assertEqualsObject(EstadoEntity a,EstadoEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFecha()),format.format(b.getFecha()));
    }

    private EstadoEntity create() {
        return factory.manufacturePojo(EstadoEntity.class);
    }
}
