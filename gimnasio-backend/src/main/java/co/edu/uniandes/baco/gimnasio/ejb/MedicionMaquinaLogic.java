package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
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
        super(persistence, ejercicioHechoLogic, EjercicioHechoEntity::getMedicionMaquinaEnt, MedicionMaquinaEntity::setEjercicioHecho);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }

    public MedicionMaquinaEntity create(Long idEjercicioHecho, MedicionMaquinaEntity entity, Long idTipoMedida) throws BusinessLogicException {
        TipoMedidaEntity tipoMedida = tipoMedidaLogic.find(idTipoMedida);
        MedicionMaquinaEntity est = create(idEjercicioHecho, entity);
        entity.setTipoMedida(tipoMedida);
        return est;
    }

    @Override
    public MedicionMaquinaEntity update(Long idEjercicioHecho, MedicionMaquinaEntity entity) throws BusinessLogicException {
        MedicionMaquinaEntity old = find(entity.getId());
        if (!old.getEjercicioHecho().getId().equals(idEjercicioHecho)) {
            throw new NoExisteException(idEjercicioHecho);
        }

        entity.setEjercicioHecho(old.getEjercicioHecho());
        entity.setTipoMedida(old.getTipoMedida());
        return persistence.update(entity);
    }   
}
