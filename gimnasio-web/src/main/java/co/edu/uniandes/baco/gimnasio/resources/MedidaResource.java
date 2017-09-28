/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Produces("application/json")
@Consumes("application/json")
public class MedidaResource {

    @Inject
    private MedidaLogic logic;

    @POST
    public MedidaDTO post(@PathParam("idEstado") Long idEstado, MedidaDTO nuevo) throws BusinessLogicException {
        return new MedidaDTO(logic.create(idEstado, nuevo.toEntity()));
    }

    @GET
    public List<MedidaDTO> getAll(@PathParam("idEstado") Long isEstado) throws BusinessLogicException {
        return MedidaDTO.listDetailDTO(logic.findAll(isEstado));
    }

    @GET
    @Path("{id: \\d+}")
    public MedidaDTO get(@PathParam("idEstado") Long idEstado, @PathParam("id") long id) throws BusinessLogicException {
        return new MedidaDTO(logic.find(idEstado, id));
    }

    @PUT
    @Path("{id: \\d+}")
    public MedidaDTO put(@PathParam("idEstado") Long idEstado, @PathParam("id") long id, MedidaDTO nuevo) throws BusinessLogicException {
        MedidaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedidaDTO(logic.update(idEstado, entity));
    }
}
