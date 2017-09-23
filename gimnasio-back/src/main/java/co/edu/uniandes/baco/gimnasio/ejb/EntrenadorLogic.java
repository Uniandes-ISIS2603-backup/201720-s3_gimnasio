package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EntrenadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorLogic extends BaseLogic<EntrenadorEntity>{
    
    public EntrenadorEntity createEntrenador(EntrenadorEntity entity) throws  Exception
    {
        if(((EntrenadorPersistence)persistence).findByDocumento(entity.getDocumento())!= null)
            throw new BusinessLogicException("ya exixte un entrenador con ese");
        return super.create(entity);
    }    
}
