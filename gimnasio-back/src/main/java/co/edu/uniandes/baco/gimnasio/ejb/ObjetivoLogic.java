/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import javax.ejb.Stateless;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity>{

    @Override
    public ObjetivoEntity create(ObjetivoEntity entity)throws BusinessLogicException, Exception{
        if(null!=((ObjetivoPersistence)persistence).findByTipo(entity.getTipo()))
            throw new BusinessLogicException("ya esiste un objetivo con ese tipo");
        return super.create(entity); 
    }
    
}
