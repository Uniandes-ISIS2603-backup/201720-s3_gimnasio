/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity>{
    @Override
    public ObjetivoEntity create(ObjetivoEntity entity)throws BusinessLogicException{
        if(null!=((ObjetivoPersistence)persistence).findByTipo(entity.getTipo()))
            throw new BusinessLogicException("ya esiste un objetivo con ese tipo");
        return super.create(entity); 
    }
    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    @Inject
    private EjercicioLogic ejercicioLogic;
    
    public List<ObjetivoEntity> findAllObjetivosEjercicio(Long id) throws BusinessLogicException{
        return ejercicioLogic.find(id).getObjetivos();        
    }
    
    public ObjetivoEntity findObjetivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=ejercicioLogic.find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public ObjetivoEntity createObjetivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        ejercicioLogic.find(idEjercicio).getObjetivos().add(aux);
        return aux;
    }
    
    public void removeObejtivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=ejercicioLogic.find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
    //-----------------------------------
    // USUSARIO
    //-----------------------------------
    @Inject
    private UsuarioLogic usuarioLogic;
    
    public List<ObjetivoEntity> findAllObjetivosUsuario(Long id) throws BusinessLogicException{
        return usuarioLogic.find(id).getObjetivos();        
    }
    
    public ObjetivoEntity findObjetivoUsuario(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=usuarioLogic.find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public ObjetivoEntity createObjetivoUsuario(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        usuarioLogic.find(idEjercicio).getObjetivos().add(aux);
        return aux;
    }
    
    public void removeObejtivoUsuario(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=usuarioLogic.find(idEjercicio).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
}
