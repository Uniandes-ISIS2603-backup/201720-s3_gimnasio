/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.persistence.AtributoDeCalidadPersistence;
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
import static org.junit.Assert.*;
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
public class AtributoDeCalidadTest {

    @Inject
    private AtributoDeCalidadPersistence peristence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<AtributoDeCalidadEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AtributoDeCalidadEntity.class.getPackage())
                .addPackage(AtributoDeCalidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

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

    private void clearData() {
        em.createQuery("delete from AtributoDeCalidadEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AtributoDeCalidadEntity entity = factory.manufacturePojo(AtributoDeCalidadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void equalsHasTest(){
        PodamFactory factory = new PodamFactoryImpl();
        AtributoDeCalidadEntity newEntity = factory.manufacturePojo(AtributoDeCalidadEntity.class);
        AtributoDeCalidadEntity newEntity2 = factory.manufacturePojo(AtributoDeCalidadEntity.class);
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.equals(newEntity2),newEntity.getId().equals(newEntity2.getId()));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        assertEquals((newEntity.hashCode()==newEntity2.hashCode()),newEntity.getId().equals(newEntity2.getId()));
    }
    
    
    @Test
    public void createObjetivoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AtributoDeCalidadEntity newEntity = factory.manufacturePojo(AtributoDeCalidadEntity.class);
        AtributoDeCalidadEntity result = peristence.create(newEntity);
        assertNotNull(result);
        AtributoDeCalidadEntity entity = em.find(AtributoDeCalidadEntity.class, result.getId());
        assertEquals(newEntity.getRegresion(), entity.getRegresion());
    }

    @Test
    public void geObjetivosTest() {
        List<AtributoDeCalidadEntity> list = peristence.findAll();
        assertEquals(data.size(), list.size());
        for (AtributoDeCalidadEntity ent : list) {
            boolean found = false;
            for (AtributoDeCalidadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getObjetivoTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        AtributoDeCalidadEntity newEntity = peristence.find(entity.getId());
        assertNotNull(newEntity);
        assertEquals(newEntity.getRegresion(), entity.getRegresion());
    }

    @Test
    public void deleteObjetivoTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        peristence.delete(entity.getId());
        AtributoDeCalidadEntity deleted = em.find(AtributoDeCalidadEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateObjetivoTest() {
        AtributoDeCalidadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AtributoDeCalidadEntity newEntity = factory.manufacturePojo(AtributoDeCalidadEntity.class);
        newEntity.setId(entity.getId());
        peristence.update(newEntity);
        AtributoDeCalidadEntity resp = em.find(AtributoDeCalidadEntity.class, entity.getId());
        assertEquals(newEntity.getRegresion(), resp.getRegresion());
    }
}
