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
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 * @param <R>
 * @param <S>
 */
public abstract class SubResource<R extends BaseEntity, S extends BaseEntity> extends BaseLogic<S>{
    private BaseLogic<R> logic;
    private Function<R,List<S>> list;
    private BiConsumer<S,R> set;
    private Class<S> clase;

    public SubResource() {
        super();
    }
    
    public SubResource(BasePersistence<S> persistence, BaseLogic<R> logic,final Function<R,List<S>> list,final BiConsumer<S,R> set) {
        this.persistence = persistence;
        this.clase=persistence.getClase();
        this.logic = logic;
        this.list=list;
        this.set=set;
    }
    
    public List<S> findAll(long id) throws BusinessLogicException {
        return list.apply(logic.find(id));
    }
    
     public S find(long id,long idSub) throws BusinessLogicException {
        try {
            S s=clase.newInstance();
            s.setId(idSub);
            List<S> lista=findAll(id);
            int ind=lista.indexOf(s);
            if(ind<0)
                throw new NoExisteException(id,idSub);
            return lista.get(ind);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SubResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public abstract S update(Long id,S s)throws BusinessLogicException;
    
    public S create(long id,S entity) throws BusinessLogicException {
        R r=logic.find(id);
        set.accept(entity, r);
        return create(entity);
    }
    
    public void remove(long idUsuario,long id) throws BusinessLogicException {
        S ent=find(idUsuario,id);
        findAll(id).remove(ent);
        remove(id);
    }
}
