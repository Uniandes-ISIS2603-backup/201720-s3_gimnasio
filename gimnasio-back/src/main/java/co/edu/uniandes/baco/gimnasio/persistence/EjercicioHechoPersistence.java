package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
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
public class EjercicioHechoPersistence {
    private static final Logger LOGGER = Logger.getLogger(EjercicioHechoPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega un ejercicioHecho
     * 
     * @param entity objeto EjercicioHecho que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EjercicioHechoEntity create(EjercicioHechoEntity entity) {
        LOGGER.info("Creando un ejercicioHecho nuevo");
        em.persist(entity);
        return entity;
    }
    /**
     * Actualiza un ejercicioHecho.
     *
     * @param entity: la EjercicioHecho que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un ejercicioHecho con los cambios aplicados.
     */
    public EjercicioHechoEntity update(EjercicioHechoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando ejercicioHecho con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     *
     * Borra un ejercicioHecho de la base de datos recibiendo como argumento el id
     * de el EjercicioHecho
     *
     * @param id: id correspondiente a la EjercicioHecho a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando EjercicioHecho con id={0}", id);
        EjercicioHechoEntity entity = em.find(EjercicioHechoEntity.class, id);
        em.remove(entity);
    }
    /**
     * Busca si hay algun ejercicioHecho con el id que se envía de argumento
     *
     * @param id: id correspondiente a la EjercicioHecho buscada.
     * @return un ejercicioHecho.
     */
    public EjercicioHechoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando ejercicioHecho con id={0}", id);
        return em.find(EjercicioHechoEntity.class, id);
    }
    /**
     * Devuelve todas las EjercicioHechos de la base de datos.
     *
     * @return una lista con todas las EjercicioHechoes que encuentre en la base de datos
     */
    public List<EjercicioHechoEntity> findAll() {
        LOGGER.info("Consultando todas los EjercicioHechos");
        TypedQuery query = em.createQuery("select u from EjercicioHechoEntity u", EjercicioHechoEntity.class);
        return query.getResultList();
    }
}