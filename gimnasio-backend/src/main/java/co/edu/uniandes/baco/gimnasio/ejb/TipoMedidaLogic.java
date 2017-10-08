/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

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
public class TipoMedidaLogic extends BaseLogic<TipoMedidaEntity> {

    public TipoMedidaLogic() {
        super();
    }

    @Inject public TipoMedidaLogic(BasePersistence<TipoMedidaEntity> persistence) {
        super(persistence);
    }
    
     public List<TipoMedidaEntity> createList(List<TipoMedidaEntity> list) throws BusinessLogicException {
        for (int i = 0; i < list.size(); i++) {
            create(list.get(i));
        }
        return list;
    }
}
