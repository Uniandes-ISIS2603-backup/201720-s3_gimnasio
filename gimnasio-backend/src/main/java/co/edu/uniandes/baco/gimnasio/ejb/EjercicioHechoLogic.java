/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.robles
 */
@Stateless
public class EjercicioHechoLogic extends SubResource<EjercicioInstanciaEntity, EjercicioHechoEntity>  
{
    //----------------------------------------------------------------------------------------
    //-----------------------------------Atributos--------------------------------------------
    //----------------------------------------------------------------------------------------
    
    /**
     * Conexion con Ejercicio.
     */
    private Search<EjercicioHechoEntity, EjercicioEntity> conEjercicio;
    
    /**
     * Conexion con MedicionMaquina.
     */
    private Connection<EjercicioHechoEntity, MedicionMaquinaEntity> connMedicion;
    
    //----------------------------------------------------------------------------------------
    //---------------------------------Constructores------------------------------------------
    //----------------------------------------------------------------------------------------
    
    public EjercicioHechoLogic() { super(); }
    
    @Inject
    public EjercicioHechoLogic(BasePersistence<EjercicioHechoEntity> persistence, EjercicioInstanciaLogic logic) 
    {
        super(persistence, logic, EjercicioInstanciaEntity::getEjerciciosHechos, EjercicioHechoEntity::setEjercicios);
        //this.conEjercicio=new Search<>(persistence, EjercicioHechoEntity::getEjercicioEnt, EjercicioEntity.class);
        this.connMedicion = new Connection<>(persistence, EjercicioHechoEntity::getMedicionMaquinaEnt, MedicionMaquinaEntity.class);
    }
    
    //----------------------------------------------------------------------------------------
    // ------------------------------------Metodos-----------------------------------------
    //----------------------------------------------------------------------------------------

    @Override
    public EjercicioHechoEntity update(Long idEjercicio, EjercicioHechoEntity s) throws BusinessLogicException {
        EjercicioHechoEntity old = find(s.getId());
        if (!old.getEjercicios().getId().equals(idEjercicio)) 
            throw new NoExisteException(old.getEjercicios().getId(), idEjercicio);
        
        s.setMedicionMaquinaEnt(old.getMedicionMaquinaEnt());
        s.setEjercicios(old.getEjercicios());
        return super.update(s);
    }   
    
    //----------------------------------------------------------------------------------------
    // ------------------------------------Ejercicio-----------------------------------------
    //----------------------------------------------------------------------------------------
      
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException
    {
        return conEjercicio.findAll(id);
    }
    
    public EjercicioEntity findAllEjercicio(Long id,Long idEjercicio) throws BusinessLogicException{
        return conEjercicio.find(id, idEjercicio);
    }
    
    //----------------------------------------------------------------------------------------
    // ------------------------------------MedicionMaquina------------------------------------
    //----------------------------------------------------------------------------------------

    public List<MedicionMaquinaEntity> findAllMedicionMaquina(Long id) throws BusinessLogicException {
        return connMedicion.findAll(id);
    }

    public MedicionMaquinaEntity findMedicionMaquina(Long idHecho, Long id) throws BusinessLogicException {
        return connMedicion.find(idHecho, id);
    }

    public MedicionMaquinaEntity createMedicionMaquina(Long idHecho, Long id)  throws BusinessLogicException {
           return connMedicion.create(idHecho, id);
    }

    public void removeMedicion(Long idHecho, Long id) throws BusinessLogicException {
        connMedicion.remove(idHecho, id);
    }
    
}
