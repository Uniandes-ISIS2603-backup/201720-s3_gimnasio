/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;

import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic extends BaseLogic<EstadoEntity>{
    @Inject
    private MedidaLogic medidalogic;
    @Inject 
    private UsuarioLogic ulogic;


    public MedidaEntity create(Long EstadoId,MedidaEntity entity) throws BusinessLogicException {
    EstadoEntity estadi= persistence.find(EstadoId);
    estadi.getMedidas().add(entity);
    entity.setEstado(estadi);
    return medidalogic.create(entity);
    }
    public void removeMedida(Long idmedida, Long idEstado) throws BusinessLogicException
    {
    EstadoEntity editorialEntity = persistence.find(idEstado);
    MedidaEntity medida = medidalogic.find(idmedida);
    medida.setEstado(null);
    editorialEntity.getMedidas().remove(medida);
    }
    public List<MedidaEntity> medidas(Long idEstado) throws BusinessLogicException 
    {
        return persistence.find(idEstado).getMedidas();
    }
    
    ///
    ///
    public EstadoEntity find(Long idUsuario,Long id) throws BusinessLogicException 
    {
       EstadoEntity ent=new EstadoEntity();
       ent.setId(id);
       List<EstadoEntity> list=ulogic.find(id).getEstados();
        int ind=list.indexOf(ent);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
        public List<EstadoEntity> findAll(long idUsuario) throws BusinessLogicException {
        return ulogic.find(idUsuario).getEstados();
    }
     
        public EstadoEntity update(Long idUsuario,EstadoEntity entity) throws BusinessLogicException {
        EstadoEntity old=find(entity.getId());
        if(!old.getUsuario().getId().equals(idUsuario))
            throw new NoExisteException(idUsuario);
        entity.setUsuario(old.getUsuario());
        entity.setUsuario(old.getUsuario());
        return persistence.update(entity);
    }    

}

