/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
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

    public MedidaEntity create(Long idEstado, MedidaEntity entity) throws BusinessLogicException {
        EstadoEntity estado = elogic.find(idEstado);
        entity.setEstado(estado);
        return create(entity);
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
}
