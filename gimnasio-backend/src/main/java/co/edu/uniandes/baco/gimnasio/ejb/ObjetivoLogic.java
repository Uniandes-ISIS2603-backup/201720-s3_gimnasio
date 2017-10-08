/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity>{

    public ObjetivoLogic() {
        super();
    }
    
    public ObjetivoLogic(BasePersistence<ObjetivoEntity> persistence){
        super(persistence);
    }
    
    /**
     * metodo para crear un objetivo
     * @param entity el objetivo a crear
     * @return el objetico creado
     * @throws BusinessLogicException si ya existe un objetivo de este estilo 
     */
    @Override
    public ObjetivoEntity create(ObjetivoEntity entity)throws BusinessLogicException{
        if(null!=((ObjetivoPersistence)persistence).findByTipo(entity.getTipo()))
            throw new BusinessLogicException("ya existe un objetivo con ese tipo");
        return super.create(entity); 
    }
    
    public List<UsuarioEntity> findAllUsuarios(Long id) throws BusinessLogicException{
        return find(id).getUsuarios();
    }
}
