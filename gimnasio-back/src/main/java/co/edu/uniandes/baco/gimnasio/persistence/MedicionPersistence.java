/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedicionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ce.robles/ Carlos Eduardo Robles 201617129
 */
@Stateless
public class MedicionPersistence     
{    
    private static final Logger LOGGER = Logger.getLogger(MedicionPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    
    /**
     * Crea una Medicion.
     * @param entity Medicion que se creara en la BD.
     * @return Retorna la entidad creada con id dado por la BD.
     */
    public MedicionEntity create (MedicionEntity entity)
    {
        LOGGER.info("Creando una medicion");
        em.persist(entity);
        return entity;
    }
    
    /**
     * Actualiza un Medicion
     * @param entity Medicion a la que se le actualiza la informacion segun su id.
     * @return Retorna el objeto con los cambios realizados.
     */
    public MedicionEntity update(MedicionEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando medicion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un Medicion de la BD segun el id dado por parametro.
     * @param id Id del Medicion a borrar.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando medicion con id={0}", id);
        MedicionEntity entity = em.find(MedicionEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca en la BD si existe alguna Medicion con el id ingresado por parametro.
     * @param id Id del Medicion a encontrar.
     * @return Id del Medicion a encontrar.
     */
     public MedicionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medicion con id={0}", id);
        return em.find(MedicionEntity.class, id);
    }
     
     /**
      * Retorna una lista con todos los objetos Medicion de la BD.
      * @return Retorna una lista con todos los objetos Medicion de la BD.
      */
     public List<MedicionEntity> findAll() {
        LOGGER.info("Consultando todas los Medicion");
        TypedQuery query = em.createQuery("select u from MedicionEntity u", MedicionEntity.class);
        return query.getResultList();
    } 
}
