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
    /**
     * metodo inject de la logica de estado
     */
    @Inject
    private EstadoLogic logic;
    /**
     * metodo que crea un nuevo estado
     * @param idUsuario id del usuario
     * @param nuevo el estado
     * @return el estado creado
     * @throws BusinessLogicException 
     */
    @POST
    public EstadoDTO post(@PathParam("idUsuario") Long idUsuario,EstadoDTO nuevo) throws BusinessLogicException{
        return new EstadoDTO(logic.create(idUsuario,nuevo.toEntity()));
    }
    /**
     * metodo que obtiene todo lso estados de un usuario
     * @param idUsuario id del usuario
     * @return lista con todos los usuarios
     * @throws BusinessLogicException el usuario no existe
     */
    @GET
    public List<EstadoDetailDTO> getAll(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return EstadoDetailDTO.listDetailDTO(logic.findAll(idUsuario));
    }
    /**
     * metodo que obtine un estado ene specifico
     * @param idUsuario id del usuario
     * @param id del estado
     * @return el estado
     * @throws BusinessLogicException el usuario o el estado no existe 
     */
    @GET
    @Path("{id: \\d+}")
    public EstadoDetailDTO get(@PathParam("idUsuario") Long idUsuario,@PathParam("id") long id) throws BusinessLogicException {
        return new EstadoDetailDTO(logic.find(idUsuario,id));
    }
    /**
     * metodoq eu actuliza une stado
     * @param idUsuario del usuario
     * @param id del estado
     * @param nuevo nuevo estado
     * @return el estado actulizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public EstadoDTO put(@PathParam("idUsuario") Long idUsuario,@PathParam("id")long id, EstadoDTO nuevo) throws BusinessLogicException {
        EstadoEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EstadoDTO(logic.update(idUsuario,entity));
    }
    /**
     * metodo que borra un estado
     * @param idUsuario del usaurio
     * @param id del estado
     * @throws BusinessLogicException si el estado o usuario noe xiste 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("idUsuario") Long idUsuario,@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(idUsuario,id);
    }
    /**
     * metodo que retorna los servicio de medidas
     * @param idUsuario del usuario
     * @param id del estado
     * @return la clase de servicio
     * @throws BusinessLogicException si el estado o los servicioo no sirven 
     */
    @Path("{idEstado: \\d+}/medidas")
    public Class<MedidaResource> getMedidasResource(@PathParam("idUsuario") Long idUsuario,@PathParam("idEstado") Long id) throws BusinessLogicException{
        if (logic.find(idUsuario,id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return MedidaResource.class;
    }
}
