/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
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
public class UsuarioPersistence {
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    /**
     * Agrega un usuario
     * 
     * @param entity objeto Usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UsuarioEntity create(UsuarioEntity entity) {
        LOGGER.info("Creando un usuario nuevo");
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza un usuario.
     *
     * @param entity: el usuario que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un usuario con los cambios aplicados.
     */
    public UsuarioEntity update(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Usuario con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id
     * de el Usuario
     *
     * @param id: id correspondiente al Usuario a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Usuario con id={0}", id);
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param id: id correspondiente a un Usuario buscado.
     * @return un Usuario.
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }

    /**
     * Devuelve todas los Usuarios de la base de datos.
     *
     * @return una lista con todas los Usuarios que encuentre en la base de datos
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando todas los Usuarios");
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
}

