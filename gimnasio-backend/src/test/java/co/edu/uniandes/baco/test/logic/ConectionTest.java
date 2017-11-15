package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.EntrenadorLogic;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
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
public class ConectionTest {

    @Inject
    private EntrenadorLogic baseLogic;

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
    public void createEstadoTest() {
        try {
            UsuarioEntity newEntity = data.get(1);
            EntrenadorEntity entre = padres.get(0);
            baseLogic.createUsuario(entre.getId(), newEntity.getId());
            assertTrue(em.find(EntrenadorEntity.class, entre.getId()).getUsuarios().contains(newEntity));
        } catch (BusinessLogicException ex) {
            fail("debe crearse");
        }
        try {
            UsuarioEntity newEntity = data.get(0);
            EntrenadorEntity entre = padres.get(0);
            baseLogic.createUsuario(entre.getId(), newEntity.getId());
            fail("debe debe fallar");
        } catch (BusinessLogicException ex) {
        }
    }

    @Test
    public void deleteEstadoTest() {
        try {
            UsuarioEntity newEntity = data.get(0);
            EntrenadorEntity entre = padres.get(0);
            baseLogic.removeUsuario(entre.getId(), newEntity.getId());
            assertFalse(em.find(EntrenadorEntity.class, entre.getId()).getUsuarios().contains(newEntity));
        } catch (BusinessLogicException ex) {
            fail("debe borrarse");
        }
    }


    private UsuarioEntity create() {
        return factory.manufacturePojo(UsuarioEntity.class);
    }
}
