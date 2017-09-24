/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.ObjetivoLogic;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
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
 * @author jc.bojaca
 */
@Path("objetivos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjetivoResource{
    @Inject
    private ObjetivoLogic logic;
    
    @POST
    public ObjetivoDTO post(ObjetivoDTO nuevo) throws BusinessLogicException{
        return new ObjetivoDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<ObjetivoDTO> getAll() throws BusinessLogicException{
        return ObjetivoDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ObjetivoDTO get(@PathParam("id") long id) throws BusinessLogicException{
        return new ObjetivoDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ObjetivoDTO put(@PathParam("id")long id, ObjetivoDTO nuevo) throws BusinessLogicException{
        ObjetivoEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new ObjetivoDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(id);
    }
}
