/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaLogic extends BaseLogic<MaquinaEntity> {

    private TipoMedidaLogic tipoMedidaLogic;
    private Connection<MaquinaEntity, TipoMedidaEntity> connTipoMedida;
    private Search<MaquinaEntity, EjercicioEntity> connEjercio;

    public MaquinaLogic() {
        super();
    }

    @Inject
    public MaquinaLogic(TipoMedidaLogic tipoMedidaLogic, BasePersistence<MaquinaEntity> persistence) {
        super(persistence);
        this.connTipoMedida = new Connection<>(persistence, MaquinaEntity::getTipoMedida, TipoMedidaEntity.class);
        this.connEjercio = new Search<>(persistence, MaquinaEntity::getEjercicios, EjercicioEntity.class);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }

    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException {
        return connEjercio.findAll(id);
    }

    public EjercicioEntity findEjercicio(Long id, Long idEjercicio) throws BusinessLogicException {
        return connEjercio.find(id, idEjercicio);
    }

    //-----------------------------------
    // TIPOMEDIDA
    //-----------------------------------
    public List<TipoMedidaEntity> findAllTipoMedidaMaquina(Long id) throws BusinessLogicException {
        return connTipoMedida.findAll(id);
    }

    public TipoMedidaEntity findTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        return connTipoMedida.find(idMaquina, id);
    }

    public TipoMedidaEntity createTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        TipoMedidaEntity aux = tipoMedidaLogic.find(id);
        if (!aux.isAutomatico()) {
            throw new BusinessLogicException("no se puede asociar una medida manual a una maquina");
        }
        return connTipoMedida.create(idMaquina, id);
    }

    public void removeTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        connTipoMedida.remove(idMaquina, id);
    }
}
