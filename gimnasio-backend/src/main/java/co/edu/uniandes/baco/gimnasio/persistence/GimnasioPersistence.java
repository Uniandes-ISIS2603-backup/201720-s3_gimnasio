package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import javax.ejb.Stateless;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class GimnasioPersistence extends BasePersistence<GimnasioEntity> {

    public GimnasioPersistence() {
        super(GimnasioEntity.class);
    }
}
