/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.MedidaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */

@Stateless
public class MedidaLogic {
    
    @Inject
    MedidaPersistence persitencia;
    
        /**
     * crear un nuevo estado 
     * @param entity
     * @return entity
     * @throws BusinessLogicException 
     */
    public MedidaEntity create(MedidaEntity entity) throws BusinessLogicException
    {
        persitencia.crete(entity);
        return entity;
    }
        /**
     * actualiza un estado 
     * @param entity
     * @return 
     */
    public MedidaEntity update(MedidaEntity entity) throws BusinessLogicException
    {
      return persitencia.update(entity);
    }
    
    public MedidaEntity get(Long id) throws BusinessLogicException
    {
        return persitencia.find(id);
    }
    
    public void delete(Long id) throws BusinessLogicException
    {
        MedidaEntity com = persitencia.find(id);
        if(com!=null)
        persitencia.delete(id);
    }
    
}
