/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioInstanciaLogic extends BaseLogic<EjercicioInstanciaEntity>{

    /**
     * injecion de la logica de rutina
     */
    private RutinaLogic rutinaLogic;
    private EjercicioLogic ejercicioLogic;
    
    public EjercicioInstanciaLogic() {
        super();
    }

    @Inject public EjercicioInstanciaLogic(RutinaLogic rutinaLogic, EjercicioLogic ejercicioLogic, BasePersistence<EjercicioInstanciaEntity> persistence) {
        super(persistence);
        this.rutinaLogic = rutinaLogic;
        this.ejercicioLogic = ejercicioLogic;
    }
    
    public EjercicioEntity findEjercicio(long idRutina, long id) throws BusinessLogicException{
        return find(idRutina, id).getEjercicio();
    }
    
    public List<EjercicioInstanciaEntity> findAll(long idRutina) throws BusinessLogicException {
        return rutinaLogic.find(idRutina).getEjercicios();
    }

    /**
     * metodo que encuentra un ejercicio en una rutina
     *
     * @param idRutina id de la rutina
     * @param id del ejercicio
     * @return el ejercicio buscado
     * @throws BusinessLogicException si la rutina o el ejercicio no existen
     */
    public EjercicioInstanciaEntity find(long idRutina, long id) throws BusinessLogicException {
        EjercicioInstanciaEntity ent = new EjercicioInstanciaEntity();
        ent.setId(id);
        List<EjercicioInstanciaEntity> list = rutinaLogic.find(idRutina).getEjercicios();
        int ind = list.indexOf(ent);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }

    /**
     * metodo para actulizar un ejercicio en una rutina
     *
     * @param idRutina id de la rutina
     * @param entity ejercicio a actulizar
     * @return el ejercicio actulizado
     * @throws BusinessLogicException si la rutino o el ejercicio no existen
     */
    public EjercicioInstanciaEntity update(Long idRutina, EjercicioInstanciaEntity entity) throws BusinessLogicException {
        EjercicioInstanciaEntity old = find(entity.getId());
        if (!old.getRutina().getId().equals(idRutina)) {
            throw new NoExisteException(idRutina);
        }
        entity.setRutina(old.getRutina());
        entity.setEjercicio(old.getEjercicio());
        return update(entity);
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
        entity.setRutina(rutinaLogic.find(idRutina));
        entity.setEjercicio(ejercicioLogic.find(idEjercicio));
        return create(entity);
    }

    /**
     * metodo que elimina un ejercicio de una rutina
     *
     * @param idRutina id de la rutina
     * @param id del ejercicioi
     * @throws BusinessLogicException si la rutina o el ejercicio no existen
     */
    public void remove(long idRutina, long id) throws BusinessLogicException {
        EjercicioInstanciaEntity ent = find(idRutina, id);
        if (ent == null) {
            throw new NoExisteException(id);
        }
        rutinaLogic.find(idRutina).getEjercicios().remove(ent);
        remove(id);
    }
}
