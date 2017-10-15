package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity> {
    public EjercicioLogic() {
        super();
    }

    @Inject public EjercicioLogic(BasePersistence<EjercicioEntity> persistence) {
        super(persistence);
    }
    //-----------------------------------
    // OBJETIVO
    //-----------------------------------
    /**
     * Metodo apra encontrar todo lso objetivos 
     * @param id del ejercicio
     * @return lista con los objetivos
     * @throws BusinessLogicException si no eciste el jercicio
     */
    public List<ObjetivoEntity> findAllObjetivo(Long id) throws BusinessLogicException{
        return find(id).getObjetivosEjercicio();        
    }
    /**
     * metodo apra encontrar un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @return el objetivo
     * @throws BusinessLogicException si no existe el objetivo
     */
    public ObjetivoEntity findObjetivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivosEjercicio();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo para crear un objetivo en el ejercicios
     * @param idEjercicio id del ejercicio 
     * @param id del objetivo a añadir
     * @return retorna el objetivo agregado al ejercicio
     * @throws BusinessLogicException si el objetivo no existe o el ejercicio no existe
     */
    public ObjetivoEntity createObjetivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        find(idEjercicio).getObjetivosEjercicio().add(aux);
        return aux;
    }
    /**
     * metodo que remueve un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @throws BusinessLogicException si el ejercicio no existe, o el objetivo no existe 
     */
    public void removeObejtivo(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=find(idEjercicio).getObjetivosEjercicio();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(ind);
    }
    
    //-----------------------------------
    // MAQUINA
    //-----------------------------------
    
      public List<MaquinaEntity> findAllMaquina(Long id) throws BusinessLogicException{
        return find(id).getMaquinas();        
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
        List<MaquinaEntity> list=find(idEjercicio).getMaquinas();
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
        MaquinaEntity aux = new MaquinaEntity();
        aux.setId(id);
        find(idEjercicio).getMaquinas().add(aux);
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
        List<MaquinaEntity> list=find(idEjercicio).getMaquinas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
    //-----------------------------------
    // TIPOMEDIDA
    //-----------------------------------
    /**
     * Metodo apra encontrar todo lso objetivos 
     * @param id del ejercicio
     * @return lista con los objetivos
     * @throws BusinessLogicException si no eciste el jercicio
     */
    public List<TipoMedidaEntity> findAllTipoMedida(Long id) throws BusinessLogicException{
        return find(id).getTiposMedidas();
    }
    /**
     * metodo apra encontrar un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @return el objetivo
     * @throws BusinessLogicException si no existe el objetivo
     */
    public TipoMedidaEntity findTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list=find(idEjercicio).getTiposMedidas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo para crear un objetivo en el ejercicios
     * @param idEjercicio id del ejercicio 
     * @param id del objetivo a añadir
     * @return retorna el objetivo agregado al ejercicio
     * @throws BusinessLogicException si el objetivo no existe o el ejercicio no existe
     */
    public TipoMedidaEntity createTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        find(idEjercicio).getTiposMedidas().add(aux);
        return aux;
    }
    /**
     * metodo que remueve un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @throws BusinessLogicException si el ejercicio no existe, o el objetivo no existe 
     */
    public void removeTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list=find(idEjercicio).getTiposMedidas();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(ind);
    }
}
