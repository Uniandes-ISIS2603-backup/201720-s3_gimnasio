package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.TipoMedidaPersistence;
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
public class TipoMedidaPersistenceTest {

    @Inject
    private TipoMedidaPersistence TipoMedidaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<TipoMedidaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TipoMedidaEntity.class.getPackage())
                .addPackage(TipoMedidaPersistence.class.getPackage())
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
        em.createQuery("delete from TipoMedidaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TipoMedidaEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void equalsHasTest() {
        TipoMedidaEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());

        BaseEntity tipo = (BaseEntity) factory.manufacturePojo(UsuarioEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo = null;
        assertFalse(newEntity.equals(tipo));

        TipoMedidaEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));

        newEntity2.setId(newEntity.getId() + 1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(), newEntity2.hashCode());
    }

    @Test
    public void createTipoMedidaTest() {
        TipoMedidaEntity newEntity = create();
        TipoMedidaEntity result = TipoMedidaPersistence.create(newEntity);
        assertNotNull(result);
        TipoMedidaEntity entity = em.find(TipoMedidaEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geTipoMedidasTest() {
        List<TipoMedidaEntity> list = TipoMedidaPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (TipoMedidaEntity ent : list) {
            boolean found = false;
            for (TipoMedidaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getTipoMedidaTest() {
        TipoMedidaEntity entity = data.get(0);
        TipoMedidaEntity newEntity = TipoMedidaPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteTipoMedidaTest() {
        TipoMedidaEntity entity = data.get(0);
        TipoMedidaPersistence.delete(entity.getId());
        TipoMedidaEntity deleted = em.find(TipoMedidaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateTipoMedidaTest() {
        TipoMedidaEntity entity = data.get(0);
        TipoMedidaEntity newEntity = create();
        newEntity.setId(entity.getId());
        TipoMedidaPersistence.update(newEntity);

        TipoMedidaEntity resp = em.find(TipoMedidaEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }

    @Test
    public void subEnititysTest() {
        TipoMedidaEntity newEntity = create();
        List<EjercicioEntity> ejercicios = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ejercicios.add(factory.manufacturePojo(EjercicioEntity.class));
        }
        List<MedidaEntity> medidas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            medidas.add(factory.manufacturePojo(MedidaEntity.class));
        }
        List<AtributoDeCalidadEntity> atributos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            atributos.add(factory.manufacturePojo(AtributoDeCalidadEntity.class));
        }
        List<MedicionMaquinaEntity> mediciones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mediciones.add(factory.manufacturePojo(MedicionMaquinaEntity.class));
        }

        newEntity.setEjercicios(ejercicios);
        newEntity.setMedidas(medidas);
        newEntity.setAtributos(atributos);
        newEntity.setMedicion(mediciones);
        assertEquals(newEntity.getEjercicios(), ejercicios);
        assertEquals(newEntity.getMedidas(), medidas);
        assertEquals(newEntity.getAtributos(), atributos);
        assertEquals(newEntity.getMedicion(), mediciones);
    }

    private void assertEqualsObject(TipoMedidaEntity a, TipoMedidaEntity b) {
        assertEquals(a.getTipoMedida(), b.getTipoMedida());
        assertEquals(a.getUnidad(), b.getUnidad());
        assertEquals(a.isAutomatico(), b.isAutomatico());
    }

    private TipoMedidaEntity create() {
        return factory.manufacturePojo(TipoMedidaEntity.class);
    }
}
