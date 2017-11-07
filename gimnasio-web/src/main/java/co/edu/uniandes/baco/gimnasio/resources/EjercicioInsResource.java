/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EjercicioInstanciaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EjercicioInstanciaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EjercicioInstanciaLogic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author jc.bojaca
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioInsResource{
    private EjercicioInstanciaLogic logic;

    public EjercicioInsResource() {
        //constructor para la parte web
    }

     @Inject public EjercicioInsResource(EjercicioInstanciaLogic logic) {
        this.logic = logic;
    }
    
    @POST
    @Path("{"+EJERCICIOID+": \\d+}")
    public EjercicioInstanciaDTO post(@PathParam(RUTINAID)Long idRutina,EjercicioInstanciaDTO nuevo,@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        return new EjercicioInstanciaDTO(logic.create(idRutina,nuevo.toEntity(),id));
    }
    
    @GET
    public List<EjercicioInstanciaDetailDTO> getAll(@PathParam(RUTINAID)Long idRutina) throws BusinessLogicException {
        return EjercicioInstanciaDetailDTO.listDetailDTO(logic.findAll(idRutina));
    }
    
    @GET
    @Path("{"+EJERCICIOID+": \\d+}")
    public EjercicioInstanciaDetailDTO get(@PathParam(RUTINAID)Long idRutina,@PathParam(EJERCICIOID) long id) throws BusinessLogicException {
        return new EjercicioInstanciaDetailDTO(logic.find(idRutina,id));
    }
    
    @GET
    @Path("{"+EJERCICIOID+": \\d+}/DATA")
    public EjercicioDetailDTO getEjercicio(@PathParam(RUTINAID)Long idRutina,@PathParam(EJERCICIOID) long id) throws BusinessLogicException {
        return new EjercicioDetailDTO(logic.findEjercicio(idRutina,id));
    }
    
    @PUT
    @Path("{"+EJERCICIOID+": \\d+}")
    public EjercicioInstanciaDTO put(@PathParam(RUTINAID)Long idRutina,@PathParam(EJERCICIOID)long id, EjercicioInstanciaDTO nuevo) throws BusinessLogicException {
        EjercicioInstanciaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EjercicioInstanciaDTO(logic.update(idRutina,entity));
    }
    
   @DELETE
   @Path("{"+EJERCICIOID+": \\d+}")
    public void delete(@PathParam(RUTINAID)Long idRutina,@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.remove(idRutina,id);
   }
    
    
     @Path("{"+EJERCICIOID+": \\d+}/"+EJERCICIOHECHO)
    public Class<EjercicioHechoResource> getEjercicioHecho(@PathParam(EJERCICIOID) long id) throws BusinessLogicException{
        logic.find(id);
        return EjercicioHechoResource.class;
    }
    
}
