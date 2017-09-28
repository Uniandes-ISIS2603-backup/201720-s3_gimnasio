/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EstadoDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EstadoDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.palacios437
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    private MedidaLogic Medidalogic;

    @POST
    public MedidaDTO post(@PathParam("idUsuario") Long idUsuario,MedidaDTO nuevo) throws BusinessLogicException{
        return new MedidaDTO(Medidalogic.create(idUsuario,nuevo.toEntity()));
    }
    
    @GET
    public List<MedidaDTO> getAll(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return MedidaDTO.listDetailDTO(Medidalogic.findAll(idUsuario));
    }
    
    @GET
    @Path("{id: \\d+}")
    public MedidaDTO get(@PathParam("idUsuario") Long idUsuario,@PathParam("id") long id) throws BusinessLogicException {
        return new MedidaDTO(Medidalogic.find(idUsuario,id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MedidaDTO put(@PathParam("idUsuario") Long idUsuario,@PathParam("id")long id, MedidaDTO nuevo) throws BusinessLogicException {
        MedidaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new MedidaDTO(Medidalogic.update(idUsuario,entity));
    }

    @Path("{MedidaId: \\d+}/medidas")
    public Class<MedidaResource> getMedidaMedida(@PathParam("MedidaId") Long MedidaId) throws BusinessLogicException {
        MedidaEntity entity = Medidalogic.find(MedidaId);
        if (entity == null) {
            throw new WebApplicationException("noe xiste el Medida", 404);
        }
        return MedidaResource.class;
    }
}
