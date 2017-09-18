package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



/**
 *
 */
@RunWith(Arquillian.class)
public class EjercicioPersistenceTest {
    @Inject
    private EjercicioPersistence ejercicioPersistence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final List<EjercicioEntity> data = new ArrayList<>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Employee, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EjercicioEntity.class.getPackage())
                .addPackage(EjercicioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Configuración inicial de la prueba.
     *
     *
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from EjercicioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EjercicioEntity entity = factory.manufacturePojo(EjercicioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Employee.
     *
     *
     */
    @Test
    public void createEjercicioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);
        EjercicioEntity result = ejercicioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EjercicioEntity entity = em.find(EjercicioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
        Assert.assertEquals(newEntity.getSeries(), entity.getSeries());
        Assert.assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        Assert.assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geEjerciciosTest() {
        List<EjercicioEntity> list = ejercicioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EjercicioEntity ent : list) {
            boolean found = false;
            for (EjercicioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Employee.
     *
     *
     */
    @Test
    public void getEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        EjercicioEntity newEntity = ejercicioPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
        Assert.assertEquals(newEntity.getSeries(), entity.getSeries());
        Assert.assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        Assert.assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        ejercicioPersistence.delete(entity.getId());
        EjercicioEntity deleted = em.find(EjercicioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);

        newEntity.setId(entity.getId());

        ejercicioPersistence.update(newEntity);

        EjercicioEntity resp = em.find(EjercicioEntity.class, entity.getId());
        entity=resp;

        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getExplicacion(), entity.getExplicacion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
        Assert.assertEquals(newEntity.getSeries(), entity.getSeries());
        Assert.assertEquals(newEntity.getTamanioParticiones(), entity.getTamanioParticiones());
        Assert.assertEquals(newEntity.getRepeticionesPorParticion(), entity.getRepeticionesPorParticion());
    }
}
