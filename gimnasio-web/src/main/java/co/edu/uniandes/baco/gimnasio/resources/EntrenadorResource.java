package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EntrenadorLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
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

@Path(URLS.ENTRENADOR)
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EntrenadorResource {
    private EntrenadorLogic logic;

    public EntrenadorResource() {
        //constructor para la parte web
    }

    @Inject public EntrenadorResource(EntrenadorLogic entrenadorLogic) {
        this.logic = entrenadorLogic;
    }
    
    @GET
    public List<EntrenadorDetailDTO> getAll() throws BusinessLogicException {
        return EntrenadorDetailDTO.listDetailDTO(logic.findAll());
    }
    
    @GET
    @Path("{"+ENTRENADORID+": \\d+}")
    public EntrenadorDetailDTO get(@PathParam(ENTRENADORID)Long id)throws BusinessLogicException{
        return new EntrenadorDetailDTO(logic.find(id));
    }
    
    @POST
    public EntrenadorDTO create(EntrenadorDTO dto) throws BusinessLogicException{
        return new EntrenadorDTO(logic.create(dto.toEntity()));
    }
   
    @PUT
    @Path("{"+ENTRENADORID+": \\d+}") 
    public EntrenadorDTO update(@PathParam(ENTRENADORID)Long id, EntrenadorDTO dto) throws BusinessLogicException{
        EntrenadorEntity entity=dto.toEntity();
        entity.setId(id);
        return new EntrenadorDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{"+ENTRENADORID+": \\d+}") 
    public void delete(@PathParam(ENTRENADORID)Long id)throws BusinessLogicException{
        logic.remove(id);
    }
     
    @Path("{"+ENTRENADORID+": \\d+}/"+USUARIO)
    public Class <EntrenadorUsuarioResource> getUsuario(@PathParam(ENTRENADORID) Long id) throws BusinessLogicException{
        logic.find(id);
        return EntrenadorUsuarioResource.class;
    }
}
