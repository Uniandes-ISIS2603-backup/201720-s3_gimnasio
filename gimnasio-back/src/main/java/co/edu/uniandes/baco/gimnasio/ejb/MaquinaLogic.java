/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaLogic extends BaseLogic<MaquinaEntity>{
    //-----------------------------
    //EJERCICIO
    //-----------------------------
    @Inject
    private EjercicioLogic ejercicioLogic;
    
     public List<MaquinaEntity> findAllMaquinas(Long id) throws BusinessLogicException{
        return ejercicioLogic.find(id).getMaquinas();        
    }
    
    public MaquinaEntity findMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        List<MaquinaEntity> list=ejercicioLogic.find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public MaquinaEntity createMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        ejercicioLogic.find(idEjercicio).getMaquinas().add(aux);
        return aux;
    }
    
    public void removeMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        List<MaquinaEntity> list=ejercicioLogic.find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
}
