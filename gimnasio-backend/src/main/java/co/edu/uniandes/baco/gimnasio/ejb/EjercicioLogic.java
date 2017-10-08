package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
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

    /**
     * injecion de la logica de rutina
     */
    private RutinaLogic rutinaLogic;

    public EjercicioLogic() {
        super();
    }

    @Inject public EjercicioLogic(RutinaLogic rutinaLogic, BasePersistence<EjercicioEntity> persistence) {
        super(persistence);
        this.rutinaLogic = rutinaLogic;
    }

    public List<EjercicioEntity> findAll(long idRutina) throws BusinessLogicException {
        return rutinaLogic.find(idRutina).getEjercicios();
    }

    /**
     * metodo que encuentra un ejercicio en una rutina
     *
     * @param idRutina id de la rutina
     * @param id del ejercicio
     * @return el ejercicio buscado
     * @throws BusinessLogicException si la rutina o el ejercicio no existen
     */
    public EjercicioEntity find(long idRutina, long id) throws BusinessLogicException {
        EjercicioEntity ent = new EjercicioEntity();
        ent.setId(id);
        List<EjercicioEntity> list = rutinaLogic.find(idRutina).getEjercicios();
        int ind = list.indexOf(ent);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }

    /**
     * metodo para actulizar un ejercicio en una rutina
     *
     * @param idRutina id de la rutina
     * @param entity ejercicio a actulizar
     * @return el ejercicio actulizado
     * @throws BusinessLogicException si la rutino o el ejercicio no existen
     */
    public EjercicioEntity update(Long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        EjercicioEntity old = find(entity.getId());
        if (!old.getRutina().getId().equals(idRutina)) {
            throw new NoExisteException(idRutina);
        }
        entity.setRutina(old.getRutina());
        entity.setObjetivosEjercicio(old.getObjetivosEjercicio());
        entity.setMaquinas(old.getMaquinas());
        return update(entity);
    }

    /**
     * metodo que crea o asocia un ejercicio a una rutina
     *
     * @param idRutina id de la rutina
     * @param entity el ejercicio
     * @return el ejercicio creado
     * @throws BusinessLogicException si la rutina no existe
     */
    public EjercicioEntity create(long idRutina, EjercicioEntity entity) throws BusinessLogicException {
        entity.setRutina(rutinaLogic.find(idRutina));
        return create(entity);
    }

    /**
     * metodo que elimina un ejercicio de una rutina
     *
     * @param idRutina id de la rutina
     * @param id del ejercicioi
     * @throws BusinessLogicException si la rutina o el ejercicio no existen
     */
    public void remove(long idRutina, long id) throws BusinessLogicException {
        EjercicioEntity ent = find(idRutina, id);
        if (ent == null) {
            throw new NoExisteException(id);
        }
        rutinaLogic.find(idRutina).getEjercicios().remove(ent);
        remove(id);
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
}
