/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.MaquinaPersistence;
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
 * @author t.kavanagh
 */
@RunWith(Arquillian.class)
public class MaquinaPersistenceTest 
{
    @Inject
    private MaquinaPersistence MaquinaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<MaquinaEntity> data = new ArrayList<>();    
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de maquina, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MaquinaEntity.class.getPackage())
                .addPackage(MaquinaPersistence.class.getPackage())
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
        em.createQuery("delete from MaquinaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el funcionamiento de la prueba.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            MaquinaEntity entity = factory.manufacturePojo(MaquinaEntity.class);

            em.persist(entity);
            data.add(entity);
        }    
    }
    
    /**
     * Prueba para crear una maquina.
     */
    @Test
    public void createMedidaUsuarioTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        MaquinaEntity newEntity = factory.manufacturePojo(MaquinaEntity.class);
        MaquinaEntity result = MaquinaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MaquinaEntity entity = em.find(MaquinaEntity.class, result.getId());


    }
    
    /**
     * Prueba para consultar la lista de maquinaes.
     */
    @Test
    public void listaMedidaUsuarioTest() 
    {
        List<MaquinaEntity> list = MaquinaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(MaquinaEntity ent : list) {
            boolean found = false;
            for (MaquinaEntity entity : data)                 
                if (ent.getId().equals(entity.getId())) 
                    found = true;               
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una maquina.
     */
    @Test
    public void consultarMedidaUsuarioTest() 
    {
        MaquinaEntity entity = data.get(0);
        MaquinaEntity newEntity =  MaquinaPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);

    }
    
    /**
     * Prueba para eliminar una maquina.
     */
    @Test
    public void deleteMaquinaEntityTest() 
    {
        MaquinaEntity entity = data.get(0);
        MaquinaPersistence.delete(entity.getId());
        MaquinaEntity deleted = em.find(MaquinaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /** 
     * Prueba para actualizar una maquina.
     */
     @Test
    public void updateMedidaUsuarioTest() {
        MaquinaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MaquinaEntity newEntity = factory.manufacturePojo(MaquinaEntity.class);

        newEntity.setId(entity.getId());

        MaquinaPersistence.update(newEntity);

        MaquinaEntity resp = em.find(MaquinaEntity.class, entity.getId());

      
    }
}