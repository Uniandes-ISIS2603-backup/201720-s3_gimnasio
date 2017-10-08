package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MedidaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
    /**
     * injeccion de la logica de medida
     */
    @Inject
    private MedidaLogic logic;
    /**
    * metodo que crea una medida 
    * @param idEstado del estado
    * @param id del tipo de medida
    * @param nuevo el entity
    * @return la medida creada
    * @throws BusinessLogicException 
    */
    @POST
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public MedidaDTO post(@PathParam(ESTADOID) Long idEstado,@PathParam(TIPOMEDIDAID) Long id, MedidaDTO nuevo) throws BusinessLogicException {
        return new MedidaDTO(logic.create(idEstado,nuevo.toEntity(),id));
    }
    /**
     * metodo para obtener todo las medidas de une stado
     * @param idEstado del estado
     * @return lista con las medidas
     * @throws BusinessLogicException 
     */
    @GET
    public List<MedidaDetailDTO> getAll(@PathParam(ESTADOID) Long idEstado) throws BusinessLogicException {
        return MedidaDetailDTO.listDTO(logic.findAll(idEstado));
    }
    /**
     * metodo que retorna una medida especifica
     * @param idEstado del estado
     * @param id de la medida
     * @return la medida
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{"+MEDIDAID+": \\d+}")
    public MedidaDetailDTO get(@PathParam(ESTADOID) Long idEstado, @PathParam(MEDIDAID) long id) throws BusinessLogicException {
        return new MedidaDetailDTO(logic.find(idEstado, id));
    }
    /**
     * metodo que actuliza une stado
     * @param idEstado del estado
     * @param id de la medida
     * @param nuevo medida
     * @return la medida actulziada
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{"+MEDIDAID+": \\d+}")
    public MedidaDTO put(@PathParam(ESTADOID) Long idEstado, @PathParam(MEDIDAID) long id, MedidaDTO nuevo) throws BusinessLogicException {
        MedidaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedidaDTO(logic.update(idEstado, entity));
    }
    /**
     * metodo que elemina una medida
     * @param idEstado del estado
     * @param id de la medida
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{"+MEDIDAID+": \\d+}")
    public void delete(@PathParam(ESTADOID) Long idEstado,@PathParam(MEDIDAID) long id) throws BusinessLogicException{
        logic.remove(idEstado, id);
    }
}
