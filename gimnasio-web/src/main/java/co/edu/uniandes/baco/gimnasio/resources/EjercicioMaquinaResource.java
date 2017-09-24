/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDetailDTO;
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
public class EjercicioMaquinaResource {
    @Inject
    private EjercicioLogic logic;
    
    @GET
    public List<MaquinaDetailDTO> findAllObejtivos(@PathParam("idEjercicio") Long id) throws BusinessLogicException {
          return MaquinaDetailDTO.listDetailDTO(logic.findAllMaquinas(id));
    }

    @GET
    @Path("{id: \\d+}")
    public MaquinaDTO findMaquina(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws BusinessLogicException {
        return new MaquinaDTO(logic.findMaquina(idEjercicio, id));
    }

    @POST
    @Path("{id: \\d+}")
    public MaquinaDTO createMaquina(@PathParam("idEjercicio") Long idEjericio,@PathParam("id") Long id) throws BusinessLogicException {
        return new MaquinaDTO(logic.createMaquina(idEjericio, id));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void removeMaquina(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws BusinessLogicException {
        logic.removeObejtivo(idEjercicio, id);
    }
}
