package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.GimnasioDTO;
import co.edu.uniandes.baco.gimnasio.ejb.GimnasioLogic;
import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
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

/**
 * Clase que implementa el recurso REST correspondiente a "Gimnasios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Gimnasios". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Gimnasios"
 *
 * @author ISIS2603
 *
 */
@Path("gimnasio")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class GimnasioResource {

    @Inject
    GimnasioLogic gimnasioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(GimnasioResource.class.getName());

    /**
     * POST http://localhost:8080/gimnasio-web/api/gimnasios
     *
     * @param gimnasio correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "GimnasioDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public GimnasioDTO createGimnasio(GimnasioDTO gimnasio) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        GimnasioEntity gimnasioEntity = gimnasio.toEntity();
        // Invoca la lógica para crear la Gimnasio nueva
        GimnasioEntity nuevoGimnasio = gimnasioLogic.create(gimnasioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new GimnasioDTO(nuevoGimnasio);
    }

    /**
     * GET para todas las Gimnasioes.
     * http://localhost:8080/gimnasio-web/api/gimnasios
     *
     * @return la lista de todas las Gimnasioes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<GimnasioDTO> getGimnasios() throws BusinessLogicException {
        return GimnasioDTO.listDTO(gimnasioLogic.findAll());
    }


   
    /**
     * PUT http://localhost:8080/gimnasio-web/api/gimnasios/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Gimnasio a actualizar.
     * @param gimnasio corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Gimnasio actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Gimnasio a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public GimnasioDTO updateGimnasio(@PathParam("id") Long id, GimnasioDTO gimnasio) throws BusinessLogicException {
          throw new UnsupportedOperationException("Este servicio  no está implementado");
    }

    /**
     * DELETE http://localhost:8080/gimnasio-web/api/gimnasio/{id}
     *
     * @param id corresponde a la Gimnasio a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Gimnasio a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteGimnasio(@PathParam("id") Long id) throws BusinessLogicException { 
        GimnasioEntity e = gimnasioLogic.find(id);
         if(e != null)
         {
             gimnasioLogic.remove(id);
         }
         else
         {
                     throw new BusinessLogicException("no se pudo eliminaar ya que no existe");
         }
    }
    
    @Path("{id: \\d+}/entrenadores")
    public Class <EntrenadorResource> getEntrenador (@PathParam("EntrenadorId") Long entID) throws BusinessLogicException{
        return EntrenadorResource.class;
    }
}
