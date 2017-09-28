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

    @Inject
    private RutinaLogic rutinaLogic;

    public List<EjercicioEntity> findAll(long idRutina) throws BusinessLogicException {
        return rutinaLogic.find(idRutina).getEjercicios();
    }

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

    public EjercicioEntity create(long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        entity.setRutina(rutinaLogic.find(idRutina));
        return create(entity);
    }

    public void remove(long idRutina, long id) throws BusinessLogicException {
        EjercicioEntity ent = find(idRutina, id);
        rutinaLogic.find(idRutina).getEjercicios().remove(ent);
        remove(id);
    }
}
