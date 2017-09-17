/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class EstadoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EstadoPersistence.class.getName());
    
   @PersistenceContext(unitName = "gimnasioPU")
   protected EntityManager em;
   
   // metodo para crear un nuevo estado
   public EstadoEntity create(EstadoEntity entity)
   {
      LOGGER.info("Creando un estado nueva");  
      em.persist(entity);
      LOGGER.info("Creando un estado nueva");
      return entity;
   }
   // metodo para actulizar un estado
   public EstadoEntity update(EstadoEntity entity)
   {
      LOGGER.info("update un estado"); 
      return em.merge(entity);
   }
   
   // metodo para eliminar un estado
   public void delete(Long id)
   {
       EstadoEntity entity = em.find(EstadoEntity.class,id);
       em.remove(entity);
   }
   
   // metodo para encontrar un estado
   public EstadoEntity find(Long id)
   {
       return em.find(EstadoEntity.class, id);
   }
   public EstadoEntity findfecha(Date date)
   {
       return em.find(EstadoEntity.class, date);
   }
   
    
}
