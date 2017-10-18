/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.robles
 */
@Stateless
public class MedicionMaquinaLogic extends BaseLogic<MedicionMaquinaEntity>{

    private EjercicioHechoLogic ejercicioHechoLogic;
    private TipoMedidaLogic tipoMedidaLogic;    
    
    public MedicionMaquinaLogic() {
        super();
    }
    
    @Inject public MedicionMaquinaLogic(EjercicioHechoLogic ejercicioHechoLogic, TipoMedidaLogic tipoMedidaLogic, BasePersistence<MedicionMaquinaEntity> persistence)
    {
        super(persistence);
        this.ejercicioHechoLogic = ejercicioHechoLogic;
        this.tipoMedidaLogic = tipoMedidaLogic;
    }
    
    public MedicionMaquinaEntity create(Long idEjercicioHecho, MedicionMaquinaEntity entity,Long idTipoMedida) throws BusinessLogicException 
    {
        EjercicioHechoEntity ejercicioHecho = ejercicioHechoLogic.find(idEjercicioHecho);
        TipoMedidaEntity tipoMedida = tipoMedidaLogic.find(idTipoMedida);
        entity.setEjercicioEnt(ejercicioHecho);
        MedicionMaquinaEntity est = create(entity);
        entity.setTipoMedida(tipoMedida);
        return est;
    }
    
    public List<MedicionMaquinaEntity> findAll(Long idEjercicioHecho) throws BusinessLogicException 
    {
        return ejercicioHechoLogic.find(idEjercicioHecho).getAtributos();
    }
    
    public MedicionMaquinaEntity find(Long idEjericioHecho, Long id) throws BusinessLogicException 
    {
        MedicionMaquinaEntity ent = new MedicionMaquinaEntity();
        ent.setId(id);
        List<MedicionMaquinaEntity> list = ejercicioHechoLogic.find(idEjericioHecho).getAtributos();
        int ind = list.indexOf(ent);
       
        if (ind < 0) 
            throw new NoExisteException(id);
        
        return list.get(ind);
    }
    
    public MedicionMaquinaEntity update(Long idEjercicioHecho, MedicionMaquinaEntity entity) throws BusinessLogicException {
        MedicionMaquinaEntity old = find(entity.getId());
        
        if (!old.getEjercicioEnt().getId().equals(idEjercicioHecho)) 
            throw new NoExisteException(idEjercicioHecho);
        
        entity.setEjercicioEnt(old.getEjercicioEnt());
        entity.setTipoMedida(old.getTipoMedida());
        return persistence.update(entity);
    }
    
    public void remove(long idEjercicioHecho,long id) throws BusinessLogicException 
    {
        MedicionMaquinaEntity ent= find(idEjercicioHecho,id);
        ejercicioHechoLogic.find(idEjercicioHecho).getAtributos().remove(ent);
        remove(id);
    }  
}
