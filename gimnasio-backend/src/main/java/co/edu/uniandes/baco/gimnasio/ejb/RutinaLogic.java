/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.MedicionMaquinaPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.RutinaPersistence;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
public class RutinaLogic extends SubResource<UsuarioEntity, RutinaEntity> {

    public RutinaLogic() {
        super();
    }

    @Inject
    public RutinaLogic(UsuarioLogic logic, BasePersistence<RutinaEntity> persistence) {
        super(persistence, logic, UsuarioEntity::getRutinas, RutinaEntity::setUsuario);
    }

    public RutinaEntity getLast(long idUsuario) {
        return ((RutinaPersistence) persistence).last(idUsuario);
    }
}
