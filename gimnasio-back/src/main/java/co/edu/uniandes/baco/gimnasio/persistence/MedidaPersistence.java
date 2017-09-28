package co.edu.uniandes.baco.gimnasio.persistence;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class MedidaPersistence extends BasePersistence<MedidaEntity>{
    
    public MedidaPersistence() {
        super(MedidaEntity.class);
    }
}
