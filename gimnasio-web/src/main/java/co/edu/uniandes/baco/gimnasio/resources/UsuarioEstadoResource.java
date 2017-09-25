/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EstadoDTO;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.palacios437
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEstadoResource {
 
@Inject
private UsuarioLogic logic;

   private List<EstadoDTO> estadoListEntity2DTO(List<EstadoEntity> list)
{
    List<EstadoDTO> lts = new ArrayList<>();
    for(EstadoEntity est:list)
    {
       lts.add(new EstadoDTO(est));
    }
    return lts;
}
     private List<EstadoEntity> medidaListDTO2Entity(List<EstadoDTO> dtos) {
        List<EstadoEntity> list = new ArrayList<>();
        for (EstadoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    } 
     
    @GET
        public List<EstadoDTO> listmedidas(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return estadoListEntity2DTO(logic.findAllEstado(idUsuario));
    }
     @GET
    @Path("{EstadoId: \\d+}")
    public EstadoDTO getMedidas(@PathParam("idUsuario") Long idUsuario, @PathParam("EstadoId") Long EstadoId) throws BusinessLogicException {
        return new EstadoDTO(logic.getestado(idUsuario, EstadoId));
    }
    @POST
    @Path("{EstadoId: \\d+}")
    public EstadoDTO addBooks(@PathParam("idUsuario") Long idUsuario, @PathParam("EstadoId") Long EstadoId) throws BusinessLogicException {
        return new EstadoDTO(logic.addEstado(idUsuario, EstadoId));
    }
}
