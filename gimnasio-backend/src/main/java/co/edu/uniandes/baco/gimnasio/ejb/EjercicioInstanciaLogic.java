/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioInstanciaLogic extends SubResource<RutinaEntity, EjercicioInstanciaEntity> {

    /**
     * injecion de la logica de rutina
     */
    private EjercicioLogic ejercicioLogic;

    public EjercicioInstanciaLogic() {
        super();
    }

    @Inject
    public EjercicioInstanciaLogic(RutinaLogic rutinaLogic, EjercicioLogic ejercicioLogic, BasePersistence<EjercicioInstanciaEntity> persistence) {
        super(persistence, rutinaLogic, RutinaEntity::getEjercicios, EjercicioInstanciaEntity::setRutina);
        this.ejercicioLogic = ejercicioLogic;
    }

    /**
     * metodo que crea o asocia un ejercicio a una rutina
     *
     * @param idRutina id de la rutina
     * @param entity el ejercicio
     * @param idEjercicio
     * @return el ejercicio creado
     * @throws BusinessLogicException si la rutina no existe
     */
    public EjercicioInstanciaEntity create(long idRutina, EjercicioInstanciaEntity entity, long idEjercicio) throws BusinessLogicException {
        EjercicioInstanciaEntity ans = create(idRutina, entity);
        ans.setEjercicio(ejercicioLogic.find(idEjercicio));
        return ans;
    }

    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    public EjercicioEntity findEjercicio(long idRutina, long id) throws BusinessLogicException {
        return find(idRutina, id).getEjercicio();
    }
}
