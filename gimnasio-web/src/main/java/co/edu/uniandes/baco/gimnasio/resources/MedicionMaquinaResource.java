/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedicionMaquinaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedicionMaquinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
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
 * @author ce.robles
 */
//TODO Arreglar el path.
@Path("/aquiVaUnPath2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicionMaquinaResource 
{
    @Inject
    private MedicionMaquinaLogic logic;
    
    @POST
    public MedicionMaquinaDTO post(MedicionMaquinaDTO nuevo) throws BusinessLogicException, Exception{
        return new MedicionMaquinaDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<MedicionMaquinaDTO> getAll() throws Exception
    {
        return MedicionMaquinaDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public MedicionMaquinaDTO get(@PathParam("id") long id) throws Exception{
        return new MedicionMaquinaDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MedicionMaquinaDTO put(@PathParam("id")long id, MedicionMaquinaDTO nuevo) throws BusinessLogicException, Exception{
        MedicionMaquinaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedicionMaquinaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws Exception{
        logic.remove(id);
    }
}
