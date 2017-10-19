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
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc.bojaca
 * @param <R>
 * @param <S>
 */
public class Search <R extends BaseEntity, S extends BaseEntity> extends BaseLogic<R>{
    protected Function<R,List<S>> list;
    protected Class<S> clase;

    public Search() {
        super();
    }
    
    public Search(BasePersistence<R> persistence,final Function<R,List<S>> list,Class<S> clase) {
        super(persistence);
        this.list=list;
        this.clase=clase;
    }
    
    public List<S> findAll(long id) throws BusinessLogicException {
        return list.apply(find(id));
    }
    
     public S find(long id,long idSub) throws BusinessLogicException {
        try {
            List<S> lista=findAll(id);
            S s=clase.newInstance();
            s.setId(idSub);
            int ind=lista.indexOf(s);
            if(ind<0)
                throw new NoExisteException(id,idSub);
            return lista.get(ind);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
