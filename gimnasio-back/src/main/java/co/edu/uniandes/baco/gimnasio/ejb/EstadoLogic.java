/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic extends BaseLogic<EstadoEntity>{
    @Inject
    UsuarioLogic logic;
    
    public List<EstadoEntity> findAll(long idUsuario) throws BusinessLogicException {
        return logic.find(idUsuario).getEstados();
    }
    
    public EstadoEntity find(long idUsuario,long id) throws BusinessLogicException {
       EstadoEntity ent=new EstadoEntity();
       ent.setId(id);
       List<EstadoEntity> list=logic.find(idUsuario).getEstados();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }

    public EstadoEntity update(Long idUsuario,EstadoEntity entity) throws BusinessLogicException {
        EstadoEntity old=find(entity.getId());
        if(!old.getUsuario().getId().equals(idUsuario))
            throw new NoExisteException(idUsuario);
        entity.setUsuario(old.getUsuario());
        entity.setMedidas(old.getMedidas());
        return update(entity);
    }
    
    public EstadoEntity create(long idUsuario,EstadoEntity entity) throws BusinessLogicException {
        UsuarioEntity Usuario=logic.find(idUsuario);
        entity.setUsuario(Usuario);
        return create(entity);
    }

    public void remove(long idUsuario,long id) throws BusinessLogicException {
        EstadoEntity ent=find(idUsuario,id);
        logic.find(idUsuario).getEstados().remove(ent);
        remove(id);
    }
}

