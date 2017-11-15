/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic extends SubResource<UsuarioEntity, EstadoEntity> {

    public EstadoLogic() {
        super();
    }

    @Inject
    public EstadoLogic(UsuarioLogic logic, BasePersistence<EstadoEntity> persistence) {
        super(persistence, logic, UsuarioEntity::getEstados, EstadoEntity::setUsuario);
    }
}
