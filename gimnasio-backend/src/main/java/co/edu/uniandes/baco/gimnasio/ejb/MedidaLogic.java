package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class MedidaLogic extends SubResource<EstadoEntity, MedidaEntity> {

    private TipoMedidaLogic tipoMedidaLogic;

    public MedidaLogic() {
        super();
    }

    @Inject
    public MedidaLogic(EstadoLogic logic, TipoMedidaLogic tipoMedidaLogic, BasePersistence<MedidaEntity> persistence) {
        super(persistence, logic, EstadoEntity::getMedidas, MedidaEntity::setEstado);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }

    /**
     * metodo que crea o asocia una medida a un estado
     *
     * @param idEstado id del estado
     * @param entity la medida a asociar
     * @param idTipoMedida el tipod e medida de la medida
     * @return la medida creada
     * @throws BusinessLogicException si estado no existe
     */
    public MedidaEntity create(Long idEstado, MedidaEntity entity, Long idTipoMedida) throws BusinessLogicException {
        TipoMedidaEntity parte = tipoMedidaLogic.find(idTipoMedida);
        if (parte.isAutomatico()) {
            throw new BusinessLogicException("no se puede asociar una medidcion manual a una medida automatica");
        }
        MedidaEntity est = create(idEstado, entity);
        entity.setParte(parte);
        return est;
    }
}
