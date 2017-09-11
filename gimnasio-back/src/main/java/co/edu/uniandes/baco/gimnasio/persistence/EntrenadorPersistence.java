/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorPersistence {
    //@PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
    
    /**
     * metodo que crea el entrenador y lo persiste
     * @param entity entidad a crear
     * @return entity
     */
    public EntrenadorEntity create(EntrenadorEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * actualiza un Entrenador
     * @param entity
     * @return 
     */
    public EntrenadorEntity update(EntrenadorEntity entity)
    {
        em.merge(entity);
        return entity;
    }
    
    /**
     * Borra un Entrenador de la base de datos recibiendo como argumento el id
     * del entrenador
     * @param id id del Entrenador
     */
    public void delete(Long id)
    {
        EntrenadorEntity aBorrar = em.find(EntrenadorEntity.class, id);
        em.remove(aBorrar);
    }
    
    /**
     * busca un entrenadoor en la base de datos recibiendo como parametro el id del enrenador
     * @param id id del entrenador
     * @return 
     */
    public EntrenadorEntity find(Long id)
    {
        return em.find(EntrenadorEntity.class, id);
    }
    
    /**
     * Devuelve todas los EntrenadorEntity de la base de datos.
     *
     * @return una lista con todas las entrenadores que encuentre en la base de
     * datos, "select u from EntrenadorEntity u" es como un "select * from
     * EntrenadorEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<EntrenadorEntity> findAll() {
        TypedQuery query = em.createQuery("select u from GimnasioEntity u", EntrenadorEntity.class);
        return query.getResultList();
    }
}
