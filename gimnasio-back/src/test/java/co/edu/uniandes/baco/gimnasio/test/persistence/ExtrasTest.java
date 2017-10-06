/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.bojaca
 */
public class ExtrasTest {
     private final PodamFactory factory = new PodamFactoryImpl();
     
     @Test
     public void testBaseEntity(){
        BaseEntity newEntity = (BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());
         assertFalse(newEntity.equals("hola esto es una prueba"));
        
        BaseEntity newEntity2= (BaseEntity)factory.manufacturePojo(ObjetivoEntity.class);
        newEntity2.setId(null);
        assertFalse(newEntity.equals(newEntity2));
        assertEquals(newEntity2.hashCode(), newEntity2.hashCode());
     }
     
     @Test
     public void pruebaExepcion(){
         String mensaje=factory.manufacturePojo(String.class);
         Throwable cause=factory.manufacturePojo(Throwable.class);
         try{
             throw new BusinessLogicException(mensaje);
         }catch(BusinessLogicException e){
             assertEquals(mensaje, e.getMessage());
         }
         
         try{
             throw new BusinessLogicException(cause);
         }catch(BusinessLogicException e){
             assertEquals(cause, e.getCause());
         }
         
         try{
             throw new BusinessLogicException(mensaje,cause);
         }catch(BusinessLogicException e){
             assertEquals(mensaje, e.getMessage());
             assertEquals(cause, e.getCause());
         }
     }
}
