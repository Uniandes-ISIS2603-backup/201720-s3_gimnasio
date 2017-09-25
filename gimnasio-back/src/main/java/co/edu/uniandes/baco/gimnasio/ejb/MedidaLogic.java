/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author js.palacios437
 */

@Stateless

public class MedidaLogic extends BaseLogic<MedidaEntity>{

    @Inject
    private ParteDelCuerpoLogic pclogic;
    
    public ParteDelCuerpoEntity addPartedelcuerpo(Long pcid,Long idMedida)throws BusinessLogicException
    {
        MedidaEntity medida = this.find(idMedida);
        ParteDelCuerpoEntity pc = pclogic.find(pcid);
        if(pc!=null)
        {
           medida.setParte(pc);
        }
        else
        {
            throw new BusinessLogicException("no existe esa parte del cuerpo");
        }
        return pc;
    }
    
}
