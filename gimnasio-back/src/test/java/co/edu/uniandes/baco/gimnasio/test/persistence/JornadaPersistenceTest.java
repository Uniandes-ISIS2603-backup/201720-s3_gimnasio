package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.JornadaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.JornadaPersistence;
import java.util.ArrayList;
import java.util.Date;
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
public class JornadaPersistenceTest {
    @Inject
    private JornadaPersistence jornadaPersistence;
    
    @PersistenceContext(unitName = "jornadaPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final List<JornadaEntity> data = new ArrayList<>();

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
                .addPackage(JornadaEntity.class.getPackage())
                .addPackage(JornadaPersistence.class.getPackage())
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
        em.createQuery("delete from JornadaEntity").executeUpdate();
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
            JornadaEntity entity = factory.manufacturePojo(JornadaEntity.class);

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
    public void createJornadaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        JornadaEntity newEntity = factory.manufacturePojo(JornadaEntity.class);
        JornadaEntity result = jornadaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        JornadaEntity entity = em.find(JornadaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());
        Assert.assertTrue(compararHoras(newEntity.getHoraIni(), entity.getHoraIni()));
        Assert.assertTrue(compararHoras(newEntity.getHoraFin(), entity.getHoraFin()));
    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geJornadasTest() {
        List<JornadaEntity> list = jornadaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (JornadaEntity ent : list) {
            boolean found = false;
            for (JornadaEntity entity : data) {
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
    public void getJornadaTest() {
        JornadaEntity entity = data.get(0);
        JornadaEntity newEntity = jornadaPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());
        Assert.assertTrue(compararHoras(newEntity.getHoraIni(), entity.getHoraIni()));
        Assert.assertTrue(compararHoras(newEntity.getHoraFin(), entity.getHoraFin()));
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteJornadaTest() {
        JornadaEntity entity = data.get(0);
        jornadaPersistence.delete(entity.getId());
        JornadaEntity deleted = em.find(JornadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateJornadaTest() {
        JornadaEntity entitys = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        JornadaEntity newEntity = factory.manufacturePojo(JornadaEntity.class);

        newEntity.setId(entitys.getId());

        jornadaPersistence.update(newEntity);

        JornadaEntity entity = em.find(JornadaEntity.class, entitys.getId());

        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());
        Assert.assertTrue(compararHoras(newEntity.getHoraIni(), entity.getHoraIni()));
        Assert.assertTrue(compararHoras(newEntity.getHoraFin(), entity.getHoraFin()));
    }
    
    
    public final static boolean compararHoras(Date a,Date b){
        return (a.getHours()*3600)+(a.getMinutes()*60)+a.getSeconds()==(b.getHours()*3600)+(b.getMinutes()*60)+b.getSeconds();
    }
}
