/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaPersistence {
      private static final Logger LOGGER = Logger.getLogger(MaquinaPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    
    /**
     * Crea una ｍａｑｕｉｎａ
     * @param entity ｍａｑｕｉｎａ que se creara en la BD.
     * @return Retorna la entidad creada con id dado por la BD.
     */
    public MaquinaEntity create (MaquinaEntity entity)
    {
        LOGGER.info("Creando una ｍａｑｕｉｎａ");
        em.persist(entity);
        return entity;
    }
    
    /**
     * Actualiza un Maquina
     * @param entity Maquina al que se le actualiza la informacion segun su id.
     * @return  Retorna el objeto con los cambios realizados.
     */
    public MaquinaEntity update(MaquinaEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando ｍａｑｕｉｎａ con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un ｍａｑｕｉｎａ de la BD segun el id dado por parametro.
     * @param id Id del ｍａｑｕｉｎａ a borrar.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando ｍａｑｕｉｎａ con id={0}", id);
        MaquinaEntity entity = em.find(MaquinaEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca en la BD si existe algun ｍａｑｕｉｎａ con el id ingresado por parametro.
     * @param id Id del Maquina a encontrar.
     * @return Id del Maquina a encontrar.
     */
     public MaquinaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando ｍａｑｕｉｎａ con id={0}", id);
        return em.find(MaquinaEntity.class, id);
    }
     
     /**
      * Retorna una lista con todos los objetos Maquina de la BD.
      * @return Retorna una lista con todos los objetos Maquina de la BD.
      */
     public List<MaquinaEntity> findAll() {
        LOGGER.info("Consultando todas las ｍａｑｕｉｎａ");
        TypedQuery query = em.createQuery("select u from MaquinaEntity u", MaquinaEntity.class);
        return query.getResultList();
    }  
}
