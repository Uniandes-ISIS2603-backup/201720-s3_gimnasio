/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic {
    @Inject
    ObjetivoPersistence persistence;
    
    public ObjetivoEntity create(ObjetivoEntity entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())!=null)throw new BusinessLogicException("ya existe un ejercicio con el id");
        return persistence.create(entity);
    }
    
    public ObjetivoEntity find(long id){
        return persistence.find(id);
    }
    
    public ObjetivoEntity update(ObjetivoEntity entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())==null)throw new BusinessLogicException("no exixte un ejercicio con el id");
        return persistence.update(entity);
    }
    
    public void remove(long id){
        persistence.delete(id);
    }
    
    public List<ObjetivoEntity> findAll(){
        return persistence.findAll();
    }
}
