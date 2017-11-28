/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.test.logic;

import co.edu.uniandes.baco.gimnasio.ejb.EjercicioInstanciaLogic;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.bojaca
 */
@RunWith(Arquillian.class)
public class testEjercicioInstancia {

    @Inject
    private EjercicioInstanciaLogic baseLogic;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<EjercicioInstanciaEntity> data = new ArrayList<>();
    private final List<RutinaEntity> padres = new ArrayList<>();
    private final List<EjercicioEntity> ejercicios = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoEntity.class.getPackage())
                .addPackage(EstadoPersistence.class.getPackage())
                .addPackage(EstadoLogic.class.getPackage())
                .addPackage(BusinessLogicException.class.getPackage())
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
        em.createQuery("delete from EjercicioInstanciaEntity").executeUpdate();
        em.createQuery("delete from RutinaEntity").executeUpdate();
        em.createQuery("delete from EjercicioEntity").executeUpdate();
    }

    private void insertData() {
        for (int j = 0; j < 4; j++) {
            EjercicioEntity ejer = factory.manufacturePojo(EjercicioEntity.class);
            em.persist(ejer);
            ejercicios.add(ejer);
        }
        for (int j = 0; j < 4; j++) {
            RutinaEntity rutina = factory.manufacturePojo(RutinaEntity.class);
            em.persist(rutina);
            padres.add(rutina);
            for (int i = 0; i < 3; i++) {
                EjercicioInstanciaEntity entity = create();
                entity.setRutina(em.find(RutinaEntity.class, rutina.getId()));
                em.persist(entity);
                rutina.getEjercicios().add(entity);
                data.add(entity);
            }
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    @Test
    public void crearTest() {
        try {
            RutinaEntity rut = padres.get(0);
            EjercicioInstanciaEntity inst = create();
            EjercicioEntity ejer = ejercicios.get(0);
            EjercicioInstanciaEntity ans = baseLogic.create(rut.getId(), inst, ejer.getId());
            assertEqualsObject(ans, inst);
            assertEquals(rut, ans.getRutina());
            assertEquals(ejer, ans.getEjercicio());
        } catch (BusinessLogicException ex) {
            ex.printStackTrace();
            Logger.getLogger(testEjercicioInstancia.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }

    private void assertEqualsObject(EjercicioInstanciaEntity a, EjercicioInstanciaEntity b) {
        assertEquals(a.getCumplimiento(), b.getCumplimiento());
        assertEquals(a.getDuracion(), b.getDuracion());
        assertEquals(a.getEfectividad(), b.getEfectividad());
        assertEquals(a.getRepeticionesPorParticion(), b.getRepeticionesPorParticion());
        assertEquals(a.getSeries(), b.getSeries());
        assertEquals(a.getTamanioParticiones(), b.getTamanioParticiones());
    }

    private EjercicioInstanciaEntity create() {
        return factory.manufacturePojo(EjercicioInstanciaEntity.class);
    }
}
