package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.TipoMedidaDTO;
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
public class EjercicioTipoMedidaResource {
    private EjercicioLogic logic;

    public EjercicioTipoMedidaResource() {
        //constructor para la parte web
    }

    @Inject public EjercicioTipoMedidaResource(EjercicioLogic logic) {
        this.logic = logic;
    }
    
    @GET
    public List<TipoMedidaDTO> findAllTipoMedidas(@PathParam(EJERCICIOID) Long id) throws BusinessLogicException {
          return TipoMedidaDTO.listDTO(logic.findAllTipoMedida(id));
    }

    @GET
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO findTipoMedida(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.findTipoMedida(idEjercicio, id));
    }

    @POST
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO createTipoMedida(@PathParam(EJERCICIOID) Long idEjericio,@PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.createTipoMedida(idEjericio, id));
    }

    @DELETE
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public void removeTipoMedida(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(TIPOMEDIDAID) Long id) throws BusinessLogicException {
        logic.removeTipoMedida(idEjercicio, id);
    }
}
