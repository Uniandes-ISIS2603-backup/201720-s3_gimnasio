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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    UsuarioLogic usuarioLogic;
    
    //--------------------
    //metodos
    //--------------------

    /**
     *
     * @return
     */
    
    @POST
    public UsuarioDTO create(UsuarioDTO p)throws Exception
    {
        UsuarioEntity pcentity = p.toEntity();
        UsuarioEntity pnew = usuarioLogic.create(pcentity);
        return new UsuarioDTO(pnew);
    }
    
    @GET
    public List<UsuarioDetailDTO> getEntrenadores() throws Exception {
        return listEntity2DetailDTO(usuarioLogic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id")Long id) throws Exception
    {
        UsuarioEntity en = usuarioLogic.find(id);
        if(en!=null)
        {
           return new UsuarioDTO(en);
        }
        else
        {
             throw new BusinessLogicException("el usuario no existe"); 
        }
    }
    
    @DELETE
    @Path("{id: \\d+}") 
    public void deleteUsuario(@PathParam("id")Long id)throws Exception
    {
              UsuarioEntity ent = usuarioLogic.find(id);
        if(ent!=null)
        {
          
          usuarioLogic.remove(id);
          
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
    
    
    
}
