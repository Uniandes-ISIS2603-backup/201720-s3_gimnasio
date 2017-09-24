    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ParteDelCuerpoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
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
@Path("Medida")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class MedidaResource {
    
   @Inject
   private MedidaLogic medidalogic;
   
    @POST
    public MedidaDTO creat(MedidaDTO medidaDto) throws Exception
    {
        MedidaEntity pcentity = medidaDto.toEntity();
        MedidaEntity pcnew = medidalogic.createMedida(pcentity);
        return new MedidaDTO(pcnew);
        
    }
    
    @GET
    @Path("{id: \\d+}")
    public MedidaDTO getMedida(@PathParam("id")Long id)throws Exception
    {
        MedidaEntity en = medidalogic.getMedida(id);
        if(en!=null)
        {
           return new MedidaDTO(en);
        }
        else
        {
             throw new BusinessLogicException(); 
        }
    }
        @PUT
    @Path("{id: \\d+}") 
    public  MedidaDTO updateMedida(@PathParam("id") Long id,MedidaDTO medida)throws Exception
    {
        MedidaEntity ent = medidalogic.getMedida(id);
        if(ent!=null)
        {
          MedidaEntity en = medida.toEntity();
          ent = medidalogic.updateMedida(en);
          return new MedidaDTO(en);
        }
        else
        {
            throw new BusinessLogicException();
        }
       }
        @DELETE
    @Path("{id: \\d+}") 
    public void deleteMedida(@PathParam("id")Long id)throws Exception
    {
      MedidaEntity ent = medidalogic.getMedida(id);
        if(ent!=null)
        {
          
          medidalogic.deleteMedida(id);
          
        }
        else
        {
            throw new BusinessLogicException();
        }  
    }

    
}
