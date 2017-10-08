/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package co.edu.uniandes.baco.gimnasio.ejb;
 
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
 import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
 import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
 import javax.ejb.Stateless;
import javax.inject.Inject;
 
 /**
  * @author m.sicard10
  */
 @Stateless
 public class UsuarioLogic extends BaseLogic<UsuarioEntity> {

    public UsuarioLogic() {
        super();
    }
    
    @Inject UsuarioLogic(BasePersistence<UsuarioEntity>persistence) {
        super(persistence);
    }
    
    @Override
    public UsuarioEntity update(UsuarioEntity entity) throws BusinessLogicException {
        UsuarioEntity ent=find(entity.getId());
        entity.setEntrenadores(ent.getEntrenadores());
        entity.setEstados(ent.getEstados());
        entity.setRutinas(ent.getRutinas());
        entity.setObjetivos(ent.getObjetivos());
        return super.update(entity); 
    }
    
    //-----------------------------------
    // OBJETIVO
    //-----------------------------------
    
    /**
     * metodo apra encontrar todos los usuarios
     * @param id del usuario a buscar
     * @return los objetivos de ese usuario
     * @throws BusinessLogicException si el usuario no existe
     */
    public List<ObjetivoEntity> findAllObjetivo(Long id) throws BusinessLogicException{
        return find(id).getObjetivos();        
    }
    
    /**
     * metodo apra encontrar un objetivo en un usuario
     * @param idUsuario id del ejercicio
     * @param id del objetivo
     * @return el objetivo deseado
     * @throws BusinessLogicException si el usuario o el objetivo no existen 
     */
    public ObjetivoEntity findObjetivo(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idUsuario).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(idUsuario,id);
        return list.get(ind);
    }
    /**
     * metodo que asocia un objetico a un usuario 
     * @param idUsuario del usuario
     * @param id id del objetivo
     * @return el objetivo creado
     * @throws BusinessLogicException si el usuario o objetivo no existe 
     */
    public ObjetivoEntity createObjetivo(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        find(idUsuario).getObjetivos().add(aux);
        return findObjetivo(idUsuario, id);
    }
    
    /**
     *  metodo para eliminar un objetivo a un usuario
     * @param idUsuario del Usuario
     * @param id del objetivo
     * @throws BusinessLogicException si el usuario o el objetivo no existen 
     */
    public void removeObejtivo(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        find(idUsuario).getObjetivos().remove(aux);
    }
 }
