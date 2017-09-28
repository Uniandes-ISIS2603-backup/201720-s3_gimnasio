package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity> {
/**
 * injecion de la logica de rutina
 */
@Inject
private RutinaLogic rutinaLogic;

public List<EjercicioEntity> findAll(long idRutina) throws BusinessLogicException {
  return rutinaLogic.find(idRutina).getEjercicios();
}
/**
 *  metodo que encuentra un ejercicio en una rutina
 * @param idRutina id de la rutina
 * @param id del ejercicio
 * @return el ejercicio buscado
 * @throws BusinessLogicException si la rutina o el ejercicio no existen
 */
public EjercicioEntity find(long idRutina, long id) throws BusinessLogicException {
  EjercicioEntity ent = new EjercicioEntity();
  ent.setId(id);
  List<EjercicioEntity> list = rutinaLogic.find(idRutina).getEjercicios();
  int ind = list.indexOf(ent);
   if (ind < 0) {
     throw new NoExisteException(id);
   }
  return list.get(ind);
}
/**
 * metodo para actulizar un ejercicio en una rutina
 * @param idRutina id de la rutina
 * @param entity ejercicio a actulizar
 * @return el ejercicio actulizado
 * @throws BusinessLogicException si la rutino o el ejercicio no existen
 */
    public EjercicioEntity update(Long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        EjercicioEntity old = find(entity.getId());
        if (!old.getRutina().getId().equals(idRutina)) {
            throw new NoExisteException(idRutina);
        }
        entity.setRutina(old.getRutina());
        entity.setObjetivos(old.getObjetivos());
        entity.setMaquinas(old.getMaquinas());
        return update(entity);
    }
/**
 * metodo que crea o asocia un ejercicio a una rutina
 * @param idRutina id de la rutina
 * @param entity el ejercicio
 * @return el ejercicio creado
 * @throws BusinessLogicException si la rutina no existe 
 */
    public EjercicioEntity create(long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        entity.setRutina(rutinaLogic.find(idRutina));
        return create(entity);
    }
/**
 * metodo que elimina un ejercicio de una rutina
 * @param idRutina id de la rutina
 * @param id del ejercicioi
 * @throws BusinessLogicException si la rutina o el ejercicio no existen 
 */
    public void remove(long idRutina, long id) throws BusinessLogicException {
        EjercicioEntity ent = find(idRutina, id);
        if(ent==null)
            throw new NoExisteException(id);
        rutinaLogic.find(idRutina).getEjercicios().remove(ent);
        remove(id);
    }
}
