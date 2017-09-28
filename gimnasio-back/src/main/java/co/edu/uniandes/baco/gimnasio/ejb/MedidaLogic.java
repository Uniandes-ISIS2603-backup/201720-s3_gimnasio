/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */

@Stateless

public class MedidaLogic extends BaseLogic<MedidaEntity> {
    @Inject
    private EstadoLogic elogic;
    @Inject
    private TipoMedidaLogic tipoMedidaLogic;

    public MedidaEntity create(Long idEstado, MedidaEntity entity,Long idTipoMedida) throws BusinessLogicException {
        EstadoEntity estado = elogic.find(idEstado);
        TipoMedidaEntity parte = tipoMedidaLogic.find(idTipoMedida);
        if(parte.isAutomatico())
            throw new BusinessLogicException("no se puede asociar una medidcion manual a una medida automatica");
        entity.setEstado(estado);
        MedidaEntity est=create(entity);
        entity.setParte(parte);
        return est;
    }

    public List<MedidaEntity> findAll(Long Idestado) throws BusinessLogicException {
        return elogic.find(Idestado).getMedidas();
    }

    public MedidaEntity find(Long idEstado, Long id) throws BusinessLogicException {
        MedidaEntity ent = new MedidaEntity();
        ent.setId(id);
        List<MedidaEntity> list = elogic.find(id).getMedidas();
        int ind = list.indexOf(ent);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }

    public MedidaEntity update(Long idEstado, MedidaEntity entity) throws BusinessLogicException {
        MedidaEntity old = find(entity.getId());
        if (!old.getEstado().getId().equals(idEstado)) {
            throw new NoExisteException(idEstado);
        }
        entity.setEstado(old.getEstado());
        return persistence.update(entity);
    }
    
     public void remove(long idEstado,long id) throws BusinessLogicException {
        MedidaEntity ent=find(idEstado,id);
        elogic.find(idEstado).getMedidas().remove(ent);
        remove(id);
    }
}
