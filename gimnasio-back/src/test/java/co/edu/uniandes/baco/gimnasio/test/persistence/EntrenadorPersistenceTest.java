package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
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
public class EntrenadorPersistenceTest {

    @Inject
    private EntrenadorPersistence EntrenadorPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<EntrenadorEntity> data = new ArrayList<>();

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
                .addPackage(EntrenadorEntity.class.getPackage())
                .addPackage(EntrenadorPersistence.class.getPackage())
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
        em.createQuery("delete from EntrenadorEntity").executeUpdate();
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
            EntrenadorEntity entity = factory.manufacturePojo(EntrenadorEntity.class);

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
    public void createEntrenadorTest() throws Exception {

        PodamFactory factory = new PodamFactoryImpl();
        EntrenadorEntity newEntity = factory.manufacturePojo(EntrenadorEntity.class);
        EntrenadorEntity result = EntrenadorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EntrenadorEntity entity = em.find(EntrenadorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDocumento(), entity.getDocumento());
        Assert.assertEquals(newEntity.getFechaNacimiento().getDate(), entity.getFechaNacimiento().getDate());

    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geEntrenadorsTest() throws Exception {
        List<EntrenadorEntity> list = EntrenadorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EntrenadorEntity ent : list) {
            boolean found = false;
            for (EntrenadorEntity entity : data) {
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
    public void getEntrenadorTest() throws Exception {

        EntrenadorEntity entity = data.get(0);
        EntrenadorEntity newEntity = EntrenadorPersistence.find(entity.getId());

        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDocumento(), entity.getDocumento());
        Assert.assertEquals(newEntity.getFechaNacimiento().getDate(), entity.getFechaNacimiento().getDate());

    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteEntrenadorTest() throws Exception {

        EntrenadorEntity entity = data.get(0);
        EntrenadorPersistence.delete(entity.getId());
        EntrenadorEntity deleted = em.find(EntrenadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateEntrenadorTest() throws Exception {
        EntrenadorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EntrenadorEntity newEntity = factory.manufacturePojo(EntrenadorEntity.class);

        newEntity.setId(entity.getId());

        EntrenadorPersistence.update(newEntity);

        EntrenadorEntity resp = em.find(EntrenadorEntity.class, entity.getId());
        entity = resp;

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDocumento(), entity.getDocumento());
        Assert.assertEquals(newEntity.getFechaNacimiento().getDate(), entity.getFechaNacimiento().getDate());
    }
}
