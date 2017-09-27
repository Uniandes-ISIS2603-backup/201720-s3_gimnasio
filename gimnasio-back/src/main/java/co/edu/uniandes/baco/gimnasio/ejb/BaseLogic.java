/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 * @param <T>
 */
public abstract class BaseLogic<T extends BaseEntity>{
    @Inject
    protected BasePersistence<T> persistence;
    
     public T find(Long id)throws BusinessLogicException{
        T t= persistence.find(id);
        if(t==null)
            throw new NoExisteException(id);
        return t;
    }
    
     public T create(T entity)throws BusinessLogicException{
        return persistence.create(entity);
    }
    
    public T update(T entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())==null)
             throw new NoExisteException(entity.getId());
        return persistence.update(entity);
    }
    
    public void remove(Long id)throws BusinessLogicException{
         if(persistence.find(id)==null)
             throw new NoExisteException(id);
        persistence.delete(id);
    }
    
    public List<T> findAll()throws BusinessLogicException{
        return persistence.findAll();
    }
}
