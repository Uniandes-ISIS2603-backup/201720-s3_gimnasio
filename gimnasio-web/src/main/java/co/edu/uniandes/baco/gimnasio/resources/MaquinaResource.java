/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MaquinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
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
 * @author t.kavanagh
 */
@Path("/maquinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaquinaResource{
    @Inject
    private MaquinaLogic logic;
    
    @POST
    public MaquinaDTO post(MaquinaDTO nuevo) throws BusinessLogicException{
        return new MaquinaDTO(logic.createMaquina(nuevo.toEntity()));
    }
    
    @GET
    public List<MaquinaDTO> getAll(){
        return MaquinaDTO.findall();
    }
    
    @GET
    @Path("{id: \\d+}")
    public MaquinaDTO get(@PathParam("id") long id){
        return new MaquinaDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MaquinaDTO put(@PathParam("id")long id, MaquinaDTO nuevo) throws BusinessLogicException{
        MaquinaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new MaquinaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id){
        logic.remove(id);
    }
}