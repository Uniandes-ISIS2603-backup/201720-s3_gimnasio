/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedidaUsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.MedidaUsuarioPersistence;
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
 * @author ce.robles
 */
@RunWith(Arquillian.class)
public class MedidaUsuarioPersistenceTest 
{
    @Inject
    private MedidaUsuarioPersistence medidaUsuarioPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<MedidaUsuarioEntity> data = new ArrayList<>();    
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Medicion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedidaUsuarioEntity.class.getPackage())
                .addPackage(MedidaUsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuracion inicial de la prueba.
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
     * Limpia las tablas implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from MedidaUsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el funcionamiento de la prueba.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            MedidaUsuarioEntity entity = factory.manufacturePojo(MedidaUsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }    
    }
    
    /**
     * Prueba para crear una Medicion.
     */
    @Test
    public void createMedidaUsuarioTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        MedidaUsuarioEntity newEntity = factory.manufacturePojo(MedidaUsuarioEntity.class);
        MedidaUsuarioEntity result = medidaUsuarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedidaUsuarioEntity entity = em.find(MedidaUsuarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());

    }
    
    /**
     * Prueba para consultar la lista de Mediciones.
     */
    @Test
    public void listaMedidaUsuarioTest() 
    {
        List<MedidaUsuarioEntity> list = medidaUsuarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(MedidaUsuarioEntity ent : list) {
            boolean found = false;
            for (MedidaUsuarioEntity entity : data)                 
                if (ent.getId().equals(entity.getId())) 
                    found = true;               
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una medicion.
     */
    @Test
    public void consultarMedidaUsuarioTest() 
    {
        MedidaUsuarioEntity entity = data.get(0);
        MedidaUsuarioEntity newEntity =  medidaUsuarioPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());

    }
    
    /**
     * Prueba para eliminar una medicion.
     */
    @Test
    public void deleteMedidaUsuarioEntityTest() 
    {
        MedidaUsuarioEntity entity = data.get(0);
        medidaUsuarioPersistence.delete(entity.getId());
        MedidaUsuarioEntity deleted = em.find(MedidaUsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una medicion.
     */
     @Test
    public void updateMedidaUsuarioTest() {
        MedidaUsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedidaUsuarioEntity newEntity = factory.manufacturePojo(MedidaUsuarioEntity.class);

        newEntity.setId(entity.getId());

        medidaUsuarioPersistence.update(newEntity);

        MedidaUsuarioEntity resp = em.find(MedidaUsuarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFecha().getDate(), entity.getFecha().getDate());
    }
}
