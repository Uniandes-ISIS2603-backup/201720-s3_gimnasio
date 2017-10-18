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
 * @author camilo
 * @param <R>
 * @param <S>
 */
public class Connection <R extends BaseEntity, S extends BaseEntity> extends BaseLogic<R>{
    private Function<R,List<S>> list;
    private Class<S> clase;

    public Connection() {
        super();
    }
    
    public Connection(BasePersistence<R> persistence,final Function<R,List<S>> list,Class<S> clase) {
        super(persistence);
        this.clase=clase;
        this.list=list;
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
     
    public S create(long id,long idSub) throws BusinessLogicException {
        try {
            S s = clase.newInstance();
            s.setId(idSub);
            findAll(id).add(s);
            return  find(id, idSub);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void remove(long id,long idSub) throws BusinessLogicException {
        try {
            S s = clase.newInstance();
            s.setId(idSub);
            findAll(id).remove(s);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
