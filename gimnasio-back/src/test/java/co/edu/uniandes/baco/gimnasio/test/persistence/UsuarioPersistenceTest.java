/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.UsuarioPersistence;
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
public class UsuarioPersistenceTest {
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final List<UsuarioEntity> data = new ArrayList<>();
    
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
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
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
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

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
    public void createUsuarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFechaDeNacimiento(), entity.getFechaDeNacimiento());
    }

    /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void geUsuarioTest() {
        List<UsuarioEntity> list = usuarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
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
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = usuarioPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFechaDeNacimiento(), entity.getFechaDeNacimiento());
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        usuarioPersistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateRutinaTest() {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);

        newEntity.setId(entity.getId());

        usuarioPersistence.update(newEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFechaDeNacimiento(), resp.getFechaDeNacimiento());
    }
    
}
