/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package co.edu.uniandes.baco.gimnasio.ejb;
 
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
 import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
 import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
 import java.util.List;
 import javax.ejb.Stateless;
 import javax.inject.Inject;
 
 /**
  *
  * @author m.sicard10
  */
 @Stateless
 public class UsuarioLogic extends BaseLogic<UsuarioEntity> {

    // @Inject
    // private EstadoLogic elogic;
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
    
    //
    //Estado
    //
    public List<EstadoEntity> findAllEstado(Long usuarioid) throws BusinessLogicException
    {
       List<EstadoEntity> lista = this.find(usuarioid).getEstados();
       return lista;
    }
   /* 
    public EstadoEntity addEstado(Long id,Long EstadoId) throws BusinessLogicException
    {
        UsuarioEntity usuario = this.find(id);
        EstadoEntity estado = elogic.find(EstadoId);
        estado.setUsuario(usuario);
        return estado;
    }
    public EstadoEntity getestado(Long id,Long EstadoId) throws BusinessLogicException 
    {
        List<EstadoEntity> estados = this.find(id).getEstados();
        EstadoEntity estado = elogic.find(EstadoId);
        int index = estados.indexOf(estado);
        if (index >= 0) {
            return estados.get(index);
        }
         throw new BusinessLogicException("el estado no existe");
    }
*/
    
 }
