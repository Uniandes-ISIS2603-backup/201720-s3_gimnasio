/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author jc.bojaca
 */
@Path("/ejercicios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioResource{
    @Inject
    private EjercicioLogic logic;
    
    @POST
    public EjercicioDTO post(EjercicioDTO nuevo) throws BusinessLogicException, Exception{
        return new EjercicioDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<EjercicioDTO> getAll() throws Exception{
        return EjercicioDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EjercicioDTO get(@PathParam("id") long id) throws Exception{
        return new EjercicioDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EjercicioDTO put(@PathParam("id")long id, EjercicioDTO nuevo) throws Exception{
        EjercicioEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EjercicioDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws Exception{
        logic.remove(id);
    }
    
    @Path("{idEjercicio: \\d+}/objetivos")
    public Class<Ejercicio_ObjetivoResource> getEjercicio_objetivoResource(@PathParam("idEjercicio") Long id) throws Exception{
        if (logic.find(id) == null)throw new WebApplicationException("El ejercicio no existe", 404);
        return Ejercicio_ObjetivoResource.class;
    }
}
