/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author camilo
 */
@Stateless
public class AtributoDeCalidadLogic extends SubResource<ObjetivoEntity,AtributoDeCalidadEntity>{
    private TipoMedidaLogic tipoMedidaLogic;

    public AtributoDeCalidadLogic() {
        super();
    }

    @Inject public AtributoDeCalidadLogic(ObjetivoLogic objetivoLogic, TipoMedidaLogic tipoMedidaLogic, BasePersistence<AtributoDeCalidadEntity> persistence) {
        super(persistence, objetivoLogic, ObjetivoEntity::getAtributos, AtributoDeCalidadEntity::setObjetivo);
        this.tipoMedidaLogic = tipoMedidaLogic;
    }
    
    /**
     * metodo que crea o asocia una medida a un estado
     * @param idObjetivo id del objetivo
     * @param entity el atributo de calidad a asociar
     * @param idTipoMedida el tipod e medida de la medida
     * @return el atributo de calidad creado
     * @throws BusinessLogicException si el objetivo no existe 
     */
    public AtributoDeCalidadEntity create(Long idObjetivo, AtributoDeCalidadEntity entity,Long idTipoMedida) throws BusinessLogicException {
        TipoMedidaEntity tipoMedida = tipoMedidaLogic.find(idTipoMedida);
        AtributoDeCalidadEntity est=create(idObjetivo, entity);
        entity.setTipoMedida(tipoMedida);
        return est;
    }
    /**
     * metodo para actualizar el atributo de calidad
     * @param idObjetivo id del objetivo
     * @param entity atribto a actualizar
     * @return el atributo actulizado
     * @throws BusinessLogicException si el objetivo o la medida no existen 
     */
    @Override
    public AtributoDeCalidadEntity update(Long idObjetivo, AtributoDeCalidadEntity entity) throws BusinessLogicException {
        AtributoDeCalidadEntity old = find(entity.getId());
        if (!old.getObjetivo().getId().equals(idObjetivo)) {
            throw new NoExisteException(idObjetivo);
        }
        entity.setObjetivo(old.getObjetivo());
        entity.setTipoMedida(old.getTipoMedida());
        return persistence.update(entity);
    }
}
