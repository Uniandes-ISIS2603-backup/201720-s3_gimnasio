/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */
@Stateless
public class EstadoLogic {
    
    @Inject
    private EstadoPersistence persistencia;
    
    /**
     * crear un nuevo estado 
     * @param entity
     * @return entity
     * @throws BusinessLogicException 
     */
    public EstadoEntity createEstado(EstadoEntity entity) throws BusinessLogicException
    {
        persistencia.create(entity);
        return entity;
    }
    
    /**
     * actualiza un estado 
     * @param entity
     * @return 
     */
    public EstadoEntity updateEstado(EstadoEntity entity) throws BusinessLogicException
    {
      return persistencia.update(entity);
    }
    
    public EstadoEntity getEstadoFecha(Date date)throws BusinessLogicException
    {
        return persistencia.findfecha(date);
    }
    
    public EstadoEntity getEstado(Long id) throws BusinessLogicException
    {
        return persistencia.find(id);
    }
    
    public void deleteEstado(Long id) throws BusinessLogicException
    {
        EstadoEntity com = persistencia.find(id);
        if(com!=null)
        persistencia.delete(id);
    }
    
    
    
    
}
