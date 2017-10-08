package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.UsuarioPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
    @Inject
    private UsuarioPersistence UsuarioPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<UsuarioEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        UsuarioEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        UsuarioEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void createUsuarioTest() {
        UsuarioEntity newEntity = create();
        UsuarioEntity result = UsuarioPersistence.create(newEntity);
        assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void geUsuariosTest() {
        List<UsuarioEntity> list = UsuarioPersistence.findAll();
        assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = UsuarioPersistence.find(entity.getId());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);
    }

    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioPersistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = create();
        newEntity.setId(entity.getId());
        UsuarioPersistence.update(newEntity);
        
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        assertEqualsObject(newEntity, resp);
    }
    
    @Test
    public void subEnititysTest(){
        UsuarioEntity newEntity = create();
        List<EntrenadorEntity> entrenadores=new ArrayList<>();
        for(int i=0;i<5;i++)
            entrenadores.add(factory.manufacturePojo(EntrenadorEntity.class));
        List<ObjetivoEntity> objetivos=new ArrayList<>();
        for(int i=0;i<5;i++)
            objetivos.add(factory.manufacturePojo(ObjetivoEntity.class));
        List<RutinaEntity> rutinas=new ArrayList<>();
        for(int i=0;i<5;i++)
            rutinas.add(factory.manufacturePojo(RutinaEntity.class));
        List<EstadoEntity> estados=new ArrayList<>();
        for(int i=0;i<5;i++)
            estados.add(factory.manufacturePojo(EstadoEntity.class));
        
        newEntity.setEntrenadores(entrenadores);
        newEntity.setObjetivos(objetivos);
        newEntity.setRutinas(rutinas);
        newEntity.setEstados(estados);
        assertEquals(newEntity.getEntrenadores(), entrenadores);
        assertEquals(newEntity.getObjetivos(), objetivos);
        assertEquals(newEntity.getRutinas(), rutinas);
        assertEquals(newEntity.getEstados(), estados);
    }
    
    private void assertEqualsObject(UsuarioEntity a,UsuarioEntity b){
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(format.format(a.getFechaDeNacimiento()),format.format(b.getFechaDeNacimiento()));
        assertEquals(a.getNombre(),b.getNombre());
        assertEquals(a.isGenero(),b.isGenero());
    }
    
    private UsuarioEntity create(){
        return factory.manufacturePojo(UsuarioEntity.class);
    }
}
