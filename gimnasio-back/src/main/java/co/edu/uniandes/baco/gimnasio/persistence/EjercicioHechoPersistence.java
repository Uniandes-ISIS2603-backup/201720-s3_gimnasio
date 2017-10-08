package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import javax.ejb.Stateless;

/**
 *
 * @author ce.robles
 */
@Stateless
public class EjercicioHechoPersistence extends BasePersistence<EjercicioHechoEntity>{
    
    public EjercicioHechoPersistence() {
        super(EjercicioHechoEntity.class);
    }
    
}
