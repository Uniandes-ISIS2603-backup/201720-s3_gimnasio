package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity> {
    @Inject
    ObjetivoLogic objetivoLogic;
    
    @Override
    public EjercicioEntity create(EjercicioEntity entity) throws WebApplicationException,BusinessLogicException {
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
        if(ind<0)throw new WebApplicationException("El recurso con id=" + id + " no existe.", 404);
        return list.get(ind);
    }
    
    public ObjetivoEntity createObjetivo(Long idEjercicio, Long id) throws Exception{
        ObjetivoEntity aux = objetivoLogic.find(id);
        if(aux==null)throw new WebApplicationException("El recurso con id=" + id + " no existe.", 404);
        find(idEjercicio).getObjetivos().add(aux);
        return aux;
    }
    
    public void removeObejtivo(Long idEjercicio, Long id) throws Exception{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)throw new WebApplicationException("El recurso con id=" + id + " no existe.", 404);
        list.remove(aux);
    }
}
