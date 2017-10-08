/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.ObjetivoDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.ObjetivoLogic;
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
 * @author camilo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioObjetivoResource {
    /**
     * inject de la logica de objetivos
     */
    @Inject
    private ObjetivoLogic logic;
    /**
     * metodo que debuelve todo los objeticos
     * @param id del usuario
     * @return lista de objetivos del usuario
     * @throws BusinessLogicException si el usuario noe xiste
     */
    @GET
    public List<ObjetivoDetailDTO> findAllObejtivos(@PathParam("idUsuario") Long id) throws BusinessLogicException {
        return ObjetivoDetailDTO.listDetailDTO(logic.findAllObjetivosUsuario(id));
    }
    /**
     * metodo qque encuentra un objetivo 
     * @param idUsuario id del usuario
     * @param id del objetivo
     * @return el objetivo requerido
     * @throws BusinessLogicException si el usuario o el objetivo noe xisten 
     */
    @GET
    @Path("{id: \\d+}")
    public ObjetivoDetailDTO findObjetivo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        return new ObjetivoDetailDTO(logic.findObjetivoUsuario(idUsuario, id));
    }
    /**
     * metodo que crea un objetivo
     * @param idUsuario del usuario
     * @param id del objetivo
     * @return el objetivo asociado
     * @throws BusinessLogicException si el usuario o objetivo noe xiste 
     */
    @POST
    @Path("{id: \\d+}")
    public ObjetivoDTO createObjetivo(@PathParam("idUsuario") Long idUsuario,@PathParam("id") Long id) throws BusinessLogicException {
        return new ObjetivoDTO(logic.createObjetivoUsuario(idUsuario, id));
    }
    /**
     * metodo que elimina un objetivo
     * @param idUsuario del usuario
     * @param id del objetivo
     * @throws BusinessLogicException si el objetivo no existe o el usuario tampoco 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void removeObjetivo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        logic.removeObejtivoUsuario(idUsuario, id);
    }
}
