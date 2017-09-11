package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class GimnasioPersistence {

    private static final Logger LOGGER = Logger.getLogger(GimnasioPersistence.class.getName());
    
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Gimnasio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public GimnasioEntity create(GimnasioEntity entity) {
        LOGGER.info("Creando un gimnasio nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Gimnasio en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un gimnasio nueva");
        return entity;
    }

    /**
     * Actualiza un gimnasio.
     *
     * @param entity: la Gimnasio que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un gimnasio con los cambios aplicados.
     */
    public GimnasioEntity update(GimnasioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Gimnasio con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Gimnasio con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un gimnasio de la base de datos recibiendo como argumento el id
     * de la Gimnasio
     *
     * @param id: id correspondiente a la Gimnasio a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Gimnasio con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public GimnasioEntity find(Long id) para obtener la Gimnasio a borrar.
        GimnasioEntity entity = em.find(GimnasioEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from GimnasioEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun gimnasio con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Gimnasio buscada.
     * @return un gimnasio.
     */
    public GimnasioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Gimnasio con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from GimnasioEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(GimnasioEntity.class, id);
    }

    /**
     * Devuelve todas las Gimnasioes de la base de datos.
     *
     * @return una lista con todas las Gimnasioes que encuentre en la base de
     * datos, "select u from GimnasioEntity u" es como un "select * from
     * GimnasioEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<GimnasioEntity> findAll() {
        LOGGER.info("Consultando todas las Gimnasioes");
        // Se crea un query para buscar todas las Gimnasioes en la base de datos.
        TypedQuery query = em.createQuery("select u from GimnasioEntity u", GimnasioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Gimnasioes.
        return query.getResultList();
    }
}
