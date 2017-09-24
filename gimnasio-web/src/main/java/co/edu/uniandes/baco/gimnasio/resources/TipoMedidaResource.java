/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.TipoMedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.TipoMedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
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
 * @author t.kavanagh
 */
@Path("tipoMedidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoMedidaResource{
    @Inject
    private TipoMedidaLogic logic;
    
    @POST
    public TipoMedidaDTO post(TipoMedidaDTO nuevo) throws BusinessLogicException{
        return new TipoMedidaDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<TipoMedidaDTO> getAll(){
        return TipoMedidaDTO.findall();
    }
    
    @GET
    @Path("{id: \\d+}")
    public TipoMedidaDTO get(@PathParam("id") long id) throws BusinessLogicException{
        return new TipoMedidaDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public TipoMedidaDTO put(@PathParam("id")long id, TipoMedidaDTO nuevo) throws BusinessLogicException{
        TipoMedidaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new TipoMedidaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(id);
    }
}
