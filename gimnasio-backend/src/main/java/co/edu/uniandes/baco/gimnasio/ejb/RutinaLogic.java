/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.RutinaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class RutinaLogic extends SubResource<UsuarioEntity, RutinaEntity> {

    public RutinaLogic() {
        super();
    }

    @Inject
    public RutinaLogic(UsuarioLogic logic, BasePersistence<RutinaEntity> persistence) {
        super(persistence, logic, UsuarioEntity::getRutinas, RutinaEntity::setUsuario);
    }

    public RutinaEntity getLast(long idUsuario) {
        return ((RutinaPersistence) persistence).last(idUsuario);
    }
}
