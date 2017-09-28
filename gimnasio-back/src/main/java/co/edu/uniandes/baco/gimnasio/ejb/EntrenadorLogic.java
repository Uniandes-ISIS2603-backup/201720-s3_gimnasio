package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.List;
import javax.ejb.Stateless;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorLogic extends BaseLogic<EntrenadorEntity>{
    /**
     * metodo que crear un entrenador
     * @param entity el entrenado a crear
     * @return el entrenador creado
     * @throws BusinessLogicException si el entrenador ya existe 
     */
    @Override
    public EntrenadorEntity create(EntrenadorEntity entity) throws BusinessLogicException{
        if(((EntrenadorPersistence)persistence).findByDocumento(entity.getDocumento())!= null)
            throw new BusinessLogicException("ya exixte un entrenador con ese");
        return super.create(entity);
    }
    /**
     * metodo que devuelve la lista de usuarios de un entrenador
     * @param id del entrenado
     * @return lista con los usuarios de ese entrenador
     * @throws BusinessLogicException  si el entrenador no existe
     */
    public List<UsuarioEntity> listUsuario(long id) throws BusinessLogicException 
    {
        return find(id).getUsuarios();
    }
    /**
     * metodo que asocia un usuario a un entrenador
     * @param entrenadorId id del entrenador
     * @param usuarioId id del usuario
     * @return el usuario asosiado
     * @throws BusinessLogicException si el usuario o el entrenado no existen 
     */
    public UsuarioEntity addUsuarioDeail(Long entrenadorId, Long usuarioId) throws BusinessLogicException{
        EntrenadorEntity ent = find(entrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        ent.getUsuarios().add(us);
        return getusuario(entrenadorId, usuarioId);
    }
    /**
     * metodo que consulta un usuario espesifico de un entrenador
     * @param entrenadorId id del entrenador
     * @param usuarioId id del usuario
     * @return el usuario
     * @throws BusinessLogicException si el entrenador no existe o el usuario 
     */
     public UsuarioEntity getusuario(Long entrenadorId, Long usuarioId) throws BusinessLogicException {
        List<UsuarioEntity> list = find(entrenadorId).getUsuarios();
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
     * @param EntrenadorId id del entrenador
     * @param usuarioId id del usuario
     * @throws BusinessLogicException si el entrenador o el usuario no existen
     */
    public void removeUsuario(Long EntrenadorId, Long usuarioId) throws BusinessLogicException  {
        EntrenadorEntity e = find(EntrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        e.getUsuarios().remove(us);
    }
}
