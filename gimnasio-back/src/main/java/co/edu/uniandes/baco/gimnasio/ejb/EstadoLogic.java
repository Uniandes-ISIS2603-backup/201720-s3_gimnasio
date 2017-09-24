/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;

import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic extends BaseLogic<EstadoEntity>{
    @Inject
    private MedidaLogic medidalogic;


    public MedidaEntity addMedida(Long idmedida,Long idEstado) throws BusinessLogicException
    {
        EstadoEntity estado = this.find(idEstado);
        MedidaEntity medida = medidalogic.find(idmedida);
        medida.setEstado(estado);
        return medida;
    }
    public void removeMedida(Long idmedida, Long idEstado) throws BusinessLogicException
    {
    EstadoEntity editorialEntity = this.find(idEstado);
    MedidaEntity medida = medidalogic.find(idmedida);
    medida.setEstado(null);
    editorialEntity.getMedidas().remove(medida);
    }
    public List<MedidaEntity> medidas(Long idEstado) throws BusinessLogicException 
    {
        return find(idEstado).getMedidas();
    }
    
    public MedidaEntity getMedida(Long idMedida,Long idEstado) throws BusinessLogicException 
    {
        List<MedidaEntity> medidas = find(idEstado).getMedidas();
        MedidaEntity medida = medidalogic.find(idMedida);
        int index = medidas.indexOf(medida);
        if (index >= 0) {
            return medidas.get(index);
        }
         throw new BusinessLogicException("la medida no existe");
    }


}

