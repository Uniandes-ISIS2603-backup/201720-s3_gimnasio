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
    
    /**
     * injecion de la logica de ejercicio
     */
    @Inject
    private EjercicioLogic ejercicioLogic;
    /**
     * metodo que encuentra todas las maquinas de un ejercicioi
     * @param id del ejercicio
     * @return lista con las maquinas de este
     * @throws BusinessLogicException  si el ejercicio no existe
     */
     public List<MaquinaEntity> findAllMaquinas(Long id) throws BusinessLogicException{
        return ejercicioLogic.find(id).getMaquinas();        
    }
    /**
     * metodo para encontrar una mquina especifica
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @return la maquina 
     * @throws BusinessLogicException si el ejercicio o la maquina no existe 
     */
    public MaquinaEntity findMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        List<MaquinaEntity> list=ejercicioLogic.find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo para crear o asociasr una maquina a un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @return la maquian creada o asociada
     * @throws BusinessLogicException si la maquina no existe 
     */
    public MaquinaEntity createMaquina(Long idEjercicio, Long id) throws BusinessLogicException{
        MaquinaEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        ejercicioLogic.find(idEjercicio).getMaquinas().add(aux);
        return aux;
    }
    /**
     * metodo que elimina una maquina de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @throws BusinessLogicException si la maquina o el ejercicio no existen
     */
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
