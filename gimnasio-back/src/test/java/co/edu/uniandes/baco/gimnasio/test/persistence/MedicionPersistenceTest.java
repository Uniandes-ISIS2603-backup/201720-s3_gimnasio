/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedicionEntity;
import co.edu.uniandes.baco.gimnasio.persistence.MedicionPersistence;
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
public class MedicionPersistenceTest 
{

    @Inject
    private MedicionPersistence medicionPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<MedicionEntity> data = new ArrayList<>();    
    
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
                .addPackage(MedicionEntity.class.getPackage())
                .addPackage(MedicionPersistence.class.getPackage())
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
        em.createQuery("delete from MedicionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el funcionamiento de la prueba.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            MedicionEntity entity = factory.manufacturePojo(MedicionEntity.class);

            em.persist(entity);
            data.add(entity);
        }    
    }
    
    /**
     * Prueba para crear una Medicion.
     */
    @Test
    public void createMedicionTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        MedicionEntity newEntity = factory.manufacturePojo(MedicionEntity.class);
        MedicionEntity result = medicionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedicionEntity entity = em.find(MedicionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getMedida(), entity.getMedida());
    }
    
    /**
     * Prueba para consultar la lista de Mediciones.
     */
    @Test
    public void listaMedicionTest() 
    {
        List<MedicionEntity> list = medicionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(MedicionEntity ent : list) {
            boolean found = false;
            for (MedicionEntity entity : data)                 
                if (ent.getId().equals(entity.getId())) 
                    found = true;               
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una medicion.
     */
    @Test
    public void consultarMedicionTest() 
    {
        MedicionEntity entity = data.get(0);
        MedicionEntity newEntity =  medicionPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getMedida(), entity.getMedida());
    }
    
    /**
     * Prueba para eliminar una medicion.
     */
    @Test
    public void deleteMedicionEntityTest() 
    {
        MedicionEntity entity = data.get(0);
        medicionPersistence.delete(entity.getId());
        MedicionEntity deleted = em.find(MedicionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una medicion.
     */
     @Test
    public void updateMedicionTest() {
        MedicionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicionEntity newEntity = factory.manufacturePojo(MedicionEntity.class);

        newEntity.setId(entity.getId());

        medicionPersistence.update(newEntity);

        MedicionEntity resp = em.find(MedicionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMedida(), resp.getMedida());
    }    
}
