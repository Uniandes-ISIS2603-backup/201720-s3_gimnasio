package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import javax.ejb.Stateless;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity>{

    @Override
    public EjercicioEntity create(EjercicioEntity entity) throws Exception {
        if(entity.getTipo()==null)
            throw new BusinessLogicException("no se puede agregar un objeto sin categoria(existe: no_pertenece)");
        return super.create(entity); 
    }
    
}
