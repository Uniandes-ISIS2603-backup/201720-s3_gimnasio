package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ObjetivoPersistenceTest {
    @Inject
    private ObjetivoPersistence objetivoPersistence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final List<ObjetivoEntity> data = new ArrayList<>();

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
                .addPackage(ObjetivoEntity.class.getPackage())
                .addPackage(ObjetivoPersistence.class.getPackage())
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
        em.createQuery("delete from ObjetivoEntity").executeUpdate();
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
            ObjetivoEntity entity = factory.manufacturePojo(ObjetivoEntity.class);

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
    public void createObjetivoTest() {
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ObjetivoEntity newEntity = factory.manufacturePojo(ObjetivoEntity.class);
            ObjetivoEntity result = objetivoPersistence.create(newEntity);
            
            Assert.assertNotNull(result);
            
            ObjetivoEntity entity = em.find(ObjetivoEntity.class, result.getId());
            
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geObjetivosTest() {
        try {
            List<ObjetivoEntity> list = objetivoPersistence.findAll();
            Assert.assertEquals(data.size(), list.size());
            for (ObjetivoEntity ent : list) {
                boolean found = false;
                for (ObjetivoEntity entity : data) {
                    if (ent.getId().equals(entity.getId())) {
                        found = true;
                    }
                }
                Assert.assertTrue(found);
            }
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar un Employee.
     *
     *
     */
    @Test
    public void getObjetivoTest() {
        try {
            ObjetivoEntity entity = data.get(0);
            ObjetivoEntity newEntity = objetivoPersistence.find(entity.getId());
            
            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteObjetivoTest() {
        try {
            ObjetivoEntity entity = data.get(0);
            objetivoPersistence.delete(entity.getId());
            ObjetivoEntity deleted = em.find(ObjetivoEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateObjetivoTest() {
        ObjetivoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ObjetivoEntity newEntity = factory.manufacturePojo(ObjetivoEntity.class);

        newEntity.setId(entity.getId());

        try {
            objetivoPersistence.update(newEntity);
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObjetivoEntity resp = em.find(ObjetivoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
    }
}
