/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ce.robles
 */
@Stateless
public class MedicionMaquinaPersistence
{   
    private static final Logger LOGGER = Logger.getLogger(MedicionMaquinaPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    
    protected EntityManager em;
    
    /**
     * Crea un nuevo objetivo de tipo MedicionMaquina y lo almacena en la BD.
     * @param entity objeto de tipo MedicionMaquina que se guardara en la BD.
     * @return retorna el objeto MedicionMaquina creado.
     */
    public MedicionMaquinaEntity create(MedicionMaquinaEntity entity) 
    {
        LOGGER.info("Creando una MedicionMaquina nuevo");
        em.persist(entity);
        return entity;
    }
    
    /**
     * Busca si existe un MedicionMaquina con el id dado por parametro en la BD y lo retorna. 
     * @param id Id del objeto MedicionMaquina a buscar.
     * @return objeto MedicionMaquina si lo encuentra en la DB. Null en cualquier otro caso.
     */
    public MedicionMaquinaEntity find(Long id) 
    {
        LOGGER.log(Level.INFO, "Consultando MedicionMaquina con id={0}", id);
        return em.find(MedicionMaquinaEntity.class, id);
    }
   
    /**
     * Retorna una lista con todos los objetos MedicionMaquina. 
     * @return Lista con objetos de tipo MedicionMaquina si encuentra objetos en la DB. Null en cualquier otro caso.
     */
    public List findAll() 
    {
        LOGGER.info("Consultando todos las mediciones");
        TypedQuery query = em.createQuery("select u from MedicionMaquinaEntity u", MedicionMaquinaEntity.class);
        return query.getResultList();
    }
   
     /**
     * Actualiza la informacion de un objeto MedicionMaquina ingresado por parametro.
     * @param entity Objeto de tipo EjercicioHecho.
     * @return Objeto EjercicioHecho que se actualizo.
     */
    public MedicionMaquinaEntity update(MedicionMaquinaEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando la medicionMaquina con id={0}", entity.getId());
        return em.merge(entity);
    }
    
     /**
     * Elimina un objeto MedicionMaquina de la base de datos con el id ingresado por parametro.
     * @param id Id del objeto en la BD.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando medicionMaquina con id={0}", id);
        MedicionMaquinaEntity entity = em.find(MedicionMaquinaEntity.class, id);
        em.remove(entity);
    }
    
}
