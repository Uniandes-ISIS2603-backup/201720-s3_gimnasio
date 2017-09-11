/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedidaUsuarioEntity;
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
public class MedidaUsuarioPersistence 
{
    
    private static final Logger LOGGER = Logger.getLogger(MedidaUsuarioPersistence.class.getName());
    //@PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    
    /**
     * Crea una MedidaUsuario.
     * @param entity MedidaUsuario que se creara en la BD.
     * @return Retorna la entidad creada con id dado por la BD.
     */
    public MedidaUsuarioEntity create (MedidaUsuarioEntity entity)
    {
        LOGGER.info("Creando una medidaUsuario");
        em.persist(entity);
        return entity;
    }
    
    /**
     * Actualiza un MedidaUsuario
     * @param entity MedidaUsuario a la que se le actualiza la informacion segun su id.
     * @return Retorna el objeto con los cambios realizados.
     */
    public MedidaUsuarioEntity update(MedidaUsuarioEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando medidaUsuario con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un MedidaUsuario de la BD segun el id dado por parametro.
     * @param id Id del MedidaUsuario a borrar.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando medidaUsuario con id={0}", id);
        MedidaUsuarioEntity entity = em.find(MedidaUsuarioEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca en la BD si existe alguna MedidaUsuario con el id ingresado por parametro.
     * @param id Id del MedidaUsuario a encontrar.
     * @return Id del MedidaUsuario a encontrar.
     */
     public MedidaUsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medidaUsuario con id={0}", id);
        return em.find(MedidaUsuarioEntity.class, id);
    }
     
     /**
      * Retorna una lista con todos los objetos MedidaUsuario de la BD.
      * @return Retorna una lista con todos los objetos MedidaUsuario de la BD.
      */
     public List<MedidaUsuarioEntity> findAll() {
        LOGGER.info("Consultando todas los MedidaUsuario");
        TypedQuery query = em.createQuery("select u from MedidaUsuarioEntity u", MedidaUsuarioEntity.class);
        return query.getResultList();
    } 
    
}
