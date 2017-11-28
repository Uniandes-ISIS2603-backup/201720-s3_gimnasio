package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
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
public class ObjetivoPersistenceTest {
    @Inject
    private ObjetivoPersistence ObjetivoPersistence;

    @PersistenceContext(unitName = "gimnasioPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private final List<ObjetivoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ObjetivoEntity.class.getPackage())
                .addPackage(ObjetivoPersistence.class.getPackage())
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
        em.createQuery("delete from ObjetivoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ObjetivoEntity entity = create();
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------
    // TEST
    //--------------------------------------
    
    @Test
    public void equalsHasTest() {
        ObjetivoEntity newEntity = create();
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        
        BaseEntity tipo=(BaseEntity)factory.manufacturePojo(UsuarioEntity.class);
        assertFalse(newEntity.equals(tipo));
        tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        ObjetivoEntity newEntity2 = create();
        newEntity2.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity2));
        
        newEntity2.setId(newEntity.getId()+1);
        assertFalse(newEntity.equals(newEntity2));
        assertNotEquals(newEntity.hashCode(),newEntity2.hashCode());
    }
    
    @Test
    public void finByTipoTest() {
        ObjetivoEntity entity = data.get(0);
        ObjetivoEntity newEntity = ObjetivoPersistence.findByTipo(entity.getTipo());
        assertNotNull(newEntity);
        assertEqualsObject(newEntity, entity);

        String tipo = data.get(0).getTipo();
        boolean esta;
        do {
            tipo += "a";
            esta = false;
            for (ObjetivoEntity i : data) {
                if (i.getTipo().equals(tipo)) {
                    esta = true;
                }
            }
        } while (esta);
        assertNull(ObjetivoPersistence.findByTipo(tipo));
    }
    
    @Test
    public void subEnititysTest(){
        ObjetivoEntity newEntity = create();
        List<UsuarioEntity> usuarios=new ArrayList<>();
        for(int i=0;i<5;i++)
            usuarios.add(factory.manufacturePojo(UsuarioEntity.class));
        List<AtributoDeCalidadEntity> atributos=new ArrayList<>();
        for(int i=0;i<5;i++)
            atributos.add(factory.manufacturePojo(AtributoDeCalidadEntity.class));
        List<EjercicioEntity> ejercicios=new ArrayList<>();
        for(int i=0;i<5;i++)
            ejercicios.add(factory.manufacturePojo(EjercicioEntity.class));
        
        newEntity.setEjercicios(ejercicios);
        newEntity.setUsuarios(usuarios);
        newEntity.setAtributos(atributos);
        assertEquals(newEntity.getEjercicios(), ejercicios);
        assertEquals(newEntity.getUsuarios(),usuarios);
        assertEquals(newEntity.getAtributos(), atributos);
    }
    
    private void assertEqualsObject(ObjetivoEntity a,ObjetivoEntity b){
        assertEquals(a.getDescripcion(),b.getDescripcion());
        assertEquals(a.getTipo(),b.getTipo());
    }
    
    private ObjetivoEntity create(){
        return factory.manufacturePojo(ObjetivoEntity.class);
    }
}
