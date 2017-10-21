package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.robles
 */
@Stateless
public class MedicionMaquinaLogic extends SubResource<EjercicioHechoEntity, MedicionMaquinaEntity> {

    private TipoMedidaLogic tipoMedidaLogic;

    public MedicionMaquinaLogic() {
        super();
    }

    @Inject
    public MedicionMaquinaLogic(EjercicioHechoLogic ejercicioHechoLogic, TipoMedidaLogic tipoMedidaLogic, BasePersistence<MedicionMaquinaEntity> persistence) {
        super(persistence, ejercicioHechoLogic, EjercicioHechoEntity::getAtributos, MedicionMaquinaEntity::setEjercicioEnt);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }

    public MedicionMaquinaEntity create(Long idEjercicioHecho, MedicionMaquinaEntity entity, Long idTipoMedida) throws BusinessLogicException {
        MedicionMaquinaEntity est = create(idEjercicioHecho, entity);
        est.setTipoMedida(tipoMedidaLogic.find(idTipoMedida));
        return est;
    }

    @Override
    public MedicionMaquinaEntity update(Long idEjercicioHecho, MedicionMaquinaEntity entity) throws BusinessLogicException {
        MedicionMaquinaEntity old = find(entity.getId());
        if (!old.getEjercicioEnt().getId().equals(idEjercicioHecho)) {
            throw new NoExisteException(idEjercicioHecho);
        }

        entity.setEjercicioEnt(old.getEjercicioEnt());
        entity.setTipoMedida(old.getTipoMedida());
        return persistence.update(entity);
    }
}
