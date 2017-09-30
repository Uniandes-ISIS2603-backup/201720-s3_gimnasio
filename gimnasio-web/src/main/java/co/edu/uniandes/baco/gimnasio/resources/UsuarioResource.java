/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
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
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class UsuarioResource {
    
    //--------------------
    //Atributos
    //--------------------
    /**
     * injeccion de la logica de usuario
     */
    @Inject
    UsuarioLogic logic;
    
    //--------------------
    //metodos
    //--------------------

    /**
     *metodo para crear un usuario
     * @return el usuario creado
     */
    
    @POST
    public UsuarioDTO create(UsuarioDTO p)throws BusinessLogicException
    {
        UsuarioEntity pcentity = p.toEntity();
        UsuarioEntity pnew = logic.create(pcentity);
        return new UsuarioDTO(pnew);
    }
    /**
     * metodo para obtener el usuario de un entrenado
     * @return los usuario solicitado
     * @throws BusinessLogicException 
     */
    @GET
    public List<UsuarioDetailDTO> getEntrenadores() throws BusinessLogicException {
        return UsuarioDetailDTO.listDetailDTO(logic.findAll());
    }
    /**
     * metodo para encontrar un usuario especifico
     * @param id del usuario
     * @return el usuario solicitad
     * @throws BusinessLogicException si el usuario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id")Long id) throws BusinessLogicException
    {
        UsuarioEntity en = logic.find(id);
        if(en!=null)
        {
           return new UsuarioDetailDTO(en);
        }
        else
        {
             throw new BusinessLogicException("el usuario no existe"); 
        }
    }
    /**
     * metodo que elimina un usuario
     * @param id el id del usuario
     * @throws BusinessLogicException si el usuario no existe
     */
    @DELETE
    @Path("{id: \\d+}") 
    public void deleteUsuario(@PathParam("id")Long id)throws BusinessLogicException
    {
              UsuarioEntity ent = logic.find(id);
        if(ent!=null)
        {
          
          logic.remove(id);
          
        }
        else
        {
            throw new BusinessLogicException("no se pudo eliminaar ya que no existe");
        }  
    }
    /**
     * metodo para actulzias un usuario
     * @param id el id del usuari
     * @param e detalle del usuario
     * @return retorna un detail del usuario
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}") 
    public UsuarioDetailDTO update(@PathParam("id")Long id, UsuarioDetailDTO e) throws BusinessLogicException
    {
        UsuarioEntity ent = logic.find(id);
          if(ent!= null) {
          UsuarioEntity en = e.toEntity();
          en.setId(id);
          ent = logic.update(en);
          return new UsuarioDetailDTO(ent);  
          }else {
                   throw new BusinessLogicException("error");
               }
    }
    /**
     *metodo que debuelve un entrenado
     * @param usuarioID ddel usuario
     * @return el entrenador
     * @throws BusinessLogicException si el usuario o el entrenador no existen
     */
    @GET
    @Path("{usuarioId: \\d+}/entrenadores")
    public List<EntrenadorDTO> getEntrenadorUsuarioResource(@PathParam("usuarioId") Long usuarioID) throws BusinessLogicException
    {
       List<EntrenadorEntity> a;
        a = logic.find(usuarioID).getEntrenadores();
        return EntrenadorListEntity2DTOSimple(a);
    }
    /**
     * metodo que  convierte una lista de entrenadore a dto
     * @param entityList lista de entity
     * @return lsita de dtos
     */
    private List<EntrenadorDTO> EntrenadorListEntity2DTOSimple(List<EntrenadorEntity> entityList) {
        List<EntrenadorDTO> list = new ArrayList<>();
        for (EntrenadorEntity entity : entityList) {
            list.add(new EntrenadorDTO(entity));
        }
        return list;
    }
    /**
     * metodo que regresa los servicio de una rutina
     * @param id del usuario
     * @return la rutina
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{idUsuario: \\d+}/rutinas")
    public Class<RutinaResource> getEjercicioResource(@PathParam("idUsuario") Long id) throws BusinessLogicException{
        if (logic.find(id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return RutinaResource.class;
    }
    /**
     * metodo que retorna los servicios de estado
     * @param usid id del usuari
     * @return la clase encargada de lso servicio
     * @throws BusinessLogicException 
     */
    @Path("{idUsuario: \\d+}/estados")
    public Class<EstadoResource> getEstadoResource(@PathParam("idUsuario") Long usid) throws BusinessLogicException{
         if (logic.find(usid) == null)
              throw new WebApplicationException("El estado no existe", 404);
        return EstadoResource.class;
    }
    /**
     * metodo que devuelve los servicio de objetivos
     * @param usid id del usuari
     * @return la clase encargada
     * @throws BusinessLogicException 
     */
    @Path("{idUsuario: \\d+}/objetivos")
    public Class<UsuarioObjetivoResource> getUsuarioObjetivoResource(@PathParam("idUsuario") Long usid) throws BusinessLogicException{
         if (logic.find(usid) == null)
              throw new WebApplicationException("El estado no existe", 404);
        return UsuarioObjetivoResource.class;
    }
}
