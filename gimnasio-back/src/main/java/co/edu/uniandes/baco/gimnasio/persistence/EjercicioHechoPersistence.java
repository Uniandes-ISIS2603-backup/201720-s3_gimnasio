/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
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
public class EjercicioHechoPersistence  extends BasePersistence<EjercicioHechoEntity>
{    
        public EjercicioHechoPersistence() 
        {
            super(EjercicioHechoEntity.class);
        }
}
//    private static final Logger LOGGER = Logger.getLogger(EjercicioHechoPersistence.class.getName());
//    @PersistenceContext(unitName = "gimnasioPU")
//    
//    protected EntityManager em;
//
//    /**
//     * Crea un nuevo objetivo de tipo EjecicioHecho y lo almacena en la BD.
//     * @param entity objeto de tipo EjecicioHecho que se guardara en la BD.
//     * @return retorna el objeto EjercicioHecho creado.
//     */
//    public EjercicioHechoEntity create(EjercicioHechoEntity entity) 
//    {
//        LOGGER.info("Creando un ejercicioHecho nuevo");
//        em.persist(entity);
//        return entity;
//    }
//    
//    /**
//     * Busca si existe un EjercicioHecho con el id dado por parametro en la BD y lo retorna. 
//     * @param id Id del objeto EjecicioHecho a buscar.
//     * @return objeto EjercicioHecho si lo encuentra en la DB. Null en cualquier otro caso.
//     */
//    public EjercicioHechoEntity find(Long id) 
//    {
//        LOGGER.log(Level.INFO, "Consultando EjecicioHecho con id={0}", id);
//        return em.find(EjercicioHechoEntity.class, id);
//    }
//    
//    /**
//     * Retorna una lista con todos los objetos EjercicioHecho. 
//     * @return Lista con objetos de tipo EjercicioHecho si encuentra objetos en la DB. Null en cualquier otro caso.
//     */
//    public List<EjercicioHechoEntity> findAll()
//    {
//        LOGGER.info("Consultando todos los ejecicios hechos");
//        TypedQuery query = em.createQuery("select u from EjercicioHechoEntity u", EjercicioHechoEntity.class);
//        return query.getResultList();
//    }
//
//    /**
//     * Actualiza la informacion de un objeto EjercicioHecho ingresado por parametro.
//     * @param entity Objeto de tipo EjercicioHecho.
//     * @return Objeto EjercicioHecho que se actualizo.
//     */
//    public EjercicioHechoEntity update(EjercicioHechoEntity entity) 
//    {
//        LOGGER.log(Level.INFO, "Actualizando el ejercicioHecho con id={0}", entity.getId());
//        return em.merge(entity);
//    }
//    
//    /**
//     * Elimina un objeto EjercicioHecho de la base de datos con el id ingresado por parametro.
//     * @param id Id del objeto en la BD.
//     */
//    public void delete(Long id) 
//    {
//        LOGGER.log(Level.INFO, "Borrando ejercicioHecho con id={0}", id);
//        EjercicioHechoEntity entity = em.find(EjercicioHechoEntity.class, id);
//        em.remove(entity);
//    }