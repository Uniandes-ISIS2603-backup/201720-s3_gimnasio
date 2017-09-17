/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class ParteDelCuerpoPersitence {

        @PersistenceContext(unitName = "gimnasioPU")
    protected EntityManager em;
        
    public PartesDelCuerpoEntity create(PartesDelCuerpoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public PartesDelCuerpoEntity update(PartesDelCuerpoEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        PartesDelCuerpoEntity entity = em.find(PartesDelCuerpoEntity.class,id);
        em.remove(entity);
    }
    public PartesDelCuerpoEntity find(Long id)
    {
        return em.find(PartesDelCuerpoEntity.class,id);
    }
}
