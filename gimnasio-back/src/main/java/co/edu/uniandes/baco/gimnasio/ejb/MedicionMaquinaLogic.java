/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;

/**
 *
 * @author ce.robles
 */
public class MedicionMaquinaLogic extends BaseLogic<MedicionMaquinaEntity>
{
    @Override
    public MedicionMaquinaEntity create(MedicionMaquinaEntity entity) throws Exception 
    {
        if(entity.getMedicionManquina() == null)
            throw new BusinessLogicException("No se puede ingresaruna medicion vacia.");          
        
        return super.create(entity); 
    }
}
