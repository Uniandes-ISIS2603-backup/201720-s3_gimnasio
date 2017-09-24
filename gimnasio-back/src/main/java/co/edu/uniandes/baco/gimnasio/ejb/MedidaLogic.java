/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;

import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.MedidaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OneToOne;

import javax.ejb.Stateless;


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
    public MedidaEntity createMedida(MedidaEntity entity) throws Exception
    {
      
       
        persitencia.create(entity);
        return entity;
    }
        /**
     * actualiza un estado 
     * @param entity
     * @return 
     */
    public MedidaEntity updateMedida(MedidaEntity entity) throws BusinessLogicException, Exception
    {
      return persitencia.update(entity);
    }
    
    public MedidaEntity getMedida(Long id) throws BusinessLogicException, Exception
    {
        return persitencia.find(id);
    }
    
    public void deleteMedida(Long id) throws BusinessLogicException, Exception
    {
        MedidaEntity com = persitencia.find(id);
        if(com!=null)
        persitencia.delete(id);
    }
    public List<MedidaEntity> getMedida() throws Exception
    {
      List<MedidaEntity> lista = persitencia.findAll();
      return lista;
    }
}
