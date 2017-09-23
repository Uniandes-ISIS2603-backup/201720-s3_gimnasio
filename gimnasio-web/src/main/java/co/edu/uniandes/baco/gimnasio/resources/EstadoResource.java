/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;


import co.edu.uniandes.baco.gimnasio.dtos.EstadoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ParteDelCuerpoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;

import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
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

/**
 *
 * @author js.palacios437
 */

@Path("Estado")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstadoResource {
    
 @Inject
 private  EstadoLogic estadologic;
 
     @POST
    public EstadoDTO creat(EstadoDTO entity) throws Exception
    {
        EstadoEntity estadoentity = entity.toEntity();
        EstadoEntity pcnew = estadologic.create(estadoentity);
        return new EstadoDTO(pcnew);    
    }

    @GET
    @Path("{id: \\d+}")
    public EstadoDTO getEstado(@PathParam("id")Long id)throws Exception
    {
        EstadoEntity en = estadologic.find(id);
        if(en!=null)
        {
           return new EstadoDTO(en);
        }
        else
        {
             throw new BusinessLogicException(); 
        }
        
    }
        @PUT
    @Path("{id: \\d+}") 
    public  EstadoDTO updateEstado(@PathParam("id") Long id,EstadoDTO estado)throws Exception
    {
        EstadoEntity ent = estadologic.find(id);
        if(ent!=null)
        {
          EstadoEntity en = estado.toEntity();
          ent = estadologic.update(en);
          return new EstadoDTO(en);
        }
        else
        {
            throw new BusinessLogicException();
        }
    }
        @DELETE
    @Path("{id: \\d+}") 
    public void deleteEstado(@PathParam("id")Long id)throws Exception
    {
              EstadoEntity ent = estadologic.find(id);
        if(ent!=null)
        {
          
          estadologic.remove(id);
          
        }
        else
        {
            throw new BusinessLogicException();
        }  
    }

}
