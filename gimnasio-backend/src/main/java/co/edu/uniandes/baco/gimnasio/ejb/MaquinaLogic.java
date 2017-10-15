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
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaLogic extends BaseLogic<MaquinaEntity>{
   
    private TipoMedidaLogic tipoMedidaLogic;

    public MaquinaLogic() {
        super();
    }

    @Inject public MaquinaLogic(TipoMedidaLogic tipoMedidaLogic, BasePersistence<MaquinaEntity> persistence) {
        super(persistence);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }
    
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException{
        return find(id).getEjercicios();
    }
    
    //-----------------------------------
    // TIPOMEDIDA
    //-----------------------------------
    public List<TipoMedidaEntity> findAllTipoMedidaMaquina(Long id) throws BusinessLogicException {
        return find(id).getTipoMedida();
    }

    public TipoMedidaEntity findTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list = find(idMaquina).getTipoMedida();
        int ind = list.indexOf(aux);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }

    public TipoMedidaEntity createTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        TipoMedidaEntity aux = tipoMedidaLogic.find(id);
        if (!aux.isAutomatico()) {
            throw new BusinessLogicException("no se puede asociar una medida manual a una maquina");
        }
        find(idMaquina).getTipoMedida().add(aux);
        return aux;
    }

    public void removeTipoMedida(Long idMaquina, Long id) throws BusinessLogicException {
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list = find(idMaquina).getTipoMedida();
        int ind = list.indexOf(aux);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        list.remove(aux);
    }
}
