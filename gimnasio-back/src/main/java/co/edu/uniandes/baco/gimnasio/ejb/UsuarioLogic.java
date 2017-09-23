/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author m.sicard10
 */
@Stateless
public class UsuarioLogic {
     @Inject
     UsuarioPersistence persistence;
     
     public UsuarioEntity createUsuario(UsuarioEntity e) throws BusinessLogicException
     {
          return persistence.create(e);
     }
     
     public UsuarioEntity find (long id)
     {
         return persistence.find(id);
     }
     
     public UsuarioEntity update (UsuarioEntity e) throws BusinessLogicException
     {
          if(persistence.find(e.getId())==null)throw new BusinessLogicException("no exixte un usuario con el id");
          return persistence.update(e);
     }
     
     public void remove(long id)
     {
         persistence.delete(id);
     }
     
     public List<UsuarioEntity> findall()
     {
         return persistence.findAll();
     }
}
