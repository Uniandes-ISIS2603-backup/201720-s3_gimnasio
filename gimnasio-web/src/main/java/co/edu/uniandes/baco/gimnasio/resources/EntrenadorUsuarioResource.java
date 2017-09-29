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
    /**
     * injeccion con la logica del entrenador
     */
    @Inject 
    private EntrenadorLogic entrenadorLogic;
     
    /**
     * cambia de lista de entity a DTO
     * @param entityList lista de entity
     * @return lista de DTO
     */
    private List<UsuarioDetailDTO> usuarioListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * cambia Lista de DTO a Enttity
     * @param dtos Lista de DDTO
     * @return Lista de Entity
     */
    private List<UsuarioEntity> usuarioListDTO2Entity(List<UsuarioDetailDTO> dtos) {
        List<UsuarioEntity> list = new ArrayList<>();
        for (UsuarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * da una lista de usuarios de un entrenador
     * http://localhost:8080/gimnasio-web/api/entrenadores/{id}/usuarios
     * @param id id del entrenador
     * @return lista de usuarios
     * @throws BusinessLogicException si la lista es inexistente
     */
    @GET
    public List<UsuarioDetailDTO> listusuarios(@PathParam("EntrenadorId") Long id) throws BusinessLogicException {
        return usuarioListEntity2DTO(entrenadorLogic.listUsuario(id));
    }
    
    /**
     * conecta un usuario con un entrenador
     * http://localhost:8080/gimnasio-web/api/entrenadores/{idEntrenador}/usuarios/{idUsuario}
     * @param entrenadorId el 
     * @param usuarioId
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{usuarioId: \\d+}")
    public UsuarioDetailDTO addUsuario(@PathParam("EntrenadorId") Long entrenadorId, @PathParam("usuarioId") Long usuarioId) throws BusinessLogicException{
        UsuarioEntity a = entrenadorLogic.addUsuarioDeail(entrenadorId, usuarioId);
        return new UsuarioDetailDTO(a);
    }
    /**
     * borra un usuario de un entrenador
     * http://localhost:8080/gimnasio-web/api/entrenadores/{idEntrenador}/usuarios/{idUsuario}
     * @param EntrenadorId
     * @param usuarioId
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{usuarioID: \\d+}")
    public void removeUsuario(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("usuarioID") Long usuarioId) throws BusinessLogicException{
        entrenadorLogic.removeUsuario(EntrenadorId, usuarioId);
    }
    
    @GET
    @Path("{usuarioID: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("usuarioID") Long usuarioId)throws BusinessLogicException
    {
        return new UsuarioDetailDTO(entrenadorLogic.getusuario(EntrenadorId, usuarioId));
    }
    
    @Path("{idUsuario: \\d+}/rutinas")
    public Class<RutinaResource> irRutina(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.getusuario(EntrenadorId, usuarioId) != null)
            return RutinaResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
    
    @Path("{idUsuario: \\d+}/estados")
    public Class<EstadoResource> irEstado(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.getusuario(EntrenadorId, usuarioId) != null)
            return EstadoResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
    
    @Path("{idUsuario: \\d+}/objetivos")
    public Class<ObjetivoResource> irObjetivos(@PathParam("EntrenadorId") Long EntrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.getusuario(EntrenadorId, usuarioId) != null)
            return ObjetivoResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
}
