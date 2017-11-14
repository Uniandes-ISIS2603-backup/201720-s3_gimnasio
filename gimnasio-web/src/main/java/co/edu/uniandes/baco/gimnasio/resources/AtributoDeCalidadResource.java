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
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
    private AtributoDeCalidadLogic logic;

    public AtributoDeCalidadResource() {
        //costructor para la parte web
    }

    @Inject public AtributoDeCalidadResource(AtributoDeCalidadLogic logic) {
        this.logic = logic;
    }

    @POST
    @Path("{"+ATRIBUTODECALIDADID+": \\d+}")
    public AtributoDeCalidadDTO post(@PathParam(OBJETIVOID) Long idObjetivo, @PathParam(ATRIBUTODECALIDADID) Long id ,AtributoDeCalidadDTO nuevo) throws BusinessLogicException{
        return new AtributoDeCalidadDTO(logic.create(idObjetivo,nuevo.toEntity(),id));
    }
    
    @GET
    public List<AtributoDeCalidadDetailDTO> getAll(@PathParam(OBJETIVOID) Long idObjetivo) throws BusinessLogicException {
        List<AtributoDeCalidadDetailDTO> ans= AtributoDeCalidadDetailDTO.listDTO(logic.findAll(idObjetivo));
        ans.sort((o1, o2) -> o1.getDescripcion().compareTo(o2.getDescripcion()));
        return ans;
    }
    
    @GET
    @Path("{"+ATRIBUTODECALIDADID+": \\d+}")
    public AtributoDeCalidadDetailDTO get(@PathParam(OBJETIVOID) Long idObjetivo,@PathParam(ATRIBUTODECALIDADID) long id) throws BusinessLogicException {
        return new AtributoDeCalidadDetailDTO(logic.find(idObjetivo,id));
    }
    
    @PUT
   @Path("{"+ATRIBUTODECALIDADID+": \\d+}")
    public AtributoDeCalidadDTO put(@PathParam(OBJETIVOID) Long idObjetivo,@PathParam(ATRIBUTODECALIDADID)long id, AtributoDeCalidadDTO nuevo) throws BusinessLogicException {
        AtributoDeCalidadEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new AtributoDeCalidadDTO(logic.update(idObjetivo,entity));
    }
    
    @DELETE
    @Path("{"+ATRIBUTODECALIDADID+": \\d+}")
    public void delete(@PathParam(OBJETIVOID) Long idObjetivo,@PathParam(ATRIBUTODECALIDADID) long id) throws BusinessLogicException{
        logic.remove(idObjetivo,id);
    }
}
