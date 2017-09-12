/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.romero12
 */
@Stateless
public class RutinaPersistence {
    private static final Logger LOGGER = Logger.getLogger(RutinaPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega una Rutina
     * 
     * @param entity objeto Rutina que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public RutinaEntity create(RutinaEntity entity) {
        LOGGER.info("Creando una rutina nueva");
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza una rutina.
     *
     * @param entity: la rutina que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return una rutina con los cambios aplicados.
     */
    public RutinaEntity update(RutinaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Rutina con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra una rutina de la base de datos recibiendo como argumento el id
     * de la rutina.
     *
     * @param id: id correspondiente a la Rutina a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Rutina con id={0}", id);
        RutinaEntity entity = em.find(RutinaEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay alguna rutina con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Rutina buscada.
     * @return un Rutina.
     */
    public RutinaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Rutina con id={0}", id);
        return em.find(RutinaEntity.class, id);
    }

    /**
     * Devuelve todas las Rutinas de la base de datos.
     *
     * @return una lista con todas las Rutinas que encuentre en la base de datos
     */
    public List<RutinaEntity> findAll() {
        LOGGER.info("Consultando todas los Rutinas");
        TypedQuery query = em.createQuery("select r from RutinaEntity r", RutinaEntity.class);
        return query.getResultList();
    }
}