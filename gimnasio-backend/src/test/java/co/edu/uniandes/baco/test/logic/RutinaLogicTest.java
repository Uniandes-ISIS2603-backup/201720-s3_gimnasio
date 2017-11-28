package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.RutinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
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
public class RutinaLogicTest {

    @Inject
    private RutinaLogic baseLogic;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<RutinaEntity> data = new ArrayList<>();
    private final List<UsuarioEntity> padres= new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RutinaEntity.class.getPackage())
                .addPackage(RutinaPersistence.class.getPackage())
                .addPackage(RutinaLogic.class.getPackage())
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
        em.createQuery("delete from RutinaEntity").executeUpdate();
    }

    private void insertData() {
        for (int j = 0; j < 4; j++) {
            UsuarioEntity objetivoEntity= factory.manufacturePojo(UsuarioEntity.class);
            em.persist(objetivoEntity);
            padres.add(objetivoEntity);
            for (int i = 0; i < 3; i++) {
                RutinaEntity entity = create();
                entity.setUsuario(em.find(UsuarioEntity.class, objetivoEntity.getId()));
                em.persist(entity);
                objetivoEntity.getRutinas().add(entity);
                data.add(entity);
            }
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void createRutinaTest() {
        try {
            RutinaEntity newEntity = create();
            UsuarioEntity user= padres.get(0);
            RutinaEntity result = baseLogic.create(user.getId(), newEntity);
            RutinaEntity entity = em.find(RutinaEntity.class, result.getId());
            assertEqualsObject(newEntity, entity);
            assertEquals(user, newEntity.getUsuario());
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
    }
    
    @Test
    public void updateRutinaTest() {
        try {
            RutinaEntity newEntity = create();
            UsuarioEntity user= padres.get(0);
            newEntity.setId(user.getRutinas().get(0).getId());
            RutinaEntity result = baseLogic.update(user.getId(), newEntity);
            RutinaEntity entity = em.find(RutinaEntity.class, result.getId());
            assertEqualsObject(newEntity, entity);
            assertEquals(user, result.getUsuario());
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
    }

    @Test
    public void geRutinasTest() {
        try {
            UsuarioEntity user= padres.get(0);
            List<RutinaEntity> real=em.find(UsuarioEntity.class, user.getId()).getRutinas();
            assertNotEquals(0, real.size());
            List<RutinaEntity> list = baseLogic.findAll(user.getId());
            assertNotEquals(0, list.size());
            assertEquals(real, list);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
    }

    @Test
    public void getRutinaTest() {
        try {
            RutinaEntity entity = data.get(0);
            UsuarioEntity user= entity.getUsuario();
            RutinaEntity newEntity = baseLogic.find(entity.getId());
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
            RutinaEntity newEntity = baseLogic.find((long) 0);
            fail("debe dar error");
        } catch (BusinessLogicException ex) {
            //es lo que se espera 
        }
    }

    @Test
    public void deleteRutinaTest() {
        try {
            RutinaEntity entity = data.get(0);
            baseLogic.remove(entity.getUsuario().getId(),entity.getId());
            RutinaEntity deleted = em.find(RutinaEntity.class, entity.getId());
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

     private void assertEqualsObject(RutinaEntity a,RutinaEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFechaFinal()),format.format(b.getFechaFinal()));
        assertEquals(format.format(a.getFechaInicio()),format.format(b.getFechaInicio()));
        assertEquals(a.getCumplimiento(), b.getCumplimiento());
    }

    private RutinaEntity create() {
        return factory.manufacturePojo(RutinaEntity.class);
    }
}
