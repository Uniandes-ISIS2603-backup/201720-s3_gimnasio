package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.List;
import java.util.logging.Level;
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
    
    @Override
    public EntrenadorEntity create(EntrenadorEntity entity) throws  Exception
    {
        if(((EntrenadorPersistence)persistence).findByDocumento(entity.getDocumento())!= null)
            throw new BusinessLogicException("ya exixte un entrenador con ese");
        return super.create(entity);
    }
    
    public List<UsuarioEntity> listUsuario(long id) throws BusinessLogicException, Exception
    {
        return find(id).getUsuarios();
    }

    public UsuarioEntity addUsuarioDeail(Long entrenadorId, Long usuarioId) throws BusinessLogicException, Exception {
        EntrenadorEntity ent = find(entrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        ent.getUsuarios().add(us);
        return getusuario(entrenadorId, usuarioId);
    }

     public UsuarioEntity getusuario(Long entrenadorId, Long usuarioId) throws BusinessLogicException, Exception {
        List<UsuarioEntity> list = find(entrenadorId).getUsuarios();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuarioId);
        int index = list.indexOf(usuarioEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    public void removeUsuario(Long EntrenadorId, Long usuarioId) throws BusinessLogicException, Exception {
        EntrenadorEntity e = find(EntrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        e.getUsuarios().remove(us);
    }
}
