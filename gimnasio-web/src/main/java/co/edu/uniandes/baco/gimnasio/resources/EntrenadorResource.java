/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EntrenadorLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author m.sicard10
 */
@Path("entrenadores")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EntrenadorResource {
    @Inject
    EntrenadorLogic entrenadorLogic;
    
    
    @POST
    public EntrenadorDTO create(EntrenadorDTO p) throws Exception
    {
        EntrenadorEntity pcentity = p.toEntity();
        EntrenadorEntity pnew = entrenadorLogic.create(pcentity);
        return new EntrenadorDTO(pnew);
    }
    
    @GET
    @Path("{id: \\d+}")
    public EntrenadorDTO getEntrenador(@PathParam("id")Long id)throws Exception
    {
        EntrenadorEntity en = entrenadorLogic.find(id);
        if(en!=null)
        {
           return new EntrenadorDTO(en);
        }
        else
        {
             throw new BusinessLogicException("el entrenador no existe"); 
        }
    }
    
    @GET
    public List<EntrenadorDetailDTO> getEntrenadores() throws Exception {
        return listEntity2DetailDTO(entrenadorLogic.findAll());
    }
    
    @PUT
    @Path("{id: \\d+}") 
    public EntrenadorDetailDTO update(@PathParam("id")Long id, EntrenadorDetailDTO e) throws Exception
    {
        EntrenadorEntity ent = entrenadorLogic.find(id);
          if(ent!= null) {
          EntrenadorEntity en = e.toEntity();
          ent = entrenadorLogic.update(en);
          return new EntrenadorDetailDTO(ent);  
          }else {
                   throw new BusinessLogicException("error");
               }
    }
    
    private List<EntrenadorDetailDTO> listEntity2DetailDTO(List<EntrenadorEntity> entityList) {
        List<EntrenadorDetailDTO> list = new ArrayList<>();
        for (EntrenadorEntity entity : entityList) {
            list.add(new EntrenadorDetailDTO(entity));
        }
        return list;
    }
    
     @DELETE
    @Path("{id: \\d+}") 
    public void deleteEntreador(@PathParam("id")Long id)throws Exception
    {
              EntrenadorEntity ent = entrenadorLogic.find(id);
        if(ent!=null)
        {
          
          entrenadorLogic.remove(id);
          
        }
        else
        {
            throw new BusinessLogicException("no se pudo eliminaar ya que no existe");
        }  
    }
    
    @Path("{EntrenadorId: \\d+}/Usuarios")
    public Class <EntrenadorUsuarioResource> getEntrenadorUsuarioResource (@PathParam("EntrenadorId") Long EntID) throws Exception{
        EntrenadorEntity e = entrenadorLogic.find(EntID);
        if (e == null)
        {
                        throw new WebApplicationException("El recurso /entrenadores/" + EntID + "/usuarios no existe.", 404);

        }
        return EntrenadorUsuarioResource.class;
    }
    
    
}
