/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.*;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.*;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ce.robles
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicionMaquinaResource 
{
    //----------------------------------------------------------------------------------------
    //--------------------------------------Atributos-----------------------------------------
    //---------------------------------------------------------------------------------------- 

    /**
     * Logica de la medicion.
     */
    private MedicionMaquinaLogic logic;

    //----------------------------------------------------------------------------------------
    //--------------------------------------Constructores-------------------------------------
    //---------------------------------------------------------------------------------------- 

    public MedicionMaquinaResource() {
        //constructor para la parte web
    }

    @Inject public MedicionMaquinaResource(MedicionMaquinaLogic logic) {
        this.logic = logic;
    }
    
    //----------------------------------------------------------------------------------------
    //--------------------------------------------CRUD----------------------------------------
    //---------------------------------------------------------------------------------------- 
    
    /**
     * Añadir una nueva medicionMaquina a un ejercicioHecho.
     * @param idEjercicioHecho - Id del ejercicioHecho al que pertenece la medicionMaquina.
     * @param id - Id de la medicionMaquina.
     * @param nuevo - MedicionMaquina a agregar.
     * @return MedicionMaquina creada.
     * @throws BusinessLogicException - Culquier error que incumpla las reglas de negocio.
     */
    @POST
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO post(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho, @PathParam(MEDICIONMAQUINAID) Long id ,MedicionMaquinaDTO nuevo) throws BusinessLogicException{
        return new MedicionMaquinaDTO(logic.create(idEjercicioHecho,nuevo.toEntity(),id));
    }
    
    /**
     * Obtener todas las mediciones de la maquina pertencientes al ejercicioHecho con el id ingresado por parametro.
     * @param idEjercicioHecho - Id al que pertenecen las medicionesMaquina.
     * @return Lista con todas las medicionesMaquina pertenecientes al ejercicioHecho.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @GET
    public List<MedicionMaquinaDetailDTO> getAll(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho)throws BusinessLogicException
    {
        return MedicionMaquinaDetailDTO.listDetailDTO(logic.findAll(idEjercicioHecho));
    }
    
    /**
     * Obtener una medicionMaquina con id ingresado perteneciente al ejercicio con id ingresado por parametro.
     * @param idEjercicioHecho - Id del ejercicioHecho al que pertenece la medicionMaquina
     * @param id - Id de la medicionMaquina a obtener.
     * @return MedicionMaquina buscada con el id ingresado por parametro.
     * @throws BusinessLogicException  - Cualquier error que incumpla con las reglas de negocio.
     */
    @GET
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO get(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho,@PathParam(MEDICIONMAQUINAID) long id) throws BusinessLogicException{
        return new MedicionMaquinaDTO(logic.find(idEjercicioHecho,id));
    }
    
    /**
     * Actualizar la medicion con id ingresado perteneciente al ejercicioHecho con id ingresado por parametro.
     * @param idEjercicioHecho - Id del ejercicioHecho al que pertenece la medicionMaquina a actualizar.
     * @param id - Id de la medicionMaquina a actualizar.
     * @param nuevo - MedicionMaquina actualizada.
     * @return MedicionMaquina actualizada.
     * @throws BusinessLogicException  - Cualquier error que incumpla con las reglas de negocio.
     */
    @PUT
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO put(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho ,@PathParam(MEDICIONMAQUINAID)long id, MedicionMaquinaDTO nuevo) throws BusinessLogicException{
        MedicionMaquinaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedicionMaquinaDTO(logic.update(idEjercicioHecho,entity));
    }
    
    /**
     * Eliminar una medicionMaquina.
     * @param idEjercicioHecho - Id del ejercicioHecho al que pertenece la medicionMaquina.
     * @param id - Id de la medicionMaquina a eliminar.
     * @throws BusinessLogicException  - Cualquier error que incumpla con las reglas de negocio.
     */
    @DELETE
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public void delete(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho ,@PathParam(MEDICIONMAQUINAID)long id) throws BusinessLogicException{
        logic.remove(idEjercicioHecho,id);
    } 
}
