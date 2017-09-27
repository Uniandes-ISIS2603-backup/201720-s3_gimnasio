/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
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
    @Inject
    UsuarioLogic logic;
    
    //--------------------
    //metodos
    //--------------------

    /**
     *
     * @return
     */
    
    @POST
    public UsuarioDTO create(UsuarioDTO p)throws BusinessLogicException
    {
        UsuarioEntity pcentity = p.toEntity();
        UsuarioEntity pnew = logic.create(pcentity);
        return new UsuarioDTO(pnew);
    }
    
    @GET
    public List<UsuarioDetailDTO> getEntrenadores() throws BusinessLogicException {
        return listEntity2DetailDTO(logic.findAll());
    }
    
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
    
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
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
     *
     * @param usuarioID
     * @return
     * @throws BusinessLogicException
     */
    
    @Path("{usuarioId: \\d+}/entrenadores")
    public Class<UsuarioEntrenadorResource> getEntrenadorUsuarioResource(@PathParam("usuarioId") Long usuarioID) throws BusinessLogicException
    {
        UsuarioEntity e  = logic.find(usuarioID);
        if(e == null)
        {
            throw new WebApplicationException("El usuario no existe", 404);
        }
        return UsuarioEntrenadorResource.class;
    }
    
    @Path("{idUsuario: \\d+}/rutinas")
    public Class<RutinaResource> getEjercicioResource(@PathParam("idUsuario") Long id) throws BusinessLogicException{
        if (logic.find(id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return RutinaResource.class;
    }
    @Path("{idUsuario: \\d+}/estados")
    public Class<EstadoResource> getEstadoResource(@PathParam("idUsuario") Long usid) throws BusinessLogicException{
         if (logic.find(usid) == null)
              throw new WebApplicationException("El estado no existe", 404);
        return EstadoResource.class;
    }
}
