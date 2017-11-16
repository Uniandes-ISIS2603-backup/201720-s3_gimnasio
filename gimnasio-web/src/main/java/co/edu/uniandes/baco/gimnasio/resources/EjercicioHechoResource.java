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
//@Path(URLS.EJERCICIOHECHO)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioHechoResource 
{
    //----------------------------------------------------------------------------------------
    // -----------------------------------Atributos------------------------------------------
    //----------------------------------------------------------------------------------------
    
    private EjercicioHechoLogic logic;

    //----------------------------------------------------------------------------------------
    // -----------------------------------Constructores---------------------------------------
    //----------------------------------------------------------------------------------------
    
    public EjercicioHechoResource() {
        //constructor para la parte web
    }

    @Inject public EjercicioHechoResource(EjercicioHechoLogic logic) {
        this.logic = logic;
    }  
    
    //----------------------------------------------------------------------------------------
    // -----------------------------------------CRUD------------------------------------------
    //----------------------------------------------------------------------------------------
    
    /**
     * Asociar y crear un ejercicioHecho a un ejercicio.
     * @param idEjercicio - Id del ejercicio al que pertenece el ejercicioHecho.
     * @param nuevo - Nuevo EjercicioHecho que se agregara en el ejercicio.
     * @return EjercicioHecho agregado.
     * @throws BusinessLogicException -  Si el usuario no existe.
     */
    @POST
    public EjercicioHechoDTO post(@PathParam(EJERCICIOID) Long idEjercicio, EjercicioHechoDTO nuevo) throws BusinessLogicException{
        return new EjercicioHechoDTO(logic.create(idEjercicio, nuevo.toEntity()));
    }
    
    /**
     * Obtener todos los ejerciciosHechos de un ejercicio.
     * @param idEjercicio - Id del ejercicio al que pertenece el ejercicioHecho.
     * @return Lista de ejerciciosHechos.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @GET
    public List<EjercicioHechoDetailDTO> getAll(@PathParam(EJERCICIOID) Long idEjercicio) throws BusinessLogicException {
        return EjercicioHechoDetailDTO.listDetailDTO(logic.findAll(idEjercicio));
    }
    
    /**
     * Obtener un ejercicioHecho especifico.
     * @param idEjercicio - Id del ejercicio al que pertenece el ejercicioHecho.
     * @param id - Id del ejercicioHecho a obtener.
     * @return EjercicioHecho con el id ingresado por parametro.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @GET
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDTO get(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(EJERCICIOHECHOID) long id) throws BusinessLogicException{
        return new EjercicioHechoDTO(logic.find(idEjercicio, id));
    }
    
    /**
     * Actualizar un ejercicioHecho.
     * @param idEjercicio - Id del ejercicio al que pertenece el ejercicioHecho.
     * @param id - Id del ejercicioHecho a actualizar.
     * @param nuevo EjercicioHecho a actualizar.
     * @return EjercicioHecho actualizado.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @PUT
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDTO put(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(EJERCICIOHECHOID)long id, EjercicioHechoDTO nuevo) throws BusinessLogicException {
        EjercicioHechoEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new EjercicioHechoDTO(logic.update(idEjercicio, entity));
    }
    
    /**
     * Eliminar un ejercicioHecho.
     * @param idEjercicio - Id del ejercicio al que pertenece el ejercicioHecho.
     * @param id - Id del ejercicioHecho a eliminar.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @DELETE
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public void delete(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(EJERCICIOHECHOID) long id) throws BusinessLogicException{
        logic.remove(idEjercicio, id);
    }
    
    /**
     * Obtener los servicios del ejercicioHecho.
     * @param id - Id del ejercicioHecho al que pertenece el servicio.
     * @return La clase del servicio.
     * @throws BusinessLogicException - Cualquier error que incumpla las reglas de negocio.
     */
    @Path("{"+EJERCICIOHECHOID+": \\d+}/"+MEDICIONMAQUINA)
    public Class<MedicionMaquinaResource> getMedicionMaquina( @PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException
    {
        logic.find(id);
        return MedicionMaquinaResource.class;
    }
}
