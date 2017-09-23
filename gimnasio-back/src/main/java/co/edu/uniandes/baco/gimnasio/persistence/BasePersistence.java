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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * @author jc.bojaca
 * @param <T>
 */
@Stateless
public abstract class BasePersistence<T extends BaseEntity> implements CRUD<T> {
    private transient final Class entityClass;
    protected static Logger LOGGER;
    
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager manager;
    
    public BasePersistence(Class entityClass) {
        this.entityClass = entityClass;
        LOGGER=Logger.getLogger(entityClass.getName());
    }
    
    @Override
    public T create(final T entity){
        LOGGER.log(Level.INFO, "Creando un {0} nuevo", entityClass.getCanonicalName());
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public T find(final Long id){
        LOGGER.log(Level.INFO, "Consultando {0} con id={1}", new Object[]{entityClass.getCanonicalName(), id});
        return (T) manager.find(entityClass, id);
    }
    
    @Override
    public List<T> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas los {0}s", entityClass.getCanonicalName());
        @SuppressWarnings("JPQLValidation")
        TypedQuery query = manager.createQuery("select u from "+entityClass.getName()+" u", entityClass);
        return query.getResultList();
    }

    @Override
    public T update(final T entity) {
        LOGGER.log(Level.INFO, "Actualizando {0} con id={1}", new Object[]{entityClass.getCanonicalName(), entity.getId()});
        T uptades=manager.merge(entity);
        manager.flush();
        return uptades;
    }

    @Override
    public void delete(Long id){
        LOGGER.log(Level.INFO, "Borrando {0} con id={1}", new Object[]{entityClass.getCanonicalName(), id});
        T entity=find(id);
        manager.remove(entity);
        manager.flush();
    }
}
