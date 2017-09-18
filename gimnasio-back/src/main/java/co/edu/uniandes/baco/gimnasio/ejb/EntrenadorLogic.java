package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorLogic {
    @Inject
    private EntrenadorPersistence persistence;
    
    public EntrenadorEntity createEntrenador(EntrenadorEntity entity) throws BusinessLogicException
    {
        if(persistence.findByDocumento(entity.getDocumento())== null)
        return persistence.create(entity);
        else{
            throw new BusinessLogicException("ya exixte un entrenador con ese");
        }
    }
    
    public EntrenadorEntity find(long id){
        return persistence.find(id);
    }
    
    public EntrenadorEntity update(EntrenadorEntity entity) throws BusinessLogicException{
        if(persistence.find(entity.getId())==null)throw new BusinessLogicException("no exixte un entrenador con el id");
        return persistence.update(entity);
    }
    
    public void remove(long id){
        persistence.delete(id);
    }
    
    public List<EntrenadorEntity> getentrenadores()
    {
        return persistence.findAll();
    }
    
}
