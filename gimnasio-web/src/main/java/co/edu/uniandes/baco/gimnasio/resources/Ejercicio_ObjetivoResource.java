/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioLogic;
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
 * @author jc.bojaca
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Ejercicio_ObjetivoResource {
    @Inject
    private EjercicioLogic logic;
    
    @GET
    public List<ObjetivoDTO> findAllObejtivos(@PathParam("idEjercicio") Long id) throws Exception {
        return ObjetivoDTO.listDTO(logic.findAllObjetivos(id));
    }

    @GET
    @Path("{id: \\d+}")
    public ObjetivoDTO findObjetivo(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws Exception {
        return new ObjetivoDTO(logic.findObjetivo(idEjercicio, id));
    }

    @POST
    @Path("{id: \\d+}")
    public ObjetivoDTO addBooks(@PathParam("idEjercicio") Long idEjericio,@PathParam("id") Long id) throws Exception {
        return new ObjetivoDTO(logic.createObjetivo(idEjericio, id));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void removeBooks(@PathParam("idEjercicio") Long idEjercicio, @PathParam("id") Long id) throws Exception {
        logic.removeObejtivo(idEjercicio, id);
    }
    
}
