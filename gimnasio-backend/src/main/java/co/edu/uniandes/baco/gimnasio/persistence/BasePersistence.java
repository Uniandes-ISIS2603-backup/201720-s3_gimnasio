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
    protected final Class<T> entityClass;
    protected static Logger logger;
    /**
     * definicion unidad de persistencia
     */
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager manager;
    
    /**
     * metodo contructor de la clase base
     * @param entityClass 
     */
    public BasePersistence(Class<T> entityClass) {
        this.entityClass = entityClass;
        logger=Logger.getLogger(entityClass.getName());
    }
    /**
     * metodo de persitencia nuevo entity
     * @param entity 
     * @return el entity persistido
     */
    @Override
    public T create(final T entity){
        logger.log(Level.INFO, "Creando un {0} nuevo", entityClass.getSimpleName());
        manager.persist(entity);
        manager.flush();
        return entity;
    }
    /**
     * metodo que encuentra un entity persistido
     * @param id del entity
     * @return se retorna el enity encontrado null si no se encuentra
     */
    @Override
    public T find(final Long id){
        logger.log(Level.INFO, "Consultando {0} con id={1}", new Object[]{entityClass.getSimpleName(), id});
        return manager.find(entityClass, id);
    }
    /**
     * metodo que devuelve todas las entitys de la base de datos
     * @return lista con todos los entitys encontrados null si no hay niguno
     */
    @Override
    public List<T> findAll(){
        logger.log(Level.INFO, "Consultando todas los {0}s", entityClass.getSimpleName());
        TypedQuery query = manager.createQuery("select u from "+entityClass.getName()+" u", entityClass);
        return query.getResultList();
    }
    /**
     * metodo para realizar update a una entity
     * @param entity que se quiere actulizar
     * @return el nuevo entity
     */
    @Override
    public T update(final T entity){
        logger.log(Level.INFO, "Actualizando {0} con id={1}", new Object[]{entityClass.getSimpleName(), entity.getId()});
        T uptades=manager.merge(entity);
        manager.flush();
        return uptades;
    }
    /**
     * metodo que elimina un entity de la base de datos
     * @param id el identificador del entity
     */
    @Override
    public void delete(Long id){
        logger.log(Level.INFO, "Borrando {0} con id={1}", new Object[]{entityClass.getSimpleName(), id});
        T entity=find(id);
        manager.remove(entity);
        manager.flush();
    }
    
    public Class<T> getClase(){
        return entityClass;
    }
}
