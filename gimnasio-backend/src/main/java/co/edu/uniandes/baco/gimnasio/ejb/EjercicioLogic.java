package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class EjercicioLogic extends BaseLogic<EjercicioEntity> {

    private Connection<EjercicioEntity, ObjetivoEntity> connObjetivo;
    private Connection<EjercicioEntity, MaquinaEntity> connMaquina;
    private Connection<EjercicioEntity, TipoMedidaEntity> connTipoMedida;
    private Connection<EjercicioEntity, EjercicioHechoEntity> connHecho;
    private Search<EjercicioEntity, EjercicioInstanciaEntity> connInstancias;

    public EjercicioLogic() {
        super();
    }

    @Inject
    public EjercicioLogic(BasePersistence<EjercicioEntity> persistence) {
        super(persistence);
        this.connMaquina = new Connection<>(persistence, EjercicioEntity::getMaquinas, MaquinaEntity.class);
        this.connObjetivo = new Connection<>(persistence, EjercicioEntity::getObjetivosEjercicio, ObjetivoEntity.class);
        this.connTipoMedida = new Connection<>(persistence, EjercicioEntity::getTiposMedidas, TipoMedidaEntity.class);
        this.connInstancias = new Search<>(persistence, EjercicioEntity::getInstancias, EjercicioInstanciaEntity.class);
    }

    //-----------------------------------
    // OBJETIVO
    //-----------------------------------
    /**
     * Metodo apra encontrar todo lso objetivos
     *
     * @param id del ejercicio
     * @return lista con los objetivos
     * @throws BusinessLogicException si no eciste el jercicio
     */
    public List<ObjetivoEntity> findAllObjetivo(Long id) throws BusinessLogicException {
        return connObjetivo.findAll(id);
    }

    /**
     * metodo apra encontrar un objetivo de un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @return el objetivo
     * @throws BusinessLogicException si no existe el objetivo
     */
    public ObjetivoEntity findObjetivo(Long idEjercicio, Long id) throws BusinessLogicException {
        return connObjetivo.find(idEjercicio, id);
    }

    /**
     * metodo para crear un objetivo en el ejercicios
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo a añadir
     * @return retorna el objetivo agregado al ejercicio
     * @throws BusinessLogicException si el objetivo no existe o el ejercicio no
     * existe
     */
    public ObjetivoEntity createObjetivo(Long idEjercicio, Long id) throws BusinessLogicException {
        return connObjetivo.create(idEjercicio, id);
    }

    /**
     * metodo que remueve un objetivo de un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @throws BusinessLogicException si el ejercicio no existe, o el objetivo
     * no existe
     */
    public void removeObejtivo(Long idEjercicio, Long id) throws BusinessLogicException {
        connObjetivo.remove(idEjercicio, id);
    }

    //-----------------------------------
    // MAQUINA
    //-----------------------------------
    public List<MaquinaEntity> findAllMaquina(Long id) throws BusinessLogicException {
        return connMaquina.findAll(id);
    }

    /**
     * metodo para encontrar una mquina especifica
     *
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @return la maquina
     * @throws BusinessLogicException si el ejercicio o la maquina no existe
     */
    public MaquinaEntity findMaquina(Long idEjercicio, Long id) throws BusinessLogicException {
        return connMaquina.find(idEjercicio, id);
    }

    /**
     * metodo para crear o asociasr una maquina a un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @return la maquian creada o asociada
     * @throws BusinessLogicException si la maquina no existe
     */
    public MaquinaEntity createMaquina(Long idEjercicio, Long id) throws BusinessLogicException {
        return connMaquina.create(idEjercicio, id);
    }

    /**
     * metodo que elimina una maquina de un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id de la maquina
     * @throws BusinessLogicException si la maquina o el ejercicio no existen
     */
    public void removeMaquina(Long idEjercicio, Long id) throws BusinessLogicException {
        connMaquina.remove(idEjercicio, id);
    }

    //-----------------------------------
    // TIPOMEDIDA
    //-----------------------------------
    /**
     * Metodo apra encontrar todo lso objetivos
     *
     * @param id del ejercicio
     * @return lista con los objetivos
     * @throws BusinessLogicException si no eciste el jercicio
     */
    public List<TipoMedidaEntity> findAllTipoMedida(Long id) throws BusinessLogicException {
        return connTipoMedida.findAll(id);
    }

    /**
     * metodo apra encontrar un objetivo de un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @return el objetivo
     * @throws BusinessLogicException si no existe el objetivo
     */
    public TipoMedidaEntity findTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException {
        return connTipoMedida.find(idEjercicio, id);
    }

    /**
     * metodo para crear un objetivo en el ejercicios
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo a añadir
     * @return retorna el objetivo agregado al ejercicio
     * @throws BusinessLogicException si el objetivo no existe o el ejercicio no
     * existe
     */
    public TipoMedidaEntity createTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException {
        return connTipoMedida.create(idEjercicio, id);
    }

    /**
     * metodo que remueve un objetivo de un ejercicio
     *
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @throws BusinessLogicException si el ejercicio no existe, o el objetivo
     * no existe
     */
    public void removeTipoMedida(Long idEjercicio, Long id) throws BusinessLogicException {
        connTipoMedida.remove(idEjercicio, id);
    }
    //-----------------------------------
    // TIPOMEDIDA
    //-----------------------------------

    public List<EjercicioInstanciaEntity> findAllEjercicioInstancia(Long id) throws BusinessLogicException {
        return connInstancias.findAll(id);
    }

    public EjercicioInstanciaEntity findEjercicioInstancia(Long idEjercicio, Long id) throws BusinessLogicException {
        return connInstancias.find(idEjercicio, id);
    }

    //-----------------------------------
    //EJERCICIO HECHO
    //-----------------------------------
    
    public List<EjercicioHechoEntity> findAllEjerciciosHechos(Long id)throws BusinessLogicException {
        return connHecho.findAll(id);
    }

    public EjercicioHechoEntity findEjercicioHecho(Long idEjercicio, Long id) throws BusinessLogicException {
        return connHecho.find(idEjercicio, id);
    }

    public EjercicioHechoEntity createEjercicioHecho(Long idEjercicio, Long id)throws BusinessLogicException {
        return connHecho.create(idEjercicio, id);
    }

    public void removeEjercicioHecho(Long idEjercicio, Long id) throws BusinessLogicException {
        connHecho.remove(idEjercicio, id);
    }

}
