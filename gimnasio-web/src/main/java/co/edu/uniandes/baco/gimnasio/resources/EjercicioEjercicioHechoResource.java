/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import javax.ws.rs.core.MediaType;
import co.edu.uniandes.baco.gimnasio.dtos.*;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author ce.robles
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioEjercicioHechoResource 
{
    private EjercicioInstanciaLogic logic;
    
    private EjercicioEjercicioHechoResource()
    {
        
    }

    @Inject public EjercicioEjercicioHechoResource(EjercicioInstanciaLogic logic) 
    {
        this.logic = logic;
    }
    
    @GET
    public List<EjercicioHechoDetailDTO> findAllEjercicios(@PathParam(EJERCICIOID) Long id) throws BusinessLogicException 
    {
          return EjercicioHechoDetailDTO.listDetailDTO(logic.findAllEjercicio(id));
    }
    
    @GET
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDetailDTO findEjercicio(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException 
    {
        return new EjercicioHechoDetailDTO(logic.findEjercicioHecho(idEjercicio, id));
    }
    
    @POST
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public EjercicioHechoDTO createMaquina(@PathParam(EJERCICIOID) Long idEjericio,@PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException 
    {
        return new EjercicioHechoDTO(logic.createEjercicioHecho(idEjericio, id));
    }
    
    @DELETE
    @Path("{"+EJERCICIOHECHOID+": \\d+}")
    public void removeEjercicioHecho(@PathParam(EJERCICIOID) Long idEjercicio, @PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException 
    {
        logic.removeEjercicioHecho(idEjercicio, id);
    }
}
