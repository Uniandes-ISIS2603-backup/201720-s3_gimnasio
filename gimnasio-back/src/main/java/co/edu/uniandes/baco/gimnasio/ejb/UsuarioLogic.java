/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package co.edu.uniandes.baco.gimnasio.ejb;
 
 import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
 import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
 import javax.ejb.Stateless;
 
 /**
  * @author m.sicard10
  */
 @Stateless
 public class UsuarioLogic extends BaseLogic<UsuarioEntity> {
     
    @Override
    public UsuarioEntity update(UsuarioEntity entity) throws BusinessLogicException {
        UsuarioEntity ent=find(entity.getId());
        entity.setEntrenadores(ent.getEntrenadores());
        entity.setRutinas(ent.getRutinas());
        return super.update(entity); 
    }
 }
