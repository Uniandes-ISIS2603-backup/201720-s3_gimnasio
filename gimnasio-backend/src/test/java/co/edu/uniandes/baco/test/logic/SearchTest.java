package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
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
public class SearchTest {

    @Inject
    private UsuarioLogic baseLogic;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<UsuarioEntity> data = new ArrayList<>();
    private final List<EntrenadorEntity> padres = new ArrayList<>();

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
            UsuarioEntity usuarioEntity = create();
            em.persist(usuarioEntity);
            data.add(usuarioEntity);
            for (int i = 0; i < 3; i++) {
                EntrenadorEntity entity = factory.manufacturePojo(EntrenadorEntity.class);
                entity.getUsuarios().add(usuarioEntity);
                usuarioEntity.getEntrenadores().add(entity);
                em.persist(entity);
                padres.add(entity);
            }
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void geEstadosTest() {
        try {
            UsuarioEntity user = data.get(0);
            List<EntrenadorEntity> real = em.find(UsuarioEntity.class, user.getId()).getEntrenadores();
            assertNotEquals(0, real.size());
            List<EntrenadorEntity> list = baseLogic.findAllEntrenador(user.getId());
            assertNotEquals(0, list.size());
            assertEquals(real, list);
        } catch (BusinessLogicException ex) {
            fail("no debe dar error");
        }
    }

    @Test
    public void getEstadoTest() {
        try {
            UsuarioEntity user = data.get(0);
            EntrenadorEntity entreador = user.getEntrenadores().get(0);
            assertTrue(baseLogic.findAllEntrenador(user.getId()).contains(entreador));
            EntrenadorEntity newEntity = baseLogic.findAllEntrenador(user.getId(), entreador.getId());
            assertEquals(newEntity.getId(), entreador.getId());
            try {
                user = data.get(1);
                baseLogic.findAllEntrenador(user.getId(), entreador.getId());
                fail("debereia fallar");
            } catch (BusinessLogicException ex) {
            }
        } catch (BusinessLogicException ex) {
            fail("no debe dar error" + ex.getMessage() + "\n" + ex.getStackTrace()[0]);
        }
    }

    private UsuarioEntity create() {
        return factory.manufacturePojo(UsuarioEntity.class);
    }
}
