package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author ce.robles
 */
@Stateless
public class MedicionMaquinaPersistence extends BasePersistence<MedicionMaquinaEntity> {

    public MedicionMaquinaPersistence() {
        super(MedicionMaquinaEntity.class);
    }
}
