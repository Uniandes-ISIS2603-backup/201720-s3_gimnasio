package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MedidaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
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
    @Path("{id: \\d+}")
    public MedidaDTO post(@PathParam("idEstado") Long idEstado,@PathParam("id") Long id, MedidaDTO nuevo) throws BusinessLogicException {
        return new MedidaDTO(logic.create(idEstado,nuevo.toEntity(),id));
    }

    @GET
    public List<MedidaDetailDTO> getAll(@PathParam("idEstado") Long isEstado) throws BusinessLogicException {
        return MedidaDetailDTO.listDTO(logic.findAll(isEstado));
    }

    @GET
    @Path("{id: \\d+}")
    public MedidaDetailDTO get(@PathParam("idEstado") Long idEstado, @PathParam("id") long id) throws BusinessLogicException {
        return new MedidaDetailDTO(logic.find(idEstado, id));
    }

    @PUT
    @Path("{id: \\d+}")
    public MedidaDTO put(@PathParam("idEstado") Long idEstado, @PathParam("id") long id, MedidaDTO nuevo) throws BusinessLogicException {
        MedidaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedidaDTO(logic.update(idEstado, entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("idEstado") Long idEstado,@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(id);
    }
}
