/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioLogic;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
public class EjercicioObjetivoResource {
    private EjercicioLogic logic;

    public EjercicioObjetivoResource() {
         //constructor para la parte web
    }

    @Inject public EjercicioObjetivoResource(EjercicioLogic logic) {
        this.logic = logic;
    }
    
    @GET
    public List<ObjetivoDetailDTO> findAllObejtivos(@PathParam(EJERCICIOID) Long id) throws BusinessLogicException {
        return ObjetivoDetailDTO.listDetailDTO(logic.findAllObjetivo(id));
    }

    @GET
    @Path("{"+OBJETIVOID+": \\d+}")
    public ObjetivoDetailDTO findObjetivo(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(OBJETIVOID) Long id) throws BusinessLogicException {
        return new ObjetivoDetailDTO(logic.findObjetivo(idEjercicio, id));
    }

    @POST
    @Path("{"+OBJETIVOID+": \\d+}")
    public ObjetivoDTO createObjetivo(@PathParam(EJERCICIOID) Long idEjericio,@PathParam(OBJETIVOID) Long id) throws BusinessLogicException {
        return new ObjetivoDTO(logic.createObjetivo(idEjericio, id));
    }

    @DELETE
    @Path("{"+OBJETIVOID+": \\d+}")
    public void removeObjetivo(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(OBJETIVOID) Long id) throws BusinessLogicException {
        logic.removeObejtivo(idEjercicio, id);
    }
}
