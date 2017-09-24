/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * @author jc.bojaca
 * @param <T>
 */
public abstract class BasePersistence<T extends BaseEntity> implements CRUD<T> {
    protected final Class entityClass;
    protected static Logger logger;
    
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager manager;
    
    public BasePersistence(Class entityClass) {
        this.entityClass = entityClass;
        logger=Logger.getLogger(entityClass.getName());
    }
    
    @Override
    public T create(final T entity){
        logger.log(Level.INFO, "Creando un {0} nuevo", entityClass.getSimpleName());
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public T find(final Long id){
        logger.log(Level.INFO, "Consultando {0} con id={1}", new Object[]{entityClass.getSimpleName(), id});
        return (T) manager.find(entityClass, id);
    }
    
    @Override
    public List<T> findAll(){
        logger.log(Level.INFO, "Consultando todas los {0}s", entityClass.getSimpleName());
        @SuppressWarnings("JPQLValidation")
        TypedQuery query = manager.createQuery("select u from "+entityClass.getName()+" u", entityClass);
        return query.getResultList();
    }

    @Override
    public T update(final T entity){
        logger.log(Level.INFO, "Actualizando {0} con id={1}", new Object[]{entityClass.getSimpleName(), entity.getId()});
        T uptades=manager.merge(entity);
        manager.flush();
        return uptades;
    }

    @Override
    public void delete(Long id){
        logger.log(Level.INFO, "Borrando {0} con id={1}", new Object[]{entityClass.getSimpleName(), id});
        T entity=find(id);
        manager.remove(entity);
        manager.flush();
    }
}
