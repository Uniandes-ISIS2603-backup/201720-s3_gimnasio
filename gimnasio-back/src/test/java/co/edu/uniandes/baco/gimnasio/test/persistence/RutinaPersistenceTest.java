/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.RutinaPersistence;
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
 * @author jp.romero12
 */
@RunWith(Arquillian.class)
public class RutinaPersistenceTest {
    @Inject
    private RutinaPersistence rutinaPersistence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final List<RutinaEntity> data = new ArrayList<>();
    
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
                .addPackage(RutinaEntity.class.getPackage())
                .addPackage(RutinaPersistence.class.getPackage())
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
        em.createQuery("delete from RutinaEntity").executeUpdate();
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
            RutinaEntity entity = factory.manufacturePojo(RutinaEntity.class);

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
    public void createRutinaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RutinaEntity newEntity = factory.manufacturePojo(RutinaEntity.class);
        RutinaEntity result = rutinaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        RutinaEntity entity = em.find(RutinaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geRutinaTest() {
        List<RutinaEntity> list = rutinaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (RutinaEntity ent : list) {
            boolean found = false;
            for (RutinaEntity entity : data) {
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
    public void getRutinaTest() {
        RutinaEntity entity = data.get(0);
        RutinaEntity newEntity = rutinaPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteRutinaTest() {
        RutinaEntity entity = data.get(0);
        rutinaPersistence.delete(entity.getId());
        RutinaEntity deleted = em.find(RutinaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateRutinaTest() {
        RutinaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RutinaEntity newEntity = factory.manufacturePojo(RutinaEntity.class);

        newEntity.setId(entity.getId());

        rutinaPersistence.update(newEntity);

        RutinaEntity resp = em.find(RutinaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
    }
    
}
