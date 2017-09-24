/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
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
public class TipoMedidaPersistanceTest 
{
    @Inject
    private TipoMedidaPersistence tipoMedidaPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<TipoMedidaEntity> data = new ArrayList<>();    
    
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
                .addPackage(TipoMedidaEntity.class.getPackage())
                .addPackage(TipoMedidaPersistence.class.getPackage())
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
            TipoMedidaEntity entity = factory.manufacturePojo(TipoMedidaEntity.class);

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
        TipoMedidaEntity newEntity = factory.manufacturePojo(TipoMedidaEntity.class);
        TipoMedidaEntity result = tipoMedidaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TipoMedidaEntity entity = em.find(TipoMedidaEntity.class, result.getId());
    }
    
    /**
     * Prueba para consultar la lista de Mediciones.
     */
    @Test
    public void listaMedicionTest() 
    {
        List<TipoMedidaEntity> list = tipoMedidaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(TipoMedidaEntity ent : list) {
            boolean found = false;
            for (TipoMedidaEntity entity : data)                 
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
        TipoMedidaEntity entity = data.get(0);
        TipoMedidaEntity newEntity =  tipoMedidaPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        //Assert.assertEquals(newEntity.getMedida(), entity.getMedida());

    }
    
    /**
     * Prueba para eliminar una medicion.
     */
    @Test
    public void deleteMedicionEntityTest() 
    {
        TipoMedidaEntity entity = data.get(0);
        tipoMedidaPersistence.delete(entity.getId());
        TipoMedidaEntity deleted = em.find(TipoMedidaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una medicion.
     */
     @Test
    public void updateMedicionTest() {
        TipoMedidaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TipoMedidaEntity newEntity = factory.manufacturePojo(TipoMedidaEntity.class);

        newEntity.setId(entity.getId());

        tipoMedidaPersistence.update(newEntity);

        TipoMedidaEntity resp = em.find(TipoMedidaEntity.class, entity.getId());

        //Assert.assertEquals(newEntity.getMedida(), resp.getMedida());
    } 
}
