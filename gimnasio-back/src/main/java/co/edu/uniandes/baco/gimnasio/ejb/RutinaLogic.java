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
    /**
     * injecticon de la clase UsuarioLÑogic
     */
    @Inject
    UsuarioLogic logic;
    
    /**
     * Metodo que retorna todas las rutinas de un usuario
     * @param idUsuario id del usuarioa  buscar
     * @return lista de las rutinas
     * @throws BusinessLogicException si el usuario no existe  
     */
    public List<RutinaEntity> findAll(long idUsuario) throws BusinessLogicException {
        return logic.find(idUsuario).getRutinas();
    }
    
    /**
     * encuentra una rutina especifica
     * @param idUsuario id del usuario
     * @param id de la rutina especifica
     * @return la rutina especifica
     * @throws BusinessLogicException si la rutina o el usuario noe xiste
     */
    public RutinaEntity find(long idUsuario,long id) throws BusinessLogicException {
       RutinaEntity ent=new RutinaEntity();
       ent.setId(id);
       List<RutinaEntity> list=logic.find(idUsuario).getRutinas();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * actuliza una rutina de un uduario
     * @param idUsuario id del usuario
     * @param entity rutina actulizada
     * @return la rutina actulizada
     * @throws BusinessLogicException  
     */
    public RutinaEntity update(Long idUsuario,RutinaEntity entity) throws BusinessLogicException {
        RutinaEntity old=find(entity.getId());
        if(!old.getUsuario().getId().equals(idUsuario))
            throw new NoExisteException(idUsuario);
        entity.setUsuario(old.getUsuario());
        entity.setEjercicios(old.getEjercicios());
        return persistence.update(entity);
    }
    /**
     * crea una nueva rutina a un uduario
     * @param idUsuario id del usuario
     * @param entity rutina a crear
     * @return la medida creada
     * @throws BusinessLogicException 
     */
    public RutinaEntity create(long idUsuario,RutinaEntity entity) throws BusinessLogicException {
        UsuarioEntity usuario=logic.find(idUsuario);
        entity.setUsuario(usuario);
        return create(entity);
    }
    /**
     * metodo para borrar una rutina
     * @param idUsuario del usuario 
     * @param id id de la rutina a borrar
     * @throws BusinessLogicException si al rutina no existe
     */
    public void remove(long idUsuario,long id) throws BusinessLogicException {
        RutinaEntity ent=find(idUsuario,id);
        logic.find(idUsuario).getRutinas().remove(ent);
        remove(id);
    }
}
