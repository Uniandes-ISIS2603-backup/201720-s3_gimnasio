package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic {
    @Inject
    EjercicioPersistence persistence;
    
    public EjercicioEntity create(EjercicioEntity entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())!=null)throw new BusinessLogicException("ya existe un ejercicio con el id");
        return persistence.create(entity);
    }
    
    public EjercicioEntity find(long id){
        return persistence.find(id);
    }
    
    public EjercicioEntity update(EjercicioEntity entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())==null)throw new BusinessLogicException("no exixte un ejercicio con el id");
        return persistence.update(entity);
    }
    
    public void remove(long id){
        persistence.delete(id);
    }
}
