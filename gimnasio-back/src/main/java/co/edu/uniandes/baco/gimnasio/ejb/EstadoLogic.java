/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.EstadoPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import javax.ejb.Stateless;


/**
 *
 * @author js.palacios437
 */
@Stateless

public class EstadoLogic {
    
    @Inject
    private EstadoPersistence persistencia;
    @Inject
    private MedidaLogic medidalogic;
    
    /**
     * crear un nuevo estado 
     * @param entity
     * @return entity
     * @throws BusinessLogicException 
     */
    public EstadoEntity createEstado(EstadoEntity entity) throws BusinessLogicException, Exception
    {
        persistencia.create(entity);
        return entity;
    }
    
    /**
     * actualiza un estado 
     * @param entity
     * @return 
     */
    public EstadoEntity updateEstado(EstadoEntity entity) throws BusinessLogicException, Exception
    {
      return persistencia.update(entity);
    }
    
    public EstadoEntity getEstadoFecha(Date date)throws BusinessLogicException
    {
        return persistencia.findfecha(date);
    }
    
    public EstadoEntity getEstado(Long id) throws BusinessLogicException, Exception
    {
        return persistencia.find(id);
    }
    
    public void deleteEstado(Long id) throws BusinessLogicException, Exception
    {
        EstadoEntity com = persistencia.find(id);
        if(com!=null)
        persistencia.delete(id);
    }
    public List<EstadoEntity> getEstado() throws Exception
    {
      List<EstadoEntity> lista = persistencia.findAll();
      return lista;
    }

    public MedidaEntity addMedida(Long idmedida,Long idEstado)throws BusinessLogicException, Exception
    {
        EstadoEntity estado = getEstado(idEstado);
        MedidaEntity medida = medidalogic.getMedida(idmedida);
        medida.setEstado(estado);
        return medida;
    }
    public void removeMedida(Long idmedida, Long idEstado) throws BusinessLogicException, Exception
    {
    EstadoEntity editorialEntity = getEstado(idEstado);
    MedidaEntity medida = medidalogic.getMedida(idmedida);
    medida.setEstado(null);
    editorialEntity.getMedidas().remove(medida);
    }
    public List<MedidaEntity> medidas(Long idEstado) throws BusinessLogicException, Exception
    {
        return getEstado(idEstado).getMedidas();
    }
    
    public MedidaEntity getMedida(Long idMedida,Long idEstado) throws BusinessLogicException, Exception
    {
        List<MedidaEntity> medidas = getEstado(idEstado).getMedidas();
        MedidaEntity medida = medidalogic.getMedida(idMedida);
        int index = medidas.indexOf(medida);
        if (index >= 0) {
            return medidas.get(index);
        }
         throw new BusinessLogicException("la medida no existe");
    }


}

