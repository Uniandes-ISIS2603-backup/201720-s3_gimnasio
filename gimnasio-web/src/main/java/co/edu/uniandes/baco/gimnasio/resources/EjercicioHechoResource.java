/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioHechoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioHechoLogic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
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
@Path("/aquiVaUnPath")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioHechoResource 
{
    @Inject
    private EjercicioHechoLogic logic;
    
    @POST
    public EjercicioHechoDTO post(EjercicioHechoDTO nuevo) throws BusinessLogicException, Exception{
        return new EjercicioHechoDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<EjercicioHechoDTO> getAll() throws Exception
    {
        return EjercicioHechoDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EjercicioHechoDTO get(@PathParam("id") long id) throws Exception{
        return new EjercicioHechoDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EjercicioHechoDTO put(@PathParam("id")long id, EjercicioHechoDTO nuevo) throws BusinessLogicException, Exception{
        EjercicioHechoEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new EjercicioHechoDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws Exception{
        logic.remove(id);
    }
}
