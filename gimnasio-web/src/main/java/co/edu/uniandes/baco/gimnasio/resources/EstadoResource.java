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
 private  EstadoLogic estadologic;
 
     @POST
    public EstadoDetailDTO creat(EstadoDetailDTO entity) throws BusinessLogicException
    {
        EstadoEntity estadoentity = entity.toEntity();
        EstadoEntity pcnew = estadologic.create(estadoentity);
        return new EstadoDetailDTO(pcnew);    
    }

    @GET
    @Path("{id: \\d+}")
    public EstadoDetailDTO getEstado(@PathParam("id")Long id)throws BusinessLogicException
    {
        EstadoEntity en = estadologic.find(id);
        if(en!=null)
        {
           return new EstadoDetailDTO(en);
        }
        else
        {
             throw new BusinessLogicException(); 
        }
        
    }
        @PUT
    @Path("{id: \\d+}") 
    public  EstadoDetailDTO updateEstado(@PathParam("id") Long id,EstadoDetailDTO estado)throws BusinessLogicException
    {
        EstadoEntity ent = estadologic.find(id);
        if(ent!=null)
        {
          EstadoEntity en = estado.toEntity();
          ent = estadologic.update(en);
          return new EstadoDetailDTO(en);
        }
        else
        {
            throw new BusinessLogicException();
        }
    }
     @DELETE
    @Path("{id: \\d+}") 
    public void deleteEstado(@PathParam("id")Long id)throws BusinessLogicException
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
    @Path("{EstadoId: \\d+}/Medida")
    public Class<EstadoMedidaResource> getEstadoMedida(@PathParam("EstadoId") Long EstadoId) throws BusinessLogicException {
        EstadoEntity entity = estadologic.find(EstadoId);
        if (entity == null) {
            throw new WebApplicationException("noe xiste el estado",404);
        }
        return EstadoMedidaResource.class;
    }

    
}
