/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class ParteDelCuerpoPersitence {

    @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
     /**
      * metodo que persiste una parte del cuerpo
      * @param entity
      * @return la aprte dle cuerpo persistida
      */
    public PartesDelCuerpoEntity create(PartesDelCuerpoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    /**
     * metodo que actualiza una parte del cuerpo
     * @param entity con al informacion a actulizar
     * @return el entity que se actulizo
     */
    public PartesDelCuerpoEntity update(PartesDelCuerpoEntity entity)
    {
        return em.merge(entity);
    }
    /**
     * borra una parte del cuerpo
     * @param id 
     */
    public void delete(Long id)
    {
        PartesDelCuerpoEntity entity = em.find(PartesDelCuerpoEntity.class,id);
        em.remove(entity);
    }
    public PartesDelCuerpoEntity find(Long id)
    {
        return em.find(PartesDelCuerpoEntity.class,id);
    }
    public List<PartesDelCuerpoEntity> findall()
    {
        TypedQuery query = em.createQuery("select u from PartesDelCuerpoEntity u", PartesDelCuerpoEntity.class);
        return query.getResultList();
    }
    
}
