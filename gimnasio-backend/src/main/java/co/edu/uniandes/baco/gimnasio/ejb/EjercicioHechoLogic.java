/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.robles
 */
@Stateless
public class EjercicioHechoLogic extends BaseLogic<EjercicioHechoEntity>
{

    public EjercicioHechoLogic() 
    {
        super();
    } 
    @Inject public EjercicioHechoLogic(BasePersistence<EjercicioHechoEntity> persistence){
        super(persistence);
    }
    
    //Revisar. Algo no me cuadra. ATT:Carlos
    @Override
    public EjercicioHechoEntity create(EjercicioHechoEntity entity)throws BusinessLogicException
    {
        if(null!=((EjercicioHechoPersistence)persistence).findByFecha(entity.getFechaInicio()))
            throw new BusinessLogicException("ya existe un ejercicio con esta fecha");
        
        return super.create(entity); 
    }

    @Override
    public EjercicioHechoEntity update(EjercicioHechoEntity entity) throws BusinessLogicException {
        EjercicioHechoEntity old=find(entity.getId());
        entity.setAtributos(old.getAtributos());
        return super.update(entity);
    }
    
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException
    {
        return find(id).getEjercicios();
    }
    
    public List<MedicionMaquinaEntity> findAllMedicion(Long id) throws BusinessLogicException
    {
        return find(id).getAtributos();
    }
    
    public MedicionMaquinaEntity findMedicion(Long idUsuario, Long id) throws BusinessLogicException{
        MedicionMaquinaEntity aux = new MedicionMaquinaEntity();
        aux.setId(id);
        List<MedicionMaquinaEntity> list = find(idUsuario).getAtributos();
        int ind=list.indexOf(aux);
        
        if(ind<0)
            throw new NoExisteException(idUsuario,id);
        
        return list.get(ind);
    }
    
    public MedicionMaquinaEntity createMedicion(Long idUsuario, Long id) throws BusinessLogicException
    {
        MedicionMaquinaEntity aux = new MedicionMaquinaEntity();
        aux.setId(id);
        find(idUsuario).getAtributos().add(aux);
        return findMedicion(idUsuario, id);
    }
    
    public void removeMedicion(Long idUsuario, Long id) throws BusinessLogicException
    {
        MedicionMaquinaEntity aux = new MedicionMaquinaEntity();
        aux.setId(id);
        find(idUsuario).getAtributos().remove(aux);
    }
    
    
}
