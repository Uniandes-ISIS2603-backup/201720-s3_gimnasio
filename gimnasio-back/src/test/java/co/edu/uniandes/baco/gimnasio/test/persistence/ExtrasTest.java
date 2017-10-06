/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.test.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import javax.ws.rs.WebApplicationException;
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
    public void testBaseEntity() {
        BaseEntity newEntity = (BaseEntity) factory.manufacturePojo(ObjetivoEntity.class);
        String mensaje = factory.manufacturePojo(String.class);
        BusinessLogicException object = new BusinessLogicException(mensaje);
        assertFalse(newEntity.equals(object));
        boolean a = newEntity.getClass().equals(object.getClass());
        assertEquals(a, newEntity.equals(object));

        assertTrue(newEntity.equals(newEntity));
        assertEquals(newEntity.hashCode(), newEntity.hashCode());

        BaseEntity newEntity2 = (BaseEntity) factory.manufacturePojo(ObjetivoEntity.class);
        newEntity2.setId(null);
        assertFalse(newEntity.equals(newEntity2));
        assertFalse(newEntity2.equals(newEntity));
        assertEquals(newEntity2.hashCode(), newEntity2.hashCode());

    }

    @Test
    public void pruebaExepcion() {
        String mensaje = factory.manufacturePojo(String.class);
        Throwable cause = factory.manufacturePojo(Throwable.class);

        try {
            throw new BusinessLogicException();
        } catch (BusinessLogicException e) {
            assertTrue(e instanceof Exception);
        }

        try {
            throw new BusinessLogicException(mensaje);
        } catch (BusinessLogicException e) {
            assertEquals(mensaje, e.getMessage());
        }

        try {
            throw new BusinessLogicException(cause);
        } catch (BusinessLogicException e) {
            assertEquals(cause, e.getCause());
        }

        try {
            throw new BusinessLogicException(mensaje, cause);
        } catch (BusinessLogicException e) {
            assertEquals(mensaje, e.getMessage());
            assertEquals(cause, e.getCause());
        }
    }

    @Test
    public void OtherExceptionTest() {
        Long id = factory.manufacturePojo(Long.class);

        try {
            throw new NoExisteException();
        } catch (NoExisteException e) {
            assertTrue(e instanceof WebApplicationException);
        }
        
        try{
             throw new NoExisteException(id);
        } catch (NoExisteException e) {
            assertEquals(e.getResponse().getStatus(), 404);
        }
        
        try{
             throw new NoExisteException(id,id);
        } catch (NoExisteException e) {
            assertEquals(e.getResponse().getStatus(), 404);
        }
    }
}
