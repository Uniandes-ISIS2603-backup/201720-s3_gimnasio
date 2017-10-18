/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.*;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import co.edu.uniandes.baco.gimnasio.entities.*;
import co.edu.uniandes.baco.gimnasio.exceptions.*;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ce.robles
 */
@Path(URLS.EJERCICIOHECHO)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioHechoResource {
    private EjercicioHechoLogic logic;

    public EjercicioHechoResource() {
        //constructor para la parte web
    }

    @Inject public EjercicioHechoResource(EjercicioHechoLogic logic) {
        this.logic = logic;
    }  
    
    @POST
    public EjercicioHechoDTO post(EjercicioHechoDTO nuevo) throws BusinessLogicException{
        return new EjercicioHechoDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<EjercicioHechoDTO> getAll() throws BusinessLogicException {
        return EjercicioHechoDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDTO get(@PathParam(EJERCICIOHECHOID) long id) throws BusinessLogicException{
        return new EjercicioHechoDTO(logic.find(id));
    }
    
    @PUT
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDTO put(@PathParam(EJERCICIOHECHOID)long id, EjercicioHechoDTO nuevo) throws BusinessLogicException {
        EjercicioHechoEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new EjercicioHechoDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public void delete(@PathParam(EJERCICIOHECHOID) long id) throws BusinessLogicException{
        logic.remove(id);
    }
    
    @Path("{"+EJERCICIOHECHOID+": \\d+}/"+MEDICIONMAQUINAID)
    public Class<MedicionMaquinaResource> getMedicionMaquina(@PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException
    {
        logic.find(id);
        return MedicionMaquinaResource.class;
    }
}
