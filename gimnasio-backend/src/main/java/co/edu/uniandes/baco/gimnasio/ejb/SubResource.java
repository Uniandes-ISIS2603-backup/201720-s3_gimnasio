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
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author camilo
 * @param <R>
 * @param <S>
 */
public abstract class SubResource<R extends BaseEntity, S extends BaseEntity> extends BaseLogic<S> {
    protected BaseLogic<R> logic;
    private Function<R, List<S>> list;
    private BiConsumer<S, R> set;
    private Class<S> clase;

    public SubResource() {
        //para glasfish
    }

    public SubResource(BasePersistence<S> persistence, BaseLogic<R> logic, final Function<R, List<S>> list, final BiConsumer<S, R> set) {
        this.persistence = persistence;
        this.clase = persistence.getClase();
        this.logic = logic;
        this.list = list;
        this.set = set;
    }

    public List<S> findAll(long id) throws BusinessLogicException {
        return list.apply(logic.find(id));
    }

    public S find(long id, long idSub) throws BusinessLogicException {
        try {
            S s = clase.newInstance();
            s.setId(idSub);
            List<S> lista = findAll(id);
            int ind = lista.indexOf(s);
            if (ind < 0) {
                throw new NoExisteException(id, idSub);
            }
            return lista.get(ind);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SubResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessLogicException(ex.getMessage());
        }
    }

    public S update(Long id, S s) throws BusinessLogicException {
        try {
            S old = find(id, s.getId());
            for (Field field : s.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ManyToMany.class) || field.isAnnotationPresent(ManyToOne.class)) {
                    field.setAccessible(true);
                    field.set(s, field.get(old));
                }
            }
            update(s);
            return old;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BaseLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public S create(long id, S entity) throws BusinessLogicException {
        R r = logic.find(id);
        set.accept(entity, r);
        return create(entity);
    }

    public void remove(long idUsuario, long id) throws BusinessLogicException {
        S ent = find(idUsuario, id);
        findAll(idUsuario).remove(ent);
        remove(id);
    }
}
