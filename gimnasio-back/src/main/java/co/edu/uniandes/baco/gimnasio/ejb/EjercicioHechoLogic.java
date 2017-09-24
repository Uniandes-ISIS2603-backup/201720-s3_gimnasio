/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;

/**
 *
 * @author ce.robles
 */
public class EjercicioHechoLogic extends BaseLogic<EjercicioHechoEntity>
{
    @Override
    public EjercicioHechoEntity create(EjercicioHechoEntity entity) throws Exception 
    {
        if(entity.getFechaInicio() == null)
            throw new BusinessLogicException("No se puede ingresar una fecha vacia.");
        else if(entity.getSeriesReales() == null)
             throw new BusinessLogicException("Las series no pueden estar vacias.");
        
        return super.create(entity); 
    }
}
