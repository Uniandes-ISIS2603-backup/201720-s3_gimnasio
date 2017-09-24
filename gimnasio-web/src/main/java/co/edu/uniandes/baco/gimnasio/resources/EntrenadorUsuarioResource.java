/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EntrenadorLogic;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
 *URI: entrenadores/{entrenadorID: \\ d+}/usuarios
 * @author m.sicard10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EntrenadorUsuarioResource {
    @Inject private EntrenadorLogic entrenadorLogic;
     
    private List<UsuarioDetailDTO> usuarioListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
    private List<UsuarioEntity> usuarioListDTO2Entity(List<UsuarioDetailDTO> dtos) {
        List<UsuarioEntity> list = new ArrayList<>();
        for (UsuarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    @GET
    public List<UsuarioDetailDTO> listusuarios(@PathParam("EntrenadorId") Long id) throws BusinessLogicException, Exception {
        return usuarioListEntity2DTO(entrenadorLogic.listUsuario(id));
    }
    
    @POST
    @Path("{usuarioId: \\d+}")
    public UsuarioDetailDTO addUsuario(@PathParam("EntrenadorId") Long entrenadorId, @PathParam("usuariosId") Long usuarioId) throws BusinessLogicException, Exception {
        return new UsuarioDetailDTO(entrenadorLogic.addUsuarioDeail(entrenadorId, usuarioId));
    }
    
    @DELETE
    @Path("{usuarioID: \\d+}")
    public void removeAuthors(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("usuarioID") Long usuarioId) throws BusinessLogicException, Exception {
        entrenadorLogic.removeUsuario(EntrenadorId, usuarioId);
    }
}
