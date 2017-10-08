/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDTO;
import co.edu.uniandes.baco.gimnasio.dtos.UsuarioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
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

/**
 *
 * @author m.sicard10
 */
@Path(URLS.USUARIO)
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
    private UsuarioLogic logic;

    public UsuarioResource() {
        //constructor para la parte web
    }

    @Inject public UsuarioResource(UsuarioLogic logic) {
        this.logic = logic;
    }

    //--------------------
    //metodos
    //--------------------
    /**
     * metodo para crear un usuario
     *
     * @param dto
     * @return el usuario creado
     * @throws co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException
     */
    @POST
    public UsuarioDTO create(UsuarioDTO dto) throws BusinessLogicException {
        return new UsuarioDTO(logic.create(dto.toEntity()));
    }

    /**
     * metodo para obtener el usuario de un entrenado
     *
     * @return los usuario solicitado
     * @throws BusinessLogicException
     */
    @GET
    public List<UsuarioDetailDTO> getAll() throws BusinessLogicException {
        return UsuarioDetailDTO.listDetailDTO(logic.findAll());
    }

    /**
     * metodo para encontrar un usuario especifico
     *
     * @param id del usuario
     * @return el usuario solicitad
     * @throws BusinessLogicException si el usuario no existe
     */
    @GET
    @Path("{" + USUARIOID + ": \\d+}")
    public UsuarioDetailDTO get(@PathParam(USUARIOID) Long id) throws BusinessLogicException {
        return new UsuarioDetailDTO(logic.find(id));
    }

    /**
     * metodo que elimina un usuario
     *
     * @param id el id del usuario
     * @throws BusinessLogicException si el usuario no existe
     */
    @DELETE
    @Path("{" + USUARIOID + ": \\d+}")
    public void delete(@PathParam(USUARIOID) Long id) throws BusinessLogicException {
        logic.remove(id);
    }

    /**
     * metodo para actulzias un usuario
     *
     * @param id el id del usuari
     * @param dto detalle del usuario
     * @return retorna un detail del usuario
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{" + USUARIOID + ": \\d+}")
    public UsuarioDetailDTO update(@PathParam(USUARIOID) Long id, UsuarioDetailDTO dto) throws BusinessLogicException {
        UsuarioEntity en = dto.toEntity();
        en.setId(id);
        return new UsuarioDetailDTO(logic.update(en));
    }

    /**
     * metodo que debuelve un entrenado
     *
     * @param usuarioID ddel usuario
     * @return el entrenador
     * @throws BusinessLogicException si el usuario o el entrenador no existen
     */
    @GET
    @Path("{"+USUARIOID+": \\d+}/"+ENTRENADOR)
    public List<EntrenadorDetailDTO> getEntrenador(@PathParam(USUARIOID) Long usuarioID) throws BusinessLogicException {
        return EntrenadorDetailDTO.listDetailDTO(logic.find(usuarioID).getEntrenadores());
    }

    /**
     * metodo que regresa los servicio de una rutina
     *
     * @param id del usuario
     * @return la rutina
     * @throws BusinessLogicException
     */

    @Path("{"+USUARIOID+": \\d+}/"+RUTINA)
    public Class<RutinaResource> getRutina(@PathParam(USUARIOID) Long id) throws BusinessLogicException {
        logic.find(id);
        return RutinaResource.class;
    }

    /**
     * metodo que retorna los servicios de estado
     *
     * @param usid id del usuari
     * @return la clase encargada de lso servicio
     * @throws BusinessLogicException
     */
    @Path("{"+USUARIOID+": \\d+}/"+ESTADO)
    public Class<EstadoResource> getEstado(@PathParam(USUARIOID) Long usid) throws BusinessLogicException {
        logic.find(usid);
        return EstadoResource.class;
    }

    /**
     * metodo que devuelve los servicio de objetivos
     *
     * @param usid id del usuari
     * @return la clase encargada
     * @throws BusinessLogicException
     */
    @Path("{"+USUARIOID+": \\d+}/"+OBJETIVO)
    public Class<UsuarioObjetivoResource> getObjetivo(@PathParam(USUARIOID) Long usid) throws BusinessLogicException {
        logic.find(usid);
        return UsuarioObjetivoResource.class;
    }
}
