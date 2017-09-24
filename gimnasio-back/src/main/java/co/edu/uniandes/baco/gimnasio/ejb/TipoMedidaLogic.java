/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.TipoMedidaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class TipoMedidaLogic {
     @Inject
     TipoMedidaPersistence persistence;
     
     public TipoMedidaEntity createTipoMedida(TipoMedidaEntity e) throws BusinessLogicException
     {
          return persistence.create(e);
     }
     
     public TipoMedidaEntity find (long id)
     {
         return persistence.find(id);
     }
     
     public TipoMedidaEntity update (TipoMedidaEntity e) throws BusinessLogicException
     {
          if(persistence.find(e.getId())==null)throw new BusinessLogicException("no exixte un TipoMedida con el id");
          return persistence.update(e);
     }
     
     public void remove(long id)
     {
         persistence.delete(id);
     }
     
     public List<TipoMedidaEntity> findall()
     {
         return persistence.findAll();
     }
}