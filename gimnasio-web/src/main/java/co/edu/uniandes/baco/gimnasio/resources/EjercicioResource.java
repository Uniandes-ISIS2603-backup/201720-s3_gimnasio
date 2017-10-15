/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
@Path(URLS.EJERCICIO)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioResource {
    private EjercicioLogic logic;

    public EjercicioResource() {
        //constructor para la parte web
    }

     @Inject public EjercicioResource(EjercicioLogic logic) {
        this.logic = logic;
    }
     
     @POST
    public EjercicioDTO post(EjercicioDTO nuevo) throws BusinessLogicException{
        return new EjercicioDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<EjercicioDetailDTO> getAll() throws BusinessLogicException{
        return EjercicioDetailDTO.listDetailDTO(logic.findAll());
    }
    
    @GET
    @Path("{"+EJERCICIOID+": \\d+}")
    public EjercicioDetailDTO get(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        return new EjercicioDetailDTO(logic.find(id));
    }
    
    @PUT
    @Path("{"+EJERCICIOID+": \\d+}")
    public EjercicioDTO put(@PathParam(EJERCICIOID)long id, EjercicioDTO nuevo) throws BusinessLogicException{
        EjercicioEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EjercicioDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{"+EJERCICIOID+": \\d+}")
    public void delete(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.remove(id);
    }
    
     @Path("{"+EJERCICIOID+": \\d+}/"+OBJETIVO)
    public Class<EjercicioObjetivoResource> getObjetivo(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.find(id);
        return EjercicioObjetivoResource.class;
    }
    
    @Path("{"+EJERCICIOID+": \\d+}/"+MAQUINA)
    public Class<EjercicioMaquinaResource> getMaquina(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.find(id);
        return EjercicioMaquinaResource.class;
    }
    
     @Path("{"+EJERCICIOID+": \\d+}/"+TIPOMEDIDA)
    public Class<EjercicioMaquinaResource> getTipoMedida(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.find(id);
        return EjercicioMaquinaResource.class;
    }
}
