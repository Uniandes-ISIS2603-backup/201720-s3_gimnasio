/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.ParteDelCuerpoPersitence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class ParteDelCuerpoLogic {
    @Inject
    ParteDelCuerpoPersitence persistence;
    
    public PartesDelCuerpoEntity createParteDelCuerpo(PartesDelCuerpoEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    public PartesDelCuerpoEntity updateParteDelCuerpo(PartesDelCuerpoEntity entity)
    {
        return persistence.update(entity);
    }
    public void delete(Long id)
    {
        persistence.delete(id);
    }
    public PartesDelCuerpoEntity find(Long id)
    {
        return persistence.find(id);
    }
    
}
