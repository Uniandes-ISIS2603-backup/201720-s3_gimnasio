/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class MedidaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(MedidaPersistence.class.getName());
     
     @PersistenceContext(unitName = "medidaPU")
     protected EntityManager em;
     
     
     // metodo para presistir una medida
     public MedidaEntity crete(MedidaEntity entity)
     {
     LOGGER.info("Creando una medida nueva");  
      em.persist(entity);
      LOGGER.info("Creando una medida nueva");
      return entity;
     }
     
     // metodo para ahcer update a una medida
     public MedidaEntity update(MedidaEntity entity)
   {
      LOGGER.info("update un estado"); 
      return em.merge(entity);
   }
   
     // metodo apra borrar una emdida
   public void delete(Long id)
   {
       MedidaEntity entity = em.find(MedidaEntity.class,id);
       em.remove(entity);
   }
   //metodo para encontrar una medida
   public MedidaEntity find(Long id)
   {
       return em.find(MedidaEntity.class, id);
   }

    MedidaEntity create(MedidaEntity newEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
