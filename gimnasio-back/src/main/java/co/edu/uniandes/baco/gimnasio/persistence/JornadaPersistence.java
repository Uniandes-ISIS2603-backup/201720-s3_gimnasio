package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.JornadaEntity;
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
public class JornadaPersistence {
    private final static Logger LOGGER=Logger.getLogger(JornadaPersistence.class.getName());
    @PersistenceContext(unitName = "jornadaPU")
    protected EntityManager em;
    
    /**
     * Agrega una Jornada
     * 
     * @param entity objeto Objetivo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public JornadaEntity create(JornadaEntity entity) {
        LOGGER.info("Creando una Jornada nueva");
        em.persist(entity);
        return entity;
    }
    /**
     * Actualiza una Jornada.
     *
     * @param entity: la Objetivo que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un objetivo con los cambios aplicados.
     */
    public JornadaEntity update(JornadaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Jornada con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     *
     * Borra una Jornada de la base de datos recibiendo como argumento el id
     * de el Objetivo
     *
     * @param id: id correspondiente a la Objetivo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Jornada con id={0}", id);
        JornadaEntity entity = em.find(JornadaEntity.class, id);
        em.remove(entity);
    }
    /**
     * Busca si hay alguna Jornada con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Objetivo buscada.
     * @return un objetivo.
     */
    public JornadaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Jornada con id={0}", id);
        return em.find(JornadaEntity.class, id);
    }
    /**
     * Devuelve todas las Objetivos de la base de datos.
     *
     * @return una lista con todas las Objetivoes que encuentre en la base de datos
     */
    public List<JornadaEntity> findAll() {
        LOGGER.info("Consultando todas las jornadas");
        TypedQuery query = em.createQuery("select u from JornadaEntity u", JornadaEntity.class);
        return query.getResultList();
    }
    
}
