package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDetailDTO;
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
public class EjercicioMaquinaResource {
    private EjercicioLogic logic;

    public EjercicioMaquinaResource() {
        //constructor para la parte web
    }

    @Inject public EjercicioMaquinaResource(EjercicioLogic logic) {
        this.logic = logic;
    }
    
    @GET
    public List<MaquinaDetailDTO> findAllMaquinas(@PathParam(EJERCICIOID) Long id) throws BusinessLogicException {
          return MaquinaDetailDTO.listDetailDTO(logic.findAllMaquina(id));
    }

    @GET
    @Path("{"+MAQUINAID+": \\d+}")
    public MaquinaDTO findMaquina(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(MAQUINAID) Long id) throws BusinessLogicException {
        return new MaquinaDTO(logic.findMaquina(idEjercicio, id));
    }

    @POST
    @Path("{"+MAQUINAID+": \\d+}")
    public MaquinaDTO createMaquina(@PathParam(EJERCICIOID) Long idEjericio,@PathParam(MAQUINAID) Long id) throws BusinessLogicException {
        return new MaquinaDTO(logic.createMaquina(idEjericio, id));
    }

    @DELETE
    @Path("{"+MAQUINAID+": \\d+}")
    public void removeMaquina(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(MAQUINAID) Long id) throws BusinessLogicException {
        logic.removeMaquina(idEjercicio, id);
    }
}
