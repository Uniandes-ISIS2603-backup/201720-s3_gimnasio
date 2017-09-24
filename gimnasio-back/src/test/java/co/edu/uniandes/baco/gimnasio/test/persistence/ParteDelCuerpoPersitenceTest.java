/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.ParteDelCuerpoPersitence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.palacios437
 */
@RunWith(Arquillian.class)
public class ParteDelCuerpoPersitenceTest {

    @Inject
    private ParteDelCuerpoPersitence pcpersitance;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<ParteDelCuerpoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParteDelCuerpoEntity.class.getPackage())
                .addPackage(ParteDelCuerpoPersitence.class.getPackage())
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
        em.createQuery("delete from ParteDelCuerpoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ParteDelCuerpoEntity entity = factory.manufacturePojo(ParteDelCuerpoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * crear una nueva parte del cuerpo.
     */
    @Test
    public void createParteDelCuerpoTest() {
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ParteDelCuerpoEntity newEntity = factory.manufacturePojo(ParteDelCuerpoEntity.class);
            ParteDelCuerpoEntity result = pcpersitance.create(newEntity);

            Assert.assertNotNull(result);

            ParteDelCuerpoEntity entity = em.find(ParteDelCuerpoEntity.class, result.getId());

            Assert.assertEquals(newEntity.getPartedelcuerpo(), entity.getPartedelcuerpo());
        } catch (Exception ex) {
            Logger.getLogger(ParteDelCuerpoPersitenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getParteDelCuerpo() {
        ParteDelCuerpoEntity entity = data.get(0);
        ParteDelCuerpoEntity newEntity = pcpersitance.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());

    }

    /**
     * actualiza un parte del cuerpo
     */
    @Test
    public void updateParteDelCuerpo() {
        try {
            ParteDelCuerpoEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            ParteDelCuerpoEntity newEntity = factory.manufacturePojo(ParteDelCuerpoEntity.class);

            newEntity.setId(entity.getId());

            pcpersitance.update(newEntity);

            MedidaEntity resp = em.find(MedidaEntity.class, entity.getId());

            Assert.assertEquals(newEntity.getId(), resp.getId());
        } catch (Exception ex) {
            Logger.getLogger(ParteDelCuerpoPersitenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * borra una parte del cuerpo
     */
    @Test
    public void deleteMedida() {
        try {
            ParteDelCuerpoEntity entity = data.get(0);
            pcpersitance.delete(entity.getId());
            ParteDelCuerpoEntity deleted = em.find(ParteDelCuerpoEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } catch (Exception ex) {
            Logger.getLogger(ParteDelCuerpoPersitenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ParteDelCuerpoPersitenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }
}
