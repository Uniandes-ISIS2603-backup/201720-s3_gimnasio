package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoPersistence {
    private static final Logger LOGGER = Logger.getLogger(ObjetivoPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega un objetivo
     * 
     * @param entity objeto Objetivo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ObjetivoEntity create(ObjetivoEntity entity) {
        LOGGER.info("Creando un objetivo nuevo");
        em.persist(entity);
        return entity;
    }
    /**
     * Actualiza un objetivo.
     *
     * @param entity: la Objetivo que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un objetivo con los cambios aplicados.
     */
    public ObjetivoEntity update(ObjetivoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando objetivo con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     *
     * Borra un objetivo de la base de datos recibiendo como argumento el id
     * de el Objetivo
     *
     * @param id: id correspondiente a la Objetivo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Objetivo con id={0}", id);
        ObjetivoEntity entity = em.find(ObjetivoEntity.class, id);
        em.remove(entity);
    }
    /**
     * Busca si hay algun objetivo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Objetivo buscada.
     * @return un objetivo.
     */
    public ObjetivoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando objetivo con id={0}", id);
        return em.find(ObjetivoEntity.class, id);
    }
    /**
     * Devuelve todas las Objetivos de la base de datos.
     *
     * @return una lista con todas las Objetivoes que encuentre en la base de datos
     */
    public List<ObjetivoEntity> findAll() {
        LOGGER.info("Consultando todas los Objetivos");
        TypedQuery query = em.createQuery("select u from ObjetivoEntity u", ObjetivoEntity.class);
        return query.getResultList();
    }
}