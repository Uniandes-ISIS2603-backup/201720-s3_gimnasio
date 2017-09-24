package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity> {
    
    @Override
    public EjercicioEntity create(EjercicioEntity entity) throws Exception {
        if (entity.getTipo() == null) {
            throw new BusinessLogicException("no se puede agregar un objeto sin categoria(existe: no_pertenece)");
        }
        return super.create(entity);        
    }
    
    public List<ObjetivoEntity> findAllObjetivos(Long id) throws Exception {
        return persistence.find(id).getObjetivos();        
    }
    
    public ObjetivoEntity findObjetivo(Long idEjercicio, Long id) throws Exception {
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)return null;
        return list.get(ind);
    }
    
    public ObjetivoEntity createObjetivo(Long idEjercicio, Long id) throws Exception{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        find(idEjercicio).getObjetivos().add(aux);
        return findObjetivo(idEjercicio, id);
    }
    
    public void removeObejtivo(Long idEjercicio, Long id) throws Exception{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        find(idEjercicio).getObjetivos().remove(aux);
    }
}
