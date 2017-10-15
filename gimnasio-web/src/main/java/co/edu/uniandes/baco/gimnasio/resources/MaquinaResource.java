/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioDetailDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDTO;
import co.edu.uniandes.baco.gimnasio.dtos.MaquinaDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.MaquinaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
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
 * @author t.kavanagh
 */
@Path(URLS.MAQUINA)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaquinaResource{
    
    private MaquinaLogic logic;

    public MaquinaResource() {
        //constrcutor para la parte web
    }

    @Inject public MaquinaResource(MaquinaLogic logic) {
        this.logic = logic;
    }
    
    @POST
    public MaquinaDTO post(MaquinaDTO nuevo) throws BusinessLogicException{
        return new MaquinaDTO(logic.create(nuevo.toEntity()));
    }
    
    @GET
    public List<MaquinaDetailDTO> getAll() throws BusinessLogicException{
        return MaquinaDetailDTO.listDetailDTO(logic.findAll());
    }
    
    @GET
    @Path("{"+MAQUINAID+": \\d+}")
    public MaquinaDetailDTO get(@PathParam(MAQUINAID) long id) throws BusinessLogicException{
        return new MaquinaDetailDTO(logic.find(id));
    }
    
    @PUT
    @Path("{"+MAQUINAID+": \\d+}")
    public MaquinaDTO put(@PathParam(MAQUINAID)long id, MaquinaDTO nuevo) throws BusinessLogicException{
        MaquinaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new MaquinaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{"+MAQUINAID+": \\d+}")
    public void delete(@PathParam(MAQUINAID) long id) throws BusinessLogicException{
        logic.remove(id);
    }
    
    @Path("{"+MAQUINAID+": \\d+}/"+TIPOMEDIDA)
    public Class<MaquinaTipoMediaResource> getEjercicioResource(@PathParam(MAQUINAID) Long idMaquina) throws BusinessLogicException{
       logic.find(idMaquina);
       return MaquinaTipoMediaResource.class;
    }
    
    @GET
    @Path("{"+MAQUINAID+": \\d+}/"+EJERCICIO)
    public List<EjercicioDetailDTO> findEjercicios(@PathParam(MAQUINAID) long id) throws BusinessLogicException{
        return EjercicioDetailDTO.listDetailDTO(logic.findAllEjercicio(id));
    }
}
