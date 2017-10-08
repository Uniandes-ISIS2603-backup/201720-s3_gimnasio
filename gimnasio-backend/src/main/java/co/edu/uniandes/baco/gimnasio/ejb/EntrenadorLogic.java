package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorLogic extends BaseLogic<EntrenadorEntity>{

    public EntrenadorLogic() {
        super();
    }
    
    @Inject public EntrenadorLogic(BasePersistence<EntrenadorEntity> persistence) {
        super(persistence);
    }
    
    /**
     * metodo que crear un entrenador
     * @param entity el entrenado a crear
     * @return el entrenador creado
     * @throws BusinessLogicException si el entrenador ya existe 
     */
    @Override
    public EntrenadorEntity create(EntrenadorEntity entity) throws BusinessLogicException{
        if(((EntrenadorPersistence)persistence).findByDocumento(entity.getDocumento())!= null)
            throw new BusinessLogicException("ya exixte un entrenador con ese documento");
        return super.create(entity);
    }
    
    /**
     * metodo que devuelve la lista de usuarios de un entrenador
     * @param id del entrenado
     * @return lista con los usuarios de ese entrenador
     * @throws BusinessLogicException  si el entrenador no existe
     */
    public List<UsuarioEntity> findAllUsuario(long id) throws BusinessLogicException {
        return find(id).getUsuarios();
    }
    
    /**
     * metodo que asocia un usuario a un entrenador
     * @param id id del entrenador
     * @param usuarioId id del usuario
     * @return el usuario asosiado
     * @throws BusinessLogicException si el usuario o el entrenado no existen 
     */
    public UsuarioEntity createUsuario(Long id, Long usuarioId) throws BusinessLogicException{
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        find(id).getUsuarios().add(us);
        return findUsuario(id, usuarioId);
    }
    
    /**
     * metodo que consulta un usuario espesifico de un entrenador
     * @param id id del entrenador
     * @param usuarioId id del usuario
     * @return el usuario
     * @throws BusinessLogicException si el entrenador no existe o el usuario 
     */
     public UsuarioEntity findUsuario(Long id, Long usuarioId) throws BusinessLogicException {
        List<UsuarioEntity> list = find(id).getUsuarios();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuarioId);
        int index = list.indexOf(usuarioEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
     
    /**
     * metodo que elimina un usuario de un entrenador
     * @param id id del entrenador
     * @param usuarioId id del usuario
     * @throws BusinessLogicException si el entrenador o el usuario no existen
     */
    public void removeUsuario(Long id, Long usuarioId) throws BusinessLogicException  {
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        find(id).getUsuarios().remove(us);
    }
}
