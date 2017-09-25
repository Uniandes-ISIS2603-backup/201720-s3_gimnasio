/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.persistence.ParteDelCuerpoPersitence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.palacios437
 */
@Stateless

public class ParteDelCuerpoLogic extends BaseLogic<ParteDelCuerpoEntity>{

    @Inject
    protected ParteDelCuerpoPersitence pc;
   public ParteDelCuerpoEntity findName(String name)
    {
        return pc.findName(name);
    }

}
