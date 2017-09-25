package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
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
    private ObjetivoLogic objetivoLogic;
    
    @Inject
    private ParteDelCuerpoLogic parteDelCuerpoLogic;
    
    @Inject
    private MaquinaLogic maquinaLogic;
    
    @Inject
    private RutinaLogic logic;

    public List<EjercicioEntity> findAll(long idRutina) throws BusinessLogicException {
        return logic.find(idRutina).getEjercicios();
    }
    
    public EjercicioEntity find(long idRutina,long id) throws BusinessLogicException {
       EjercicioEntity ent=new EjercicioEntity();
       ent.setId(id);
       List<EjercicioEntity> list=logic.find(idRutina).getEjercicios();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }

    public EjercicioEntity update(Long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        EjercicioEntity old=find(entity.getId());
        if(!old.getRutina().getId().equals(idRutina))
            throw new NoExisteException(idRutina);
        entity.setRutina(old.getRutina());
        entity.setObjetivos(old.getObjetivos());
        entity.setPartesDelCuerpo(old.getPartesDelCuerpo());
        entity.setMaquinas(old.getMaquinas());
        return persistence.update(entity);
    }
    
    

    public EjercicioEntity create(long idRutina,EjercicioEntity entity) throws BusinessLogicException {
        EjercicioEntity ent=create(entity);
        RutinaEntity rutina=logic.find(idRutina);
        ent.setRutina(rutina);
        rutina.getEjercicios().add(ent);
        return ent;
    }

    public void remove(long idRutina,long id) throws BusinessLogicException {
        EjercicioEntity ent=find(id);
        logic.find(idRutina).getEjercicios().remove(ent);
    }
    //------------
    //objetivos
    //------------
    public List<ObjetivoEntity> findAllObjetivos(Long id){
        return persistence.find(id).getObjetivos();        
    }
    
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
