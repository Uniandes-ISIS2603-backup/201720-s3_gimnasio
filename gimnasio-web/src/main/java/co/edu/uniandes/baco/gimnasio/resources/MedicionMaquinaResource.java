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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicionMaquinaResource {
    private MedicionMaquinaLogic logic;

    public MedicionMaquinaResource() {
        //constructor para la parte web
    }

    @Inject public MedicionMaquinaResource(MedicionMaquinaLogic logic) {
        this.logic = logic;
    }
    
    @POST
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO post(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho, @PathParam(MEDICIONMAQUINAID) Long id ,MedicionMaquinaDTO nuevo) throws BusinessLogicException{
        return new MedicionMaquinaDTO(logic.create(idEjercicioHecho,nuevo.toEntity(),id));
    }
    
    @GET
    public List<MedicionMaquinaDetailDTO> getAll(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho)throws BusinessLogicException
    {
        return MedicionMaquinaDetailDTO.listDetailDTO(logic.findAll(idEjercicioHecho));
    }
    
    @GET
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO get(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho,@PathParam(MEDICIONMAQUINAID) long id) throws BusinessLogicException{
        return new MedicionMaquinaDTO(logic.find(idEjercicioHecho,id));
    }
    
    @PUT
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDTO put(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho ,@PathParam(MEDICIONMAQUINAID)long id, MedicionMaquinaDTO nuevo) throws BusinessLogicException{
        MedicionMaquinaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new MedicionMaquinaDTO(logic.update(idEjercicioHecho,entity));
    }
    
    @DELETE
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public void delete(@PathParam(EJERCICIOHECHOID) Long idEjercicioHecho ,@PathParam(MEDICIONMAQUINAID)long id) throws BusinessLogicException{
        logic.remove(idEjercicioHecho,id);
    } 
}
