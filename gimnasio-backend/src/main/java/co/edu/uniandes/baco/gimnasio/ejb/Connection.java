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
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 * @param <R>
 * @param <S>
 */
public class Connection <R extends BaseEntity, S extends BaseEntity> extends Search<R, S>{
    public Connection() {
        super();
    }
    
    public Connection(BasePersistence<R> persistence,final Function<R,List<S>> list,Class<S> clase) {
        super(persistence, list, clase);
        this.clase=clase;
        this.list=list;
    }
     
    public S create(long id,long idSub) throws BusinessLogicException {
        try {
            S s = clase.newInstance();
            s.setId(idSub);
            List<S> lista = findAll(id);
            int ind = lista.indexOf(s);
            if (ind >= 0) {
                throw new BusinessLogicException("la conexion ya existe");
            }
            lista.add(s);
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
