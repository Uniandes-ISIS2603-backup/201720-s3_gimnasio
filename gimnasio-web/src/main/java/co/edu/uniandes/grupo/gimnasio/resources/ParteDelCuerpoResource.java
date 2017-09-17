/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.grupo.gimnasio.resources;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.grupo.gimnasio.dtos.ParteDelCuerpoDTO;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author js.palacios437
 */
@Path("ParteDelCuerpo")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ParteDelCuerpoResource {
    
    @Inject
    ParteDelCuerpoLogic cplogic;
    
    @POST
    public ParteDelCuerpoDTO creat(ParteDelCuerpoDTO Pcuer) throws BusinessLogicException
    {
        PartesDelCuerpoEntity pcentity = Pcuer.toEntity();
        PartesDelCuerpoEntity pcnew = cplogic.createParteDelCuerpo(pcentity);
        return new ParteDelCuerpoDTO(pcnew);
        
    }
    
}
