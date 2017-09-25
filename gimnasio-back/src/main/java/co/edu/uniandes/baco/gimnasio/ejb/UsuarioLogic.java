/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package co.edu.uniandes.baco.gimnasio.ejb;
 
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
 import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
 import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
 import co.edu.uniandes.baco.gimnasio.persistence.UsuarioPersistence;
 import java.util.List;
 import javax.ejb.Stateless;
 import javax.inject.Inject;
 
 /**
  *
  * @author m.sicard10
  */
 @Stateless
 public class UsuarioLogic extends BaseLogic<UsuarioEntity> {

    public List<EntrenadorEntity> listEntrenador(long id) throws BusinessLogicException
    {
        return find(id).getEntrenadores();
    }

    @Override
    public UsuarioEntity update(UsuarioEntity entity) throws BusinessLogicException {
        UsuarioEntity ent=find(entity.getId());
        entity.setEntrenadores(ent.getEntrenadores());
        entity.setRutinas(ent.getRutinas());
        return super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
 }
