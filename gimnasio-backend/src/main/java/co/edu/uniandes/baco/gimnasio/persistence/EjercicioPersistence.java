package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import javax.ejb.Stateless;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioPersistence extends BasePersistence<EjercicioEntity> {

    public EjercicioPersistence() {
        super(EjercicioEntity.class);
    }
}
