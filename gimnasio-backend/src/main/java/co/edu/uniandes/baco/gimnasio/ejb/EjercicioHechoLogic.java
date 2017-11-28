/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.robles
 */
@Stateless
public class EjercicioHechoLogic extends SubResource<EjercicioInstanciaEntity, EjercicioHechoEntity> {

    //----------------------------------------------------------------------------------------
    //---------------------------------Constructores------------------------------------------
    //----------------------------------------------------------------------------------------
    public EjercicioHechoLogic() {
        super();
    }

    @Inject
    public EjercicioHechoLogic(BasePersistence<EjercicioHechoEntity> persistence, EjercicioInstanciaLogic logic) {
        super(persistence, logic, EjercicioInstanciaEntity::getEjerciciosHechos, EjercicioHechoEntity::setEjercicios);
    }

    @Override
    public EjercicioHechoEntity create(long id, EjercicioHechoEntity entity) throws BusinessLogicException {
        EjercicioHechoEntity ret = super.create(id, entity);
        ((EjercicioInstanciaLogic) logic).calcularCumplimiento(ret.getEjercicios());
        return ret;
    }

    @Override
    public EjercicioHechoEntity update(EjercicioHechoEntity entity) throws BusinessLogicException {
        EjercicioHechoEntity ret = super.update(entity);
        ((EjercicioInstanciaLogic) logic).calcularCumplimiento(ret.getEjercicios());
        return ret;
    }
}
