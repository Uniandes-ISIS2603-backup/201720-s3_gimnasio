/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author ce.robles/ Carlos Eduardo Robles 201617129
 **/
@Stateless
public class TipoMedidaPersistence 
{
    
    private static final Logger LOGGER = Logger.getLogger(TipoMedidaPersistence.class.getName());
    @PersistenceContext(unitName = "tipoMedidaPU")
    protected EntityManager em;
    
    /**
     * Crea un TipoMedida
     * @param entity TipoMedidda que se creara en la BD.
     * @return Retorna la entidad creada con id dado por la BD.
     */
    public TipoMedidaEntity create (TipoMedidaEntity entity)
    {
        LOGGER.info("Creando un tipoMedida");
        em.persist(entity);
        return entity;
    }
    
    /**
     * Actualiza un TipoMedida
     * @param entity TipoMedida al que se le actualiza la informacion segun su id.
     * @return  Retorna el objeto con los cambios realizados.
     */
    public TipoMedidaEntity update(TipoMedidaEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando tipoMedida con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un TipoMedida de la BD segun el id dado por parametro.
     * @param id Id del TipoMedida a borrar.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando tipoMedida con id={0}", id);
        TipoMedidaEntity entity = em.find(TipoMedidaEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca en la BD si existe algun TipoMedida con el id ingresado por parametro.
     * @param id Id del TipoMedida a encontrar.
     * @return Id del TipoMedida a encontrar.
     */
     public TipoMedidaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando tipoMedida con id={0}", id);
        return em.find(TipoMedidaEntity.class, id);
    }
     
     /**
      * Retorna una lista con todos los objetos TipoMedida de la BD.
      * @return Retorna una lista con todos los objetos TipoMedida de la BD.
      */
     public List<TipoMedidaEntity> findAll() {
        LOGGER.info("Consultando todas los TipoMedida");
        TypedQuery query = em.createQuery("select u from TipoMedidaEntity u", TipoMedidaEntity.class);
        return query.getResultList();
    }  
     
}
