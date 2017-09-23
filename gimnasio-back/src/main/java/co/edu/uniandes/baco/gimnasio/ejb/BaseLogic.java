/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jc.bojaca
 * @param <T>
 */
public abstract class BaseLogic<T extends BaseEntity>{
    @Inject
    protected BasePersistence<T> persistence;

     public T create(T entity){
        return persistence.create(entity);
    }
    
    public T find(long id){
        return persistence.find(id);
    }
    
    public T update(T entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())==null)
             throw new WebApplicationException("El recurso con id=" + entity.getId() + " no existe.", 404);
        return persistence.update(entity);
    }
    
    public void remove(long id){
         if(persistence.find(id)==null)
             throw new WebApplicationException("El recurso con id=" + id + " no existe.", 404);
        persistence.delete(id);
    }
    
    public List<T> findAll(){
        return persistence.findAll();
    }
}
