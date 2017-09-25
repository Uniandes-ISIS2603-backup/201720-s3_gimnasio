/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.RutinaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.RutinaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.RutinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.bojaca
 */
@Path("rutinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RutinaResource {
    @Inject
    private RutinaLogic logic;
    
    @POST
    public RutinaDTO post(RutinaDTO nuevo) throws BusinessLogicException{
        return new RutinaDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<RutinaDetailDTO> getAll() throws BusinessLogicException {
        return RutinaDetailDTO.listDetailDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public RutinaDetailDTO get(@PathParam("id") long id) throws BusinessLogicException {
        return new RutinaDetailDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public RutinaDTO put(@PathParam("id")long id, RutinaDTO nuevo) throws BusinessLogicException {
        RutinaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new RutinaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(id);
    }
    
    @Path("{idRutina: \\d+}/ejercicios")
    public Class<EjercicioResource> getEjercicioResource(@PathParam("idRutina") Long id) throws BusinessLogicException{
        if (logic.find(id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return EjercicioResource.class;
    }
}
