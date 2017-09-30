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
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author camilo
 */
@Stateless
public class AtributoDeCalidadLogic extends BaseLogic<AtributoDeCalidadEntity>{
    /**
     * inject de la logica de estado
     */
    @Inject
    private ObjetivoLogic objetivoLogic;
    /**
    * inject de la logica de medida
    */
    @Inject
    private TipoMedidaLogic tipoMedidaLogic;
    /**
     * metodo que crea o asocia una medida a un estado
     * @param idObjetivo id del objetivo
     * @param entity el atributo de calidad a asociar
     * @param idTipoMedida el tipod e medida de la medida
     * @return el atributo de calidad creado
     * @throws BusinessLogicException si el objetivo no existe 
     */
    public AtributoDeCalidadEntity create(Long idObjetivo, AtributoDeCalidadEntity entity,Long idTipoMedida) throws BusinessLogicException {
        ObjetivoEntity objetivo = objetivoLogic.find(idObjetivo);
        TipoMedidaEntity tipoMedida = tipoMedidaLogic.find(idTipoMedida);
        entity.setObjetivo(objetivo);
        AtributoDeCalidadEntity est=create(entity);
        entity.setTipoMedida(tipoMedida);
        return est;
    }
    /**
     * metodo que encuentra todos los atribtos de calidad de un obetivo
     * @param idObjetivo id del objetivo
     * @return lista de atributos de calidad del objetivo
     * @throws BusinessLogicException si el objetivo no existe
     */
    public List<AtributoDeCalidadEntity> findAll(Long idObjetivo) throws BusinessLogicException {
        return objetivoLogic.find(idObjetivo).getAtributos();
    }
    /**
     * metodo que encuentra un atributo de calidad de un objetivo especifico
     * @param idObjetivo id del objetivo
     * @param id del atributo de calidad
     * @return el atributo de calidad
     * @throws BusinessLogicException el objetivo o el atrbuto de calidad no existen 
     */
    public AtributoDeCalidadEntity find(Long idObjetivo, Long id) throws BusinessLogicException {
        AtributoDeCalidadEntity ent = new AtributoDeCalidadEntity();
        ent.setId(id);
        List<AtributoDeCalidadEntity> list = objetivoLogic.find(idObjetivo).getAtributos();
        int ind = list.indexOf(ent);
        if (ind < 0) {
            throw new NoExisteException(id);
        }
        return list.get(ind);
    }
    /**
     * metodo para actualizar el atributo de calidad
     * @param idObjetivo id del objetivo
     * @param entity atribto a actualizar
     * @return el atributo actulizado
     * @throws BusinessLogicException si el objetivo o la medida no existen 
     */
    public AtributoDeCalidadEntity update(Long idObjetivo, AtributoDeCalidadEntity entity) throws BusinessLogicException {
        AtributoDeCalidadEntity old = find(entity.getId());
        if (!old.getObjetivo().getId().equals(idObjetivo)) {
            throw new NoExisteException(idObjetivo);
        }
        entity.setObjetivo(old.getObjetivo());
        entity.setTipoMedida(old.getTipoMedida());
        return persistence.update(entity);
    }
    /**
     * metodo que elimina una medida de un estado
     * @param idObjetivo id del objetivo
     * @param id de el atributo De calidad
     * @throws BusinessLogicException si el estado o la medida no existe 
     */
     public void remove(long idObjetivo,long id) throws BusinessLogicException {
        AtributoDeCalidadEntity ent=find(idObjetivo,id);
        objetivoLogic.find(idObjetivo).getAtributos().remove(ent);
        remove(id);
    }  
}
