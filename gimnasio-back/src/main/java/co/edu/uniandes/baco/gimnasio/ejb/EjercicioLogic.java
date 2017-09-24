package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
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
    ObjetivoLogic objetivoLogic;
    
    @Inject
    ParteDelCuerpoLogic parteDelCuerpoLogic;
    
    @Inject
    MaquinaLogic maquinaLogic;
    
    public List<ObjetivoEntity> findAllObjetivos(Long id){
        return persistence.find(id).getObjetivos();        
    }
    //------------
    //objetivos
    //------------
    public ObjetivoEntity findObjetivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public ObjetivoEntity createObjetivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = objetivoLogic.find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        find(idEjercicio).getObjetivos().add(aux);
        return aux;
    }
    
    public void removeObejtivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
    //------------
    //maquinas
    //------------
     public List<MaquinaEntity> findAllMaquinas(Long id){
        return persistence.find(id).getMaquinas();        
    }
    
    public MaquinaEntity findMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        List<MaquinaEntity> list=find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public MaquinaEntity createMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = maquinaLogic.find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        find(idEjercicio).getMaquinas().add(aux);
        return aux;
    }
    
    public void removeMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        List<MaquinaEntity> list=find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
    //------------
    //partesDelcuerpo
    //------------
     public List<ParteDelCuerpoEntity> findAllParteDelCuerpos(Long id){
        return persistence.find(id).getPartesDelCuerpo();        
    }
    
    public ParteDelCuerpoEntity findParteDelCuerpo(Long idEjercicio, Long id) throws BusinessLogicException{
        ParteDelCuerpoEntity aux = new ParteDelCuerpoEntity();
        aux.setId(id);
        List<ParteDelCuerpoEntity> list=find(idEjercicio).getPartesDelCuerpo();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public ParteDelCuerpoEntity createParteDelCuerpo(Long idEjercicio, Long id) throws BusinessLogicException{
        ParteDelCuerpoEntity aux = parteDelCuerpoLogic.find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        find(idEjercicio).getPartesDelCuerpo().add(aux);
        return aux;
    }
    
    public void removeParteDelCuerpo(Long idEjercicio, Long id) throws BusinessLogicException{
        ParteDelCuerpoEntity aux = new ParteDelCuerpoEntity();
        aux.setId(id);
        List<ParteDelCuerpoEntity> list=find(idEjercicio).getPartesDelCuerpo();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
}
