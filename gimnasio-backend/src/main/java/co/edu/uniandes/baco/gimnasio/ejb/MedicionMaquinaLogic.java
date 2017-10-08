/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class MedicionMaquinaLogic extends BaseLogic<MedicionMaquinaEntity>{

    public MedicionMaquinaLogic() {
        super();
    }
    
    @Inject public MedicionMaquinaLogic(BasePersistence<MedicionMaquinaEntity> persistence){
        super(persistence);
    }
}
