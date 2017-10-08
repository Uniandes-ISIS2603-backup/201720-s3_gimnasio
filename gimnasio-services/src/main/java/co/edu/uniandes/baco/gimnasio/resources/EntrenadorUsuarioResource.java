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
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
    private EntrenadorLogic entrenadorLogic;

    public EntrenadorUsuarioResource() {
        //constructor para la parte web
    }

    @Inject public EntrenadorUsuarioResource(EntrenadorLogic entrenadorLogic) {
        this.entrenadorLogic = entrenadorLogic;
    }
     
    @GET
    public List<UsuarioDetailDTO> getAll(@PathParam(ENTRENADORID) Long id) throws BusinessLogicException {
        return UsuarioDetailDTO.listDetailDTO(entrenadorLogic.findAllUsuario(id));
    }
    
    @GET
    @Path("{"+USUARIOID+": \\d+}")
    public UsuarioDetailDTO get(@PathParam(ENTRENADORID) Long entrenadorId, @PathParam(USUARIOID) Long usuarioId)throws BusinessLogicException{
        return new UsuarioDetailDTO(entrenadorLogic.findUsuario(entrenadorId, usuarioId));
    }
    
   
    @POST
    @Path("{"+USUARIOID+": \\d+}")
    public UsuarioDetailDTO add(@PathParam(ENTRENADORID) Long entrenadorId, @PathParam(USUARIOID) Long usuarioId) throws BusinessLogicException{
        UsuarioEntity a = entrenadorLogic.createUsuario(entrenadorId, usuarioId);
        return new UsuarioDetailDTO(a);
    }
    
    @DELETE
    @Path("{"+USUARIOID+": \\d+}")
    public void remove(@PathParam(ENTRENADORID) Long entrenadorId, @PathParam(USUARIOID) Long usuarioId) throws BusinessLogicException{
        entrenadorLogic.removeUsuario(entrenadorId, usuarioId);
    }
    
    /**
    /**
     * redirecciona a las rutinas del usuario
     * http://localhost:8080/gimnasio-web/api/entrenadores/{idEntrenador}/usuarios/{idUsuario}/rutinas
     * @param entrenadorId
     * @param usuarioId
     * @return
     * @throws BusinessLogicException 
     *
    @Path("{idUsuario: \\d+}/rutinas")
    public Class<RutinaResource> irRutina(@PathParam("EntrenadorId") Long entrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.findUsuario(entrenadorId, usuarioId) != null)
            return RutinaResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
    /**
     * redicecciona a los estados del usuario
     * http://localhost:8080/gimnasio-web/api/entrenadores/{idEntrenador}/usuarios/{idUsuario}/estados
     * @param entrenadorId
     * @param usuarioId
     * @return
     * @throws BusinessLogicException 
     *
    @Path("{idUsuario: \\d+}/estados")
    public Class<EstadoResource> irEstado(@PathParam("EntrenadorId") Long entrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.findUsuario(entrenadorId, usuarioId) != null)
            return EstadoResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
    /**
     * redirecciona a los objetivos de un usuario
     * http://localhost:8080/gimnasio-web/api/entrenadores/{idEntrenador}/usuarios/{idUsuario}/objetivos
     * @param entrenadorId
     * @param usuarioId
     * @return
     * @throws BusinessLogicException 
     *
    @Path("{idUsuario: \\d+}/objetivos")
    public Class<ObjetivoResource> irObjetivos(@PathParam("EntrenadorId") Long entrenadorId, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if(entrenadorLogic.findUsuario(entrenadorId, usuarioId) != null)
            return ObjetivoResource.class;
        else 
            throw new BusinessLogicException("no usuario deberia existir");
    }
    */
}
