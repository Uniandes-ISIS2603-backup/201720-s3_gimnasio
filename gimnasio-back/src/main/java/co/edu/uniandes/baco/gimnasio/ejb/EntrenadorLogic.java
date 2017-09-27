package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
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
    
    @Override
    public EntrenadorEntity create(EntrenadorEntity entity) throws BusinessLogicException{
        if(((EntrenadorPersistence)persistence).findByDocumento(entity.getDocumento())!= null)
            throw new BusinessLogicException("ya exixte un entrenador con ese");
        return super.create(entity);
    }
    
    public List<UsuarioEntity> listUsuario(long id) throws BusinessLogicException 
    {
        return find(id).getUsuarios();
    }

    public UsuarioEntity addUsuarioDeail(Long entrenadorId, Long usuarioId) throws BusinessLogicException{
        EntrenadorEntity ent = find(entrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        ent.getUsuarios().add(us);
        return getusuario(entrenadorId, usuarioId);
    }

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

    public void removeUsuario(Long EntrenadorId, Long usuarioId) throws BusinessLogicException  {
        EntrenadorEntity e = find(EntrenadorId);
        UsuarioEntity us = new UsuarioEntity();
        us.setId(usuarioId);
        e.getUsuarios().remove(us);
    }

    public void addRutinaAUsuario(Long entrenadorId, Long usuarioId, RutinaEntity estadoentity) throws BusinessLogicException {
           List<UsuarioEntity> list = find(entrenadorId).getUsuarios();
           for (UsuarioEntity u: list) {
            if (u.getId() == usuarioId)
            {
                u.getRutinas().add(estadoentity);
                return;
            }
            throw new BusinessLogicException(" el id del usuario no existe o no lo tiene el entrenador");
        }
    }

    public UsuarioEntity BuscarUsuario(Long entrenadorId, Long usuarioId) throws BusinessLogicException {
        List<UsuarioEntity> list = find(entrenadorId).getUsuarios();
           for (UsuarioEntity u: list) {
            if (u.getId() == usuarioId)
            {
                return u;
            }
    }
           throw new BusinessLogicException("no se encontro el usuario");
    }

}
