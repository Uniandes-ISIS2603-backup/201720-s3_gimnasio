/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
public class RutinaLogic extends BaseLogic<RutinaEntity>{
    @Inject
    UsuarioLogic logic;
    
    public List<RutinaEntity> findAll(long idUsuario) throws BusinessLogicException {
        return logic.find(idUsuario).getRutinas();
    }
    
    public RutinaEntity find(long idUsuario,long id) throws BusinessLogicException {
       RutinaEntity ent=new RutinaEntity();
       ent.setId(id);
       List<RutinaEntity> list=logic.find(idUsuario).getRutinas();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }

    public RutinaEntity update(Long idUsuario,RutinaEntity entity) throws BusinessLogicException {
        RutinaEntity old=find(entity.getId());
        if(!old.getUsuario().getId().equals(idUsuario))
            throw new NoExisteException(idUsuario);
        entity.setUsuario(old.getUsuario());
        entity.setEjercicios(old.getEjercicios());
        return persistence.update(entity);
    }
    
    public RutinaEntity create(long idUsuario,RutinaEntity entity) throws BusinessLogicException {
        UsuarioEntity Usuario=logic.find(idUsuario);
        entity.setUsuario(Usuario);
        return create(entity);
    }

    public void remove(long idUsuario,long id) throws BusinessLogicException {
        RutinaEntity ent=find(idUsuario,id);
        logic.find(idUsuario).getRutinas().remove(ent);
        remove(id);
    }
}
