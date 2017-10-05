package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class BaseEntityTest {
    
      private final PodamFactory factory = new PodamFactoryImpl();
      
      @Test
      public void test(){
        BaseEntity newEntity=factory.manufacturePojo(EjercicioEntity.class);
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
        assertTrue(newEntity.equals(newEntity));
        
        BaseEntity tipo=null;
        assertFalse(newEntity.equals(tipo));
        
        Object newEntity2= factory.manufacturePojo(EjercicioEntity.class);
        assertFalse(newEntity.equals(newEntity2));
        
        BaseEntity newEntity3=factory.manufacturePojo(EjercicioEntity.class);
        newEntity3.setId(null);
        assertFalse(newEntity.equals(newEntity3));
        assertNotEquals(newEntity.hashCode(),newEntity3.hashCode());
        
        newEntity3.setId(newEntity.getId());
        assertTrue(newEntity.equals(newEntity3));
        newEntity.setId(null);
        assertFalse(newEntity.equals(newEntity3));
        
        newEntity.setId(null);
        assertEquals(newEntity.hashCode(),((Object)newEntity).hashCode());
      }
}
