/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.TipoMedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MaquinaLogic;
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
 * @author camilo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaquinaTipoMediaResource {
    private MaquinaLogic logic;

    public MaquinaTipoMediaResource() {
        //constructor para la parte web
    }

    @Inject public MaquinaTipoMediaResource(MaquinaLogic logic) {
        this.logic = logic;
    }

    @GET
    public List<TipoMedidaDTO> findAllObejtivos(@PathParam(MAQUINAID) Long id) throws BusinessLogicException {
        return TipoMedidaDTO.listDTO(logic.findAllTipoMedidaMaquina(id));
    }

    @GET
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO findTipoMedida(@PathParam(MAQUINAID) Long idMaquina, @PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.findTipoMedida(idMaquina, id));
    }

    @POST
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO createTipoMedida(@PathParam(MAQUINAID) Long idMaquina, @PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.createTipoMedida(idMaquina, id));
    }

    @DELETE
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public void removeTipoMedida(@PathParam(MAQUINAID) Long idMaquina, @PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        logic.removeTipoMedida(idMaquina, id);
    }
}
