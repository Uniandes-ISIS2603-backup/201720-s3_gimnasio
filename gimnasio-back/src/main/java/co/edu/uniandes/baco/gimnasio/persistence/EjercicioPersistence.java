/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioPersistence {
    private static final Logger LOGGER = Logger.getLogger(EjercicioPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega un objetivo
     * 
     * @param entity objeto Ejercicio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EjercicioEntity create(EjercicioEntity entity) {
        LOGGER.info("Creando un objetivo nuevo");
        em.persist(entity);
        return entity;
    }
    /**
     * Actualiza un objetivo.
     *
     * @param entity: la Ejercicio que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un objetivo con los cambios aplicados.
     */
    public EjercicioEntity update(EjercicioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando objetivo con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     *
     * Borra un objetivo de la base de datos recibiendo como argumento el id
     * de el Ejercicio
     *
     * @param id: id correspondiente a la Ejercicio a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Ejercicio con id={0}", id);
        EjercicioEntity entity = em.find(EjercicioEntity.class, id);
        em.remove(entity);
    }
    /**
     * Busca si hay algun objetivo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Ejercicio buscada.
     * @return un objetivo.
     */
    public EjercicioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando objetivo con id={0}", id);
        return em.find(EjercicioEntity.class, id);
    }
    /**
     * Devuelve todas las Ejercicios de la base de datos.
     *
     * @return una lista con todas las Ejercicioes que encuentre en la base de datos
     */
    public List<EjercicioEntity> findAll() {
        LOGGER.info("Consultando todas los Ejercicios");
        TypedQuery query = em.createQuery("select u from EjercicioEntity u", EjercicioEntity.class);
        return query.getResultList();
    }
}
