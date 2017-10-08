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
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic extends BaseLogic<EstadoEntity>{
    /**
     * injecion de la logica de usuario
     */
    
    UsuarioLogic logic;

    public EstadoLogic() {
        super();
    }

    @Inject public EstadoLogic(UsuarioLogic logic, BasePersistence<EstadoEntity> persistence) {
        super(persistence);
        this.logic = logic;
    }

    /**
     * metodo que encuentra todos los estados de un usuario
     * @param idUsuario id del usuario
     * @return los estados del usuario
     * @throws BusinessLogicException  si el usuario no existe
     */
    public List<EstadoEntity> findAll(long idUsuario) throws BusinessLogicException {
        return logic.find(idUsuario).getEstados();
    }
    /**
     * metodo que encuentra un estado 
     * @param idUsuario id del usuario
     * @param id del estado
     * @return el estado
     * @throws BusinessLogicException si el estado o el usuario no existen 
     */
    public EstadoEntity find(long idUsuario,long id) throws BusinessLogicException {
       EstadoEntity ent=new EstadoEntity();
       ent.setId(id);
       List<EstadoEntity> list=logic.find(idUsuario).getEstados();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo que actuliza un estado de un usuario
     * @param idUsuario id del usuario
     * @param entity estado a actulizar
     * @return el estado actuilzado
     * @throws BusinessLogicException si el usuario o el estado no existen 
     */
    public EstadoEntity update(Long idUsuario,EstadoEntity entity) throws BusinessLogicException {
        EstadoEntity old=find(entity.getId());
        if(!old.getUsuario().getId().equals(idUsuario))
            throw new NoExisteException(idUsuario);
        entity.setUsuario(old.getUsuario());
        entity.setMedidas(old.getMedidas());
        return update(entity);
    }
    /**
     * metodo que crea un estado 
     * @param idUsuario id del usuario
     * @param entity estado a crear
     * @return el estado creado
     * @throws BusinessLogicException si el usuario no existe 
     */
    public EstadoEntity create(long idUsuario,EstadoEntity entity) throws BusinessLogicException {
        UsuarioEntity usuario=logic.find(idUsuario);
        entity.setUsuario(usuario);
        return create(entity);
    }
    /**
     * metodo que elimina un estado de un usuario
     * @param idUsuario id del usuario
     * @param id id del estado
     * @throws BusinessLogicException si el estado o el usuariuo no existen 
     */
    public void remove(long idUsuario,long id) throws BusinessLogicException {
        EstadoEntity ent=find(idUsuario,id);
        logic.find(idUsuario).getEstados().remove(ent);
        remove(id);
    }
}

