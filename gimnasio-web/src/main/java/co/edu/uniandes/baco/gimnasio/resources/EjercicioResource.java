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
public class EjercicioResource{
    @Inject
    private EjercicioLogic logic;
    
    @POST
    public EjercicioDTO post(@PathParam("idRutina")Long idRutina,EjercicioDTO nuevo) throws BusinessLogicException{
        return new EjercicioDTO(logic.create(idRutina,nuevo.toEntity()));
    }
    
    @GET
    public List<EjercicioDetailDTO> getAll(@PathParam("idRutina")Long idRutina) throws BusinessLogicException {
        return EjercicioDetailDTO.listDetailDTO(logic.findAll(idRutina));
    }
    
    @GET
    @Path("{id: \\d+}")
    public EjercicioDetailDTO get(@PathParam("idRutina")Long idRutina,@PathParam("id") long id) throws BusinessLogicException {
        return new EjercicioDetailDTO(logic.find(idRutina,id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EjercicioDTO put(@PathParam("idRutina")Long idRutina,@PathParam("id")long id, EjercicioDTO nuevo) throws BusinessLogicException {
        EjercicioEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new EjercicioDTO(logic.update(idRutina,entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("idRutina")Long idRutina,@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(idRutina,id);
    }
    
    
    @Path("{idEjercicio: \\d+}/objetivos")
    public Class<EjercicioObjetivoResource> getEjercicioObjetivoResource(@PathParam("idEjercicio") Long id) throws BusinessLogicException{
        if (logic.find(id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return EjercicioObjetivoResource.class;
    }
    
    
    @Path("{idEjercicio: \\d+}/maquinas")
    public Class<EjercicioMaquinaResource> getEjercicioMaquinaResource(@PathParam("idEjercicio") Long id) throws BusinessLogicException{
        if (logic.find(id) == null)
            throw new WebApplicationException("El ejercicio no existe", 404);
        return EjercicioMaquinaResource.class;
    }
}
