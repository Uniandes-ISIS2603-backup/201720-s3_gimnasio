/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ParteDelCuerpoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioLogic;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.bojaca
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioParteDelCuerpoResource {
    @Inject
    private EjercicioLogic logic;
    
     @GET
    public List<ParteDelCuerpoDTO> findAllObejtivos(@PathParam("idEjercicio") Long id) throws BusinessLogicException {
        return ParteDelCuerpoDTO.listDTO(logic.findAllParteDelCuerpos(id));
    }

    @GET
    @Path("{id: \\d+}")
    public ParteDelCuerpoDTO findParteDelCuerpo(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws BusinessLogicException {
        return new ParteDelCuerpoDTO(logic.findParteDelCuerpo(idEjercicio, id));
    }

    @POST
    @Path("{id: \\d+}")
    public ParteDelCuerpoDTO createParteDelCuerpo(@PathParam("idEjercicio") Long idEjericio,@PathParam("id") Long id) throws BusinessLogicException {
        return new ParteDelCuerpoDTO(logic.createParteDelCuerpo(idEjericio, id));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void removeParteDelCuerpo(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws BusinessLogicException {
        logic.removeObejtivo(idEjercicio, id);
    }
}
