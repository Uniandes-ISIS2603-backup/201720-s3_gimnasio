/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.ejb.GimnasioLogic;
import co.edu.uniandes.baco.gimnasio.dtos.GimnasioDetailDTO;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.GimnasioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.WebApplicationException;

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
     * @param Gimnasio correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "GimnasioDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public GimnasioDetailDTO createGimnasio(GimnasioDetailDTO Gimnasio) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        GimnasioEntity GimnasioEntity = Gimnasio.toEntity();
        // Invoca la lógica para crear la Gimnasio nueva
        GimnasioEntity nuevoGimnasio = gimnasioLogic.createGimnasio(GimnasioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new GimnasioDetailDTO(nuevoGimnasio);
    }

    /**
     * GET para todas las Gimnasioes.
     * http://localhost:8080/gimnasio-web/api/gimnasios
     *
     * @return la lista de todas las Gimnasioes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<GimnasioDetailDTO> getGimnasios() throws BusinessLogicException {
        return listEntity2DetailDTO(gimnasioLogic.getGimnasios());
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
    public GimnasioDetailDTO updateGimnasio(@PathParam("id") Long id, GimnasioDetailDTO gimnasio) throws BusinessLogicException {
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

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos GimnasioEntity a una lista de
     * objetos GimnasioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Gimnasioes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Gimnasioes en forma DTO (json)
     */
    private List<GimnasioDetailDTO> listEntity2DetailDTO(List<GimnasioEntity> entityList) {
        List<GimnasioDetailDTO> list = new ArrayList<>();
        for (GimnasioEntity entity : entityList) {
            list.add(new GimnasioDetailDTO(entity));
        }
        return list;
    }
    
    @Path("{id: \\d+}/entrenadores")
    public Class <EntrenadorResource> getEntrenador (@PathParam("EntrenadorId") Long EntID) throws BusinessLogicException{
        return EntrenadorResource.class;
    }
    
    @Path("{id: \\d+}/maquinas")
    public Class <MaquinaResource> getMaquinas (@PathParam("EntrenadorId") Long EntID) throws BusinessLogicException{
        return MaquinaResource.class;
    }
    @Path("{id: \\d+}/usuarios")
    public Class <UsuarioResource> getUsuarios (@PathParam("EntrenadorId") Long EntID) throws BusinessLogicException{
        return UsuarioResource.class;
    }
    @Path("{id: \\d+}/ParteDelCuerpo")
    public Class <ParteDelCuerpoResource> getParteDelCuerpo (@PathParam("EntrenadorId") Long EntID) throws BusinessLogicException{
        return ParteDelCuerpoResource.class;
    }
    

}
