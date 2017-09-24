/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.baco.gimnasio.persistence.*;
import java.util.List;

/**
 *
 * @author js.palacios437
 */
@Stateless

public class ParteDelCuerpoLogic {
    @Inject
    ParteDelCuerpoPersitence persistence;
    
    public PartesDelCuerpoEntity createParteDelCuerpo(PartesDelCuerpoEntity entity) throws Exception
    {
        persistence.create(entity);
        return entity;
    }

    public PartesDelCuerpoEntity updateParteDelCuerpo(PartesDelCuerpoEntity entity) throws Exception
    {
        return persistence.update(entity);
    }
    public void delete(Long id) throws Exception
    {
        persistence.delete(id);
    }
    public PartesDelCuerpoEntity find(Long id) throws Exception
    {
        return persistence.find(id);
    }
    public List<PartesDelCuerpoEntity> getPartesDelCuerpo() throws Exception
    {
      List<PartesDelCuerpoEntity> lista = persistence.findAll();
      return lista;
    }
    

}
