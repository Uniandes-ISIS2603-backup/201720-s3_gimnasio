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
public class EjercicioHechoMedicionResource 
{
    private MedicionMaquinaLogic logic;
    
    private EjercicioHechoMedicionResource()
    {
        
    }
    
    @Inject private EjercicioHechoMedicionResource(MedicionMaquinaLogic logic)
    {
        this.logic = logic;
    }
    
    @GET
    public List<MedicionMaquinaDetailDTO> findAll(@PathParam(EJERCICIOHECHOID) Long id) throws BusinessLogicException 
    {
        return MedicionMaquinaDetailDTO.listDetailDTO(logic.findAll(id));
    }
    
    @GET
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public MedicionMaquinaDetailDTO find(@PathParam(EJERCICIOHECHOID) Long idEjercicio, @PathParam(MEDICIONMAQUINAID) Long id) throws BusinessLogicException 
    {
        return new MedicionMaquinaDetailDTO(logic.find(idEjercicio, id));
    }
    
//    @POST
//    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
//    public MedicionMaquinaDTO create(@PathParam(EJERCICIOHECHOID) Long idEjercicio, @PathParam(MEDICIONMAQUINAID) Long id) throws BusinessLogicException 
//    {
//       return new MedicionMaquinaDTO(logic.createMedicion(idEjercicio, id));
//    }
    
    @DELETE
    @Path("{"+MEDICIONMAQUINAID+": \\d+}")
    public void removeMedicion(@PathParam(EJERCICIOHECHOID) Long idEjercicio, @PathParam(MEDICIONMAQUINAID) Long id) throws BusinessLogicException 
    {
        logic.remove(idEjercicio, id);
    }
}
