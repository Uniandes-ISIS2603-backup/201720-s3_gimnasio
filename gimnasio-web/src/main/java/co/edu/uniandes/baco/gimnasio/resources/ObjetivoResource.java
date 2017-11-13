/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.ObjetivoLogic;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
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
 * @author jc.bojaca
 */
@Path(URLS.OBJETIVO)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjetivoResource{
    private ObjetivoLogic logic;

    public ObjetivoResource() {
        //constructor para la parte web
    }

    @Inject public ObjetivoResource(ObjetivoLogic logic) {
        this.logic = logic;
    }
    
    @POST
    public ObjetivoDTO post(ObjetivoDTO nuevo) throws BusinessLogicException{
        return new ObjetivoDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<ObjetivoDetailDTO> getAll() throws BusinessLogicException{
        return ObjetivoDetailDTO.listDetailDTO(logic.findAll((o1, o2) -> o1.getTipo().compareTo(o2.getTipo())));
    }
    
    @GET
    @Path("{"+OBJETIVOID+": \\d+}")
    public ObjetivoDetailDTO get(@PathParam(OBJETIVOID) long id) throws BusinessLogicException{
        return new ObjetivoDetailDTO(logic.find(id));
    }
    
    @PUT
    @Path("{"+OBJETIVOID+": \\d+}")
    public ObjetivoDTO put(@PathParam(OBJETIVOID)long id, ObjetivoDTO nuevo) throws BusinessLogicException{
        ObjetivoEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new ObjetivoDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{"+OBJETIVOID+": \\d+}")
    public void delete(@PathParam(OBJETIVOID) long id) throws BusinessLogicException{
        logic.remove(id);
    }
    
    @GET
    @Path("{"+OBJETIVOID+": \\d+}/"+USUARIO)
    public List<UsuarioDetailDTO> findUsuarios(@PathParam(OBJETIVOID) long id) throws BusinessLogicException{
        return UsuarioDetailDTO.listDetailDTO(logic.findAllUsuario(id));
    }
    
    @GET
    @Path("{"+OBJETIVOID+": \\d+}/"+EJERCICIO)
    public List<EjercicioDetailDTO> findEjercicios(@PathParam(OBJETIVOID) long id) throws BusinessLogicException{
        return EjercicioDetailDTO.listDetailDTO(logic.findAllEjercicio(id));
    }
    
    @Path("{"+OBJETIVOID+": \\d+}/"+ATRIBUTODECALIDAD)
    public Class<AtributoDeCalidadResource> getAtributoDeCalidad(@PathParam(OBJETIVOID) Long id) throws BusinessLogicException{
        logic.find(id);
        return AtributoDeCalidadResource.class;
    }
}
