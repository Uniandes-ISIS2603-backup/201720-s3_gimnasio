/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EntrenadorLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author m.sicard10
 */
@Path("entrenadores")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EntrenadorResource {
    /**
     * injeccion a la logica
     */
    @Inject
    EntrenadorLogic entrenadorLogic;
    
    /**
     * genera la lista completa de entrenadores
     * path : http://localhost:8080/gimnasio-web/api/entrenadores
     * @return retorna una lista de entrenadores registrados en el sistema
     * @throws BusinessLogicException si no hay entrenadores, o la lista es nula
     */
    @GET
    public List<EntrenadorDetailDTO> getEntrenadores() throws BusinessLogicException {
        return listEntity2DetailDTO(entrenadorLogic.findAll());
    }
    
    /**
     * busca un entrenador especifico en el sistema dando por parametro el id
     * http://localhost:8080/gimnasio-web/api/entrenadores/{id}
     * @param id el id del entrenador
     * @return el entrenadr
     * @throws BusinessLogicException se lanza si el entrenador no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EntrenadorDTO getEntrenador(@PathParam("id")Long id)throws BusinessLogicException
    {
        EntrenadorEntity en = entrenadorLogic.find(id);
        if(en!=null)
        {
           return new EntrenadorDTO(en);
        }
        else
        {
             throw new BusinessLogicException("el entrenador no existe"); 
        }
    }
    /**
     * metodo que crea y persiste un entrenador
     * http://localhost:8080/gimnasio-web/api/entrenadores
     * @param p el entrrenador a crear.
     * @return el entrenador creado
     * @throws BusinessLogicException si existe un entrenador con el mismo documento
     */
    @POST
    public EntrenadorDTO create(EntrenadorDTO p) throws BusinessLogicException
    {
        EntrenadorEntity pcentity = p.toEntity();
        EntrenadorEntity pnew = entrenadorLogic.create(pcentity);
        return new EntrenadorDTO(pnew);
    }
    /**
     * metodo que actualiza la informacion de un entrenador
     * http://localhost:8080/gimnasio-web/api/entrenadores
     * @param id id del entrenador a modificar
     * @param e la informacion del entrenador a modificar
     * @return el entrenador modificado
     * @throws BusinessLogicException si el entrenador no existe o se trata de modificar el documento
     */
    @PUT
    @Path("{id: \\d+}") 
    public EntrenadorDetailDTO update(@PathParam("id")Long id, EntrenadorDetailDTO e) throws BusinessLogicException
    {
        EntrenadorEntity ent = entrenadorLogic.find(id);
          if(ent!= null) {
          EntrenadorEntity en = e.toEntity();
          ent = entrenadorLogic.update(en);
          return new EntrenadorDetailDTO(ent);  
          }else {
                   throw new BusinessLogicException("error");
               }
    }
    
    /**
     * borra un entrenador dado por parametro
     * http://localhost:8080/gimnasio-web/api/entrenadores/{id}
     * @param id id pasado por parametro
     * @throws BusinessLogicException si no se logra encontrar el entrenador
     */
    @DELETE
    @Path("{id: \\d+}") 
    public void deleteEntreador(@PathParam("id")Long id)throws BusinessLogicException
    {
              EntrenadorEntity ent = entrenadorLogic.find(id);
        if(ent!=null)
        {
          
          entrenadorLogic.remove(id);
          
        }
        else
        {
            throw new BusinessLogicException("no se pudo eliminaar ya que no existe");
        }  
    }
    /**
     * metodo que convierte una lista de entity a dto
     * @param entityList lista de entity
     * @return  lista de dto
     */
    private List<EntrenadorDetailDTO> listEntity2DetailDTO(List<EntrenadorEntity> entityList) {
        List<EntrenadorDetailDTO> list = new ArrayList<>();
        for (EntrenadorEntity entity : entityList) {
            list.add(new EntrenadorDetailDTO(entity));
        }
        return list;
    }
    
     
    /**
     * conexion con usuarios
     * http://localhost:8080/gimnasio-web/api/entrenadores/{identrenador}/usuarios
     * @param EntID id del enttrenador que pasa por parametro
     * @return clase que se encarga de manejar todo lo relacionado con los usuarios
     * @throws BusinessLogicException 
     */
    @Path("{EntrenadorId: \\d+}/usuarios")
    public Class <EntrenadorUsuarioResource> getEntrenadorUsuarioResource (@PathParam("EntrenadorId") Long EntID) throws BusinessLogicException{
        EntrenadorEntity e = entrenadorLogic.find(EntID);
        if (e == null)
        {
                        throw new WebApplicationException("El recurso /entrenadores/" + EntID + "/usuarios no existe.", 404);

        }
        return EntrenadorUsuarioResource.class;
    }
    
    
}
