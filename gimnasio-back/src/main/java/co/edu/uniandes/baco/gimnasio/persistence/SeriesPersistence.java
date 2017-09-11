/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.SeriesEntity;
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
public class SeriesPersistence {
    private static final Logger LOGGER = Logger.getLogger(SeriesPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega una Serie
     * 
     * @param entity objeto Serie que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SeriesEntity create(SeriesEntity entity) {
        LOGGER.info("Creando una serie nueva");
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza una serie.
     *
     * @param entity: las series que viene con los nuevos cambios. Por ejemplo
     * el numero de series pudo cambiar. En ese caso, se haria uso del método update.
     * @return una rutina con los cambios aplicados.
     */
    public SeriesEntity update(SeriesEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando series con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra series de la base de datos recibiendo como argumento el id
     * de la serie.
     *
     * @param id: id correspondiente a la serie a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando series con id={0}", id);
        SeriesEntity entity = em.find(SeriesEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algunas series con el id que se envía de argumento
     *
     * @param id: id correspondiente a la series buscada.
     * @return una serie.
     */
    public SeriesEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando series con id={0}", id);
        return em.find(SeriesEntity.class, id);
    }

    /**
     * Devuelve todas las Series de la base de datos.
     *
     * @return una lista con todas las series que encuentre en la base de datos
     */
    public List<SeriesEntity> findAll() {
        LOGGER.info("Consultando todas las series");
        TypedQuery query = em.createQuery("select r from SeriesEntity r", SeriesEntity.class);
        return query.getResultList();
    }
}
