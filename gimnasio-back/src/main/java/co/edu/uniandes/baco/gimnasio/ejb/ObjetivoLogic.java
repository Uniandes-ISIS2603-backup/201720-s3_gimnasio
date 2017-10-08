/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity>{
     /**
     * inject de la clase logic usuario
     */
    @Inject
    private UsuarioLogic usuarioLogic;
    
    /**
     * inject de la clase logica de ejercicios
     */
    @Inject
    private EjercicioLogic ejercicioLogic;
    
    /**
     * metodo para crear un objetivo
     * @param entity el objetivo a crear
     * @return el objetico creado
     * @throws BusinessLogicException si ya existe un objetivo de este estilo 
     */
    @Override
    public ObjetivoEntity create(ObjetivoEntity entity)throws BusinessLogicException{
        if(null!=((ObjetivoPersistence)persistence).findByTipo(entity.getTipo()))
            throw new BusinessLogicException("ya existe un objetivo con ese tipo");
        return super.create(entity); 
    }
    
    public List<UsuarioEntity> findAllUsuarios(Long id) throws BusinessLogicException{
        return find(id).getUsuarios();
    }
    
    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    /**
     * Metodo apra encontrar todo lso objetivos 
     * @param id del ejercicio
     * @return lista con los objetivos
     * @throws BusinessLogicException si no eciste el jercicio
     */
    public List<ObjetivoEntity> findAllObjetivosEjercicio(Long id) throws BusinessLogicException{
        return ejercicioLogic.find(id).getObjetivosEjercicio();        
    }
    /**
     * metodo apra encontrar un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @return el objetivo
     * @throws BusinessLogicException si no existe el objetivo
     */
    public ObjetivoEntity findObjetivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=ejercicioLogic.find(idEjercicio).getObjetivosEjercicio();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo para crear un objetivo en el ejercicios
     * @param idEjercicio id del ejercicio 
     * @param id del objetivo a añadir
     * @return retorna el objetivo agregado al ejercicio
     * @throws BusinessLogicException si el objetivo no existe o el ejercicio no existe
     */
    public ObjetivoEntity createObjetivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        ejercicioLogic.find(idEjercicio).getObjetivosEjercicio().add(aux);
        return aux;
    }
    /**
     * metodo que remueve un objetivo de un ejercicio
     * @param idEjercicio id del ejercicio
     * @param id del objetivo
     * @throws BusinessLogicException si el ejercicio no existe, o el objetivo no existe 
     */
    public void removeObejtivoEjercicio(Long idEjercicio, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=ejercicioLogic.find(idEjercicio).getObjetivosEjercicio();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
    //-----------------------------------
    // USUSARIO
    //-----------------------------------
    /**
     * metodo apra encontrar todos los usuarios
     * @param id del usuario a buscar
     * @return los objetivos de ese usuario
     * @throws BusinessLogicException si el usuario no existe
     */
    public List<ObjetivoEntity> findAllObjetivosUsuario(Long id) throws BusinessLogicException{
        return usuarioLogic.find(id).getObjetivos();        
    }
    /**
     * metodo apra encontrar un objetivo en un usuario
     * @param idUsuario id del ejercicio
     * @param id del objetivo
     * @return el objetivo deseado
     * @throws BusinessLogicException si el usuario o el objetivo no existen 
     */
    public ObjetivoEntity findObjetivoUsuario(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity aux = new ObjetivoEntity();
        aux.setId(id);
        List<ObjetivoEntity> list=usuarioLogic.find(idUsuario).getObjetivos();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    /**
     * metodo que asocia un objetico a un usuario 
     * @param idUsuario del usuario
     * @param id id del objetivo
     * @return el objetivo creado
     * @throws BusinessLogicException si el usuario o objetivo no existe 
     */
    public ObjetivoEntity createObjetivoUsuario(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity objetivoEntity = find(id);
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(idUsuario);
        objetivoEntity.getUsuarios().add(usuarioEntity);
        return objetivoEntity;
    }
    /**
     *  metodo para eliminar un objetivo a un usuario
     * @param idUsuario del Usuario
     * @param id del objetivo
     * @throws BusinessLogicException si el usuario o el objetivo no existen 
     */
    public void removeObejtivoUsuario(Long idUsuario, Long id) throws BusinessLogicException{
        ObjetivoEntity entity = find(id);
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(idUsuario);
        entity.getUsuarios().remove(usuarioEntity);
    }
}
