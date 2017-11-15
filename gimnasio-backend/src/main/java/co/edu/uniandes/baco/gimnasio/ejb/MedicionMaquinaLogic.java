package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
}
