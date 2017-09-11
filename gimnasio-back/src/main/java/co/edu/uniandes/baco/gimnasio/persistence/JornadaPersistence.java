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
    private static final Logger LOGGER = Logger.getLogger(JornadaPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega un jornada
     * 
     * @param entity objeto Jornada que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public JornadaEntity create(JornadaEntity entity) {
        LOGGER.info("Creando un jornada nuevo");
        em.persist(entity);
        return entity;
    }
    /**
     * Actualiza un jornada.
     *
     * @param entity: la Jornada que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un jornada con los cambios aplicados.
     */
    public JornadaEntity update(JornadaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando jornada con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     *
     * Borra un jornada de la base de datos recibiendo como argumento el id
     * de el Jornada
     *
     * @param id: id correspondiente a la Jornada a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Jornada con id={0}", id);
        JornadaEntity entity = em.find(JornadaEntity.class, id);
        em.remove(entity);
    }
    /**
     * Busca si hay algun jornada con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Jornada buscada.
     * @return un jornada.
     */
    public JornadaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando jornada con id={0}", id);
        return em.find(JornadaEntity.class, id);
    }
    /**
     * Devuelve todas las Jornadas de la base de datos.
     *
     * @return una lista con todas las Jornadaes que encuentre en la base de datos
     */
    public List<JornadaEntity> findAll() {
        LOGGER.info("Consultando todas los Jornadas");
        TypedQuery query = em.createQuery("select u from JornadaEntity u", JornadaEntity.class);
        return query.getResultList();
    }
}