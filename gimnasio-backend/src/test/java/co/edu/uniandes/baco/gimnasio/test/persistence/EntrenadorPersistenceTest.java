package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
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
public class EntrenadorPersistenceTest {

    @Inject
    private EntrenadorPersistence EntrenadorPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EntrenadorEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EntrenadorEntity.class.getPackage())
                .addPackage(EntrenadorPersistence.class.getPackage())
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
        em.createQuery("delete from EntrenadorEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EntrenadorEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void equalsHasTest() {
        EntrenadorEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());

        BaseEntity tipo = (BaseEntity) factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo = null;
        assertFalse(newEntity.equals(tipo));

        EntrenadorEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));

        newEntity2.setId(newEntity.getId() + 1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(), newEntity2.hashCode());
    }

    @Test
    public void createEntrenadorTest() {
        EntrenadorEntity newEntity = create();
        EntrenadorEntity result = EntrenadorPersistence.create(newEntity);
        assertNotNull(result);
        EntrenadorEntity entity = em.find(EntrenadorEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void finByDocumentoTest() {
        EntrenadorEntity entity = data.get(0);
        EntrenadorEntity newEntity = EntrenadorPersistence.findByDocumento(entity.getDocumento());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);

        String documento = data.get(0).getDocumento();
        boolean esta;
        do {
            documento += "a";
            esta = false;
            for (EntrenadorEntity i : data) {
                if (i.getDocumento().equals(documento)) {
                    esta = true;
                }
            }
        } while (esta);
        assertNull(EntrenadorPersistence.findByDocumento(documento));
    }

    @Test
    public void geEntrenadorsTest() {
        List<EntrenadorEntity> list = EntrenadorPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (EntrenadorEntity ent : list) {
            boolean found = false;
            for (EntrenadorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getEntrenadorTest() {
        EntrenadorEntity entity = data.get(0);
        EntrenadorEntity newEntity = EntrenadorPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteEntrenadorTest() {
        EntrenadorEntity entity = data.get(0);
        EntrenadorPersistence.delete(entity.getId());
        EntrenadorEntity deleted = em.find(EntrenadorEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateEntrenadorTest() {
        EntrenadorEntity entity = data.get(0);
        EntrenadorEntity newEntity = create();
        newEntity.setId(entity.getId());
        EntrenadorPersistence.update(newEntity);

        EntrenadorEntity resp = em.find(EntrenadorEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }

    @Test
    public void subEnititysTest() {
        EntrenadorEntity newEntity = create();
        List<UsuarioEntity> usuarios = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            usuarios.add(factory.manufacturePojo(UsuarioEntity.class));
        }

        newEntity.setUsuarios(usuarios);
        assertEquals(newEntity.getUsuarios(), usuarios);
    }

    private void assertEqualsObject(EntrenadorEntity a, EntrenadorEntity b) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFechaNacimiento()), format.format(b.getFechaNacimiento()));
        assertEquals(a.getDocumento(), b.getDocumento());
        assertEquals(a.getNombre(), b.getNombre());
    }

    private EntrenadorEntity create() {
        return factory.manufacturePojo(EntrenadorEntity.class);
    }
}
