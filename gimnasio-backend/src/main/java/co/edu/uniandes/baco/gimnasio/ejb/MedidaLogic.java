/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */

@Stateless

public class MedidaLogic extends BaseLogic<MedidaEntity> {
    /**
     * inject de la logica de estado
     */
    @Inject
    private EstadoLogic elogic;
    /**
    * inject de la logica de medida
    */
    @Inject
    private TipoMedidaLogic tipoMedidaLogic;
    /**
     * metodo que crea o asocia una medida a un estado
     * @param idEstado id del estado
     * @param entity la medida a asociar
     * @param idTipoMedida el tipod e medida de la medida
     * @return la medida creada
     * @throws BusinessLogicException si estado no existe 
     */
    public MedidaEntity create(Long idEstado, MedidaEntity entity,Long idTipoMedida) throws BusinessLogicException {
        EstadoEntity estado = elogic.find(idEstado);
        TipoMedidaEntity parte = tipoMedidaLogic.find(idTipoMedida);
        if(parte==null)
            throw new NoExisteException(idTipoMedida);
        if(parte.isAutomatico())
            throw new BusinessLogicException("no se puede asociar una medidcion manual a una medida automatica");
        entity.setEstado(estado);
        MedidaEntity est=create(entity);
        entity.setParte(parte);
        return est;
    }
    /**
     * metodo que encuentra todas las medidas de un etado
     * @param Idestado id del estado
     * @return lista de medidas del estado
     * @throws BusinessLogicException si el estado no existe
     */
    public List<MedidaEntity> findAll(Long idestado) throws BusinessLogicException {
        return elogic.find(idestado).getMedidas();
    }
    /**
     * metodo que encuentra una medida en especifico
     * @param idEstado id del estado
     * @param id de la medida
     * @return la medida
     * @throws BusinessLogicException si ele stado o la medida no existen 
     */
    public MedidaEntity find(Long idEstado, Long id) throws BusinessLogicException {
        MedidaEntity ent = new MedidaEntity();
        ent.setId(id);
        List<MedidaEntity> list = elogic.find(idEstado).getMedidas();
        int ind = list.indexOf(ent);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }
    /**
     * metodo para actulizar una medida
     * @param idEstado id del estado
     * @param entity medida actulizar
     * @return la medida actulizada
     * @throws BusinessLogicException si el estado o la medida noe xisten 
     */
    public MedidaEntity update(Long idEstado, MedidaEntity entity) throws BusinessLogicException {
        MedidaEntity old = find(entity.getId());
        if (!old.getEstado().getId().equals(idEstado)) {
            throw new NoExisteException(idEstado);
        }
        entity.setEstado(old.getEstado());
        return persistence.update(entity);
    }
    /**
     * metodo que elimina una medida de un estado
     * @param idEstado id del estado
     * @param id de la medida
     * @throws BusinessLogicException si el estado o la medida no existe 
     */
     public void remove(long idEstado,long id) throws BusinessLogicException {
        MedidaEntity ent=find(idEstado,id);
        elogic.find(idEstado).getMedidas().remove(ent);
        remove(id);
    }
}
