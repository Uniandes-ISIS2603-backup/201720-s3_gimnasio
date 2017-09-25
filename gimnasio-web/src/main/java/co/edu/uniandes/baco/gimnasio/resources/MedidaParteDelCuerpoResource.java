/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ParteDelCuerpoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.palacios437
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedidaParteDelCuerpoResource {
   
  @Inject
  MedidaLogic logic;
  
  @GET
  public ParteDelCuerpoDTO getPc(@PathParam("Medidaid") Long Medidaid) throws BusinessLogicException
  {
      return new ParteDelCuerpoDTO(logic.find(Medidaid).getParte());
  }
 
  @POST
  @Path("{idpc: \\d+}")
  public ParteDelCuerpoDTO addPC(@PathParam("Medidaid") Long Medidaid,@PathParam("idpc")Long idpc) throws BusinessLogicException
  {
     
      return new ParteDelCuerpoDTO(logic.addPartedelcuerpo(idpc, Medidaid));
      
  }
  
}
