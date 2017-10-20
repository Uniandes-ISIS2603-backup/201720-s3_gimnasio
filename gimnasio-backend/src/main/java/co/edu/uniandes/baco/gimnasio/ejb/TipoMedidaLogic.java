/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
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
    Search<TipoMedidaEntity, EjercicioEntity> connEjercicio;
    Search<TipoMedidaEntity, MedidaEntity> connMedidas;
    Search<TipoMedidaEntity, AtributoDeCalidadEntity> connAtributoDeCalidad;
    Search<TipoMedidaEntity, MedicionMaquinaEntity> connMedicionMaquina;
    
    public TipoMedidaLogic() {
        super();
    }

    @Inject public TipoMedidaLogic(BasePersistence<TipoMedidaEntity> persistence) {
        super(persistence);
        this.connEjercicio=new Search<>(persistence, TipoMedidaEntity::getEjercicios, EjercicioEntity.class);
        this.connMedidas= new Search<>(persistence, TipoMedidaEntity::getMedidas, MedidaEntity.class);
        this.connAtributoDeCalidad= new Search<>(persistence, TipoMedidaEntity::getAtributos, AtributoDeCalidadEntity.class);
        this.connMedicionMaquina=new Search<>(persistence, TipoMedidaEntity::getMedicion, MedicionMaquinaEntity.class);
    }
    
    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException{
        return connEjercicio.findAll(id);
    }
    
    public EjercicioEntity findEjercicio(Long id,Long idEjercicio) throws BusinessLogicException{
        return connEjercicio.find(id,idEjercicio);
    }
    
    //-----------------------------------
    // MEDIDA
    //-----------------------------------
    
    public List<MedidaEntity> findAllMedida(Long id) throws BusinessLogicException{
        return connMedidas.findAll(id);
    }
    
    public MedidaEntity findMedida(Long id,Long idMedida) throws BusinessLogicException{
        return connMedidas.find(id,idMedida);
    }
    
    //-----------------------------------
    // ATRIBUTO
    //-----------------------------------
    
    public List<AtributoDeCalidadEntity> findAllAtributoDeCalidad(Long id) throws BusinessLogicException{
        return connAtributoDeCalidad.findAll(id);
    }
    
    public AtributoDeCalidadEntity findAtributoDeCalidad(Long id,Long idAtributoDeCalidad) throws BusinessLogicException{
        return connAtributoDeCalidad.find(id,idAtributoDeCalidad);
    }
    
    //-----------------------------------
    //MEDICIONMAQUINA
    //-----------------------------------
    
    public List<MedicionMaquinaEntity> findAllMedicionMaquina(Long id) throws BusinessLogicException{
        return connMedicionMaquina.findAll(id);
    }
    
    public MedicionMaquinaEntity findMedicionMaquina(Long id,Long idMedicionMaquina) throws BusinessLogicException{
        return connMedicionMaquina.find(id,idMedicionMaquina);
    }
}
