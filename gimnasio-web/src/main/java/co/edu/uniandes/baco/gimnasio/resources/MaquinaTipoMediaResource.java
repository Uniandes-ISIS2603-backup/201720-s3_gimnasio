/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.TipoMedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.TipoMedidaLogic;
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
public class MaquinaTipoMediaResource {
    @Inject
    private TipoMedidaLogic logic;

    @GET
    public List<TipoMedidaDTO> findAllObejtivos(@PathParam("idMaquina") Long id) throws BusinessLogicException {
        return TipoMedidaDTO.listDTO(logic.findAllTipoMedidaMaquina(id));
    }

    @GET
    @Path("{id: \\d+}")
    public TipoMedidaDTO findTipoMedida(@PathParam("idMaquina") Long idMaquina, @PathParam("id") Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.findTipoMedidaMaquina(idMaquina, id));
    }

    @POST
    @Path("{id: \\d+}")
    public TipoMedidaDTO createTipoMedida(@PathParam("idMaquina") Long idMaquina, @PathParam("id") Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.createTipoMedidaMaquina(idMaquina, id));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void removeTipoMedida(@PathParam("idMaquina") Long idMaquina, @PathParam("id") Long id) throws BusinessLogicException {
        logic.removeTipoMedidaMaquina(idMaquina, id);
    }
}
