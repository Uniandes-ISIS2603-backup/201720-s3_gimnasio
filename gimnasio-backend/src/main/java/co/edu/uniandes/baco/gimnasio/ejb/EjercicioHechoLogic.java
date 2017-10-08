/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioHechoLogic extends BaseLogic<EjercicioHechoEntity>{

    @Inject public EjercicioHechoLogic(BasePersistence<EjercicioHechoEntity> persistence){
        super(persistence);
    }
    
    public EjercicioHechoLogic() {
        super();
    } 
}
