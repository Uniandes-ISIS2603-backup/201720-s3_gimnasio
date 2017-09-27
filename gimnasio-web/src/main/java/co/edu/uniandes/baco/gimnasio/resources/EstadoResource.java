/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EstadoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EstadoDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
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
 * @author js.palacios437
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    private EstadoLogic estadologic;

      @POST
    public EstadoDTO post(@PathParam("idUsuario") Long idUsuario,EstadoDTO nuevo) throws BusinessLogicException{
        return new EstadoDTO(estadologic.create(idUsuario,nuevo.toEntity()));
    }
    
    @GET
    public List<EstadoDetailDTO> getAll(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return EstadoDetailDTO.listDetailDTO(estadologic.findAll(idUsuario));
    }
    
    @GET
    @Path("{id: \\d+}")
    public EstadoDetailDTO get(@PathParam("idUsuario") Long idUsuario,@PathParam("id") long id) throws BusinessLogicException {
        return new EstadoDetailDTO(estadologic.find(idUsuario,id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EstadoDTO put(@PathParam("idUsuario") Long idUsuario,@PathParam("id")long id, EstadoDTO nuevo) throws BusinessLogicException {
        EstadoEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EstadoDTO(estadologic.update(idUsuario,entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("idUsuario") Long idUsuario,@PathParam("id") long id) throws BusinessLogicException{
        estadologic.remove(idUsuario,id);
    }
    
    @Path("{idEstado: \\d+}/ejercicios")
    public Class<EjercicioResource> getEjercicioResource(@PathParam("idUsuario") Long idUsuario,@PathParam("idEstado") Long id) throws BusinessLogicException{
        if (estadologic.find(idUsuario,id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return EjercicioResource.class;
    }

    @Path("{EstadoId: \\d+}/Medida")
    public Class<EstadoMedidaResource> getEstadoMedida(@PathParam("EstadoId") Long EstadoId) throws BusinessLogicException {
        EstadoEntity entity = estadologic.find(EstadoId);
        if (entity == null) {
            throw new WebApplicationException("noe xiste el estado", 404);
        }
        return EstadoMedidaResource.class;
    }
}
