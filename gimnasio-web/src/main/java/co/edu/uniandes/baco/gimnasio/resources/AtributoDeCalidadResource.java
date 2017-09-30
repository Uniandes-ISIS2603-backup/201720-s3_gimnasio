/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.AtributoDeCalidadDTO;
import co.edu.uniandes.baco.gimnasio.dtos.AtributoDeCalidadDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.AtributoDeCalidadLogic;
import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author camilo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtributoDeCalidadResource {
     @Inject
    private AtributoDeCalidadLogic logic;
    
    @POST
    @Path("{id: \\d+}")
    public AtributoDeCalidadDTO post(@PathParam("idObjetivo") Long idObjetivo, @PathParam("id") Long id ,AtributoDeCalidadDTO nuevo) throws BusinessLogicException{
        return new AtributoDeCalidadDTO(logic.create(idObjetivo,nuevo.toEntity(),id));
    }
    
    @GET
    public List<AtributoDeCalidadDetailDTO> getAll(@PathParam("idObjetivo") Long idObjetivo) throws BusinessLogicException {
        return AtributoDeCalidadDetailDTO.listDTO(logic.findAll(idObjetivo));
    }
    
    @GET
    @Path("{id: \\d+}")
    public AtributoDeCalidadDetailDTO get(@PathParam("idObjetivo") Long idObjetivo,@PathParam("id") long id) throws BusinessLogicException {
        return new AtributoDeCalidadDetailDTO(logic.find(idObjetivo,id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public AtributoDeCalidadDTO put(@PathParam("idObjetivo") Long idObjetivo,@PathParam("id")long id, AtributoDeCalidadDTO nuevo) throws BusinessLogicException {
        AtributoDeCalidadEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new AtributoDeCalidadDTO(logic.update(idObjetivo,entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("idObjetivo") Long idObjetivo,@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(idObjetivo,id);
    }
}
