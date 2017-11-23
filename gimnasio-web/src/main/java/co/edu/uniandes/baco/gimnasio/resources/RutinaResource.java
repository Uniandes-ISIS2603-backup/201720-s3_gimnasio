/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.RutinaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.RutinaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.RutinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.text.ParseException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.bojaca
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RutinaResource {
    /**
     * injeccion de la logica de rutina
     */
    private RutinaLogic logic;

    public RutinaResource() {
        //constructor para la parte web
    }

    @Inject public RutinaResource(RutinaLogic logic) {
        this.logic = logic;
    }
    
    /**
     * metodo para asociasr o crear una rutina a un usuario
     * @param idUsuario id del usuario
     * @param nuevo la rutina
     * @return la rutina agregada
     * @throws BusinessLogicException si el usuario noe xiste 
     */
    @POST
    public RutinaDTO post(@PathParam(USUARIOID) Long idUsuario,RutinaDTO nuevo) throws BusinessLogicException, ParseException{
        return new RutinaDTO(logic.create(idUsuario,nuevo.toEntity()));
    }
    /**
     * metodo que para obtener todas las rutinas de un usuario
     * @param idUsuario id del usuario
     * @return la lsita de rutinas
     * @throws BusinessLogicException 
     */
    @GET
    public List<RutinaDetailDTO> getAll(@PathParam(USUARIOID) Long idUsuario) throws BusinessLogicException {
        return RutinaDetailDTO.listDetailDTO(logic.findAll(idUsuario));
    }
    /**
     * metodo para obtener una rutina especifica
     * @param idUsuario id del usuario
     * @param id de a rutina
     * @return la rutina
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{"+RUTINAID+": \\d+}")
    public RutinaDetailDTO get(@PathParam(USUARIOID) Long idUsuario,@PathParam(RUTINAID) long id) throws BusinessLogicException {
        return new RutinaDetailDTO(logic.find(idUsuario,id));
    }
    /**
     * metodo que actuliza una rutina
     * @param idUsuario id del usuario
     * @param id de la rutina
     * @param nuevo la rutina a actulizar
     * @return la rutina actulizada
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{"+RUTINAID+": \\d+}")
    public RutinaDTO put(@PathParam(USUARIOID) Long idUsuario,@PathParam(RUTINAID)long id, RutinaDTO nuevo) throws BusinessLogicException, ParseException {
        RutinaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new RutinaDTO(logic.update(idUsuario,entity));
    }
    /**
     * metodo para eliminar una rutina
     * @param idUsuario id del usuario
     * @param id id de la rutina
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{"+RUTINAID+": \\d+}")
    public void delete(@PathParam(USUARIOID) Long idUsuario,@PathParam(RUTINAID) long id) throws BusinessLogicException{
        logic.remove(idUsuario,id);
    }
    /**
     * metodo para obtener los servicios de ejericio
     * @param idUsuario id del usuari
     * @param id id de la rutina
     * @return la clase de servicion
     * @throws BusinessLogicException 
     */
    @Path("{"+RUTINAID+": \\d+}/"+EJERCICIO)
    public Class<EjercicioInsResource> getEjercicioResource(@PathParam(USUARIOID) Long idUsuario,@PathParam(RUTINAID) Long id) throws BusinessLogicException{
        logic.find(idUsuario,id);
        return EjercicioInsResource.class;
    }
}
