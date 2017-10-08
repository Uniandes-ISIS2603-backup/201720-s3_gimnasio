package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.GimnasioDTO;
import co.edu.uniandes.baco.gimnasio.ejb.GimnasioLogic;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path(URLS.GIMNASIO)
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class GimnasioResource{
    /**
     * injecion de logica
     */
    private GimnasioLogic gimnasioLogic; 
    
    public GimnasioResource() {
        //constructor para la parte web
    }
    /**
     * injeciones
     * @param gimnasioLogic injecion de logica del gimnasio
     */
    @Inject public GimnasioResource(GimnasioLogic gimnasioLogic) {
        this.gimnasioLogic = gimnasioLogic;
    }
    
    @POST
    public GimnasioDTO createGimnasio(GimnasioDTO gimnasio) throws BusinessLogicException {
        return new GimnasioDTO(gimnasioLogic.create(gimnasio.toEntity()));
    }

    @GET
    public List<GimnasioDTO> getGimnasios() throws BusinessLogicException {
        return GimnasioDTO.listDTO(gimnasioLogic.findAll());
    }

    @PUT
    @Path("{"+GIMNASIOID+": \\d+}")
    public GimnasioDTO updateGimnasio(@PathParam(GIMNASIOID) Long id, GimnasioDTO gimnasio) throws BusinessLogicException {
          throw new UnsupportedOperationException("Este servicio  no está implementado");
    }

    @DELETE
    @Path("{"+GIMNASIOID+": \\d+}")
    public void deleteGimnasio(@PathParam(GIMNASIOID) Long id) throws BusinessLogicException {
             gimnasioLogic.remove(id);
    }
}
