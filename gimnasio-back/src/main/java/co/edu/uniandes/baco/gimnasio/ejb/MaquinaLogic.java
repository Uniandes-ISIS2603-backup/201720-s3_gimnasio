/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.MaquinaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaLogic {
     @Inject
     MaquinaPersistence persistence;
     
     public MaquinaEntity createMaquina(MaquinaEntity e) throws BusinessLogicException
     {
          return persistence.create(e);
     }
     
     public MaquinaEntity find (long id)
     {
         return persistence.find(id);
     }
     
     public MaquinaEntity update (MaquinaEntity e) throws BusinessLogicException
     {
          if(persistence.find(e.getId())==null)throw new BusinessLogicException("no exixte un Maquina con el id");
          return persistence.update(e);
     }
     
     public void remove(long id)
     {
         persistence.delete(id);
     }
     
     public List<MaquinaEntity> findall()
     {
         return persistence.findAll();
     }
}
