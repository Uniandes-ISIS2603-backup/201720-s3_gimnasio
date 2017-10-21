/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EjercicioHechoLogic extends SubResource<EjercicioInstanciaEntity, EjercicioHechoEntity> {

    public EjercicioHechoLogic() {
        super();
    }

    @Inject
    public EjercicioHechoLogic(BasePersistence<EjercicioHechoEntity> persistence, EjercicioInstanciaLogic logic) {
        super(persistence, logic, EjercicioInstanciaEntity::getEjerciciosHechos, EjercicioHechoEntity::setEjercicios);
    }

    @Override
    public EjercicioHechoEntity update(EjercicioHechoEntity entity) throws BusinessLogicException {
        EjercicioHechoEntity old = find(entity.getId());
        entity.setAtributos(old.getAtributos());
        return super.update(entity);
    }

    @Override
    public EjercicioHechoEntity update(Long id, EjercicioHechoEntity s) throws BusinessLogicException {
        EjercicioHechoEntity old = find(s.getId());
        if (!old.getEjercicios().getId().equals(id)) {
            throw new NoExisteException(old.getEjercicios().getId(), id);
        }
        s.setAtributos(old.getAtributos());
        s.setEjercicios(old.getEjercicios());
        return super.update(s);
    }
}
