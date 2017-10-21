/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
public class RutinaLogic extends SubResource<UsuarioEntity, RutinaEntity> {

    public RutinaLogic() {
        super();
    }

    @Inject
    public RutinaLogic(UsuarioLogic logic, BasePersistence<RutinaEntity> persistence) {
        super(persistence, logic, UsuarioEntity::getRutinas, RutinaEntity::setUsuario);
    }

    /**
     * actuliza una rutina de un uduario
     *
     * @param idUsuario id del usuario
     * @param entity rutina actulizada
     * @return la rutina actulizada
     * @throws BusinessLogicException
     */
    @Override
    public RutinaEntity update(Long idUsuario, RutinaEntity entity) throws BusinessLogicException {
        RutinaEntity old = find(entity.getId());
        if (!old.getUsuario().getId().equals(idUsuario)) {
            throw new NoExisteException(idUsuario);
        }
        entity.setUsuario(old.getUsuario());
        entity.setEjercicios(old.getEjercicios());
        return persistence.update(entity);
    }
}
