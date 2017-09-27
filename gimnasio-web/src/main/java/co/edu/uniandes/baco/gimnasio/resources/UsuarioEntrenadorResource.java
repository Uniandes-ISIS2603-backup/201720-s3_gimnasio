/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDTO;
import co.edu.uniandes.baco.gimnasio.dtos.EntrenadorDetailDTO;
import co.edu.uniandes.baco.gimnasio.ejb.UsuarioLogic;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *URI: usuarios/{usuarioId: \\ d+}/entrenadores
 * @author m.sicard10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEntrenadorResource {
    @Inject 
    private UsuarioLogic usuarioLogic;
    
    private List<EntrenadorDetailDTO> EntrenadorListEntity2DTO(List<EntrenadorEntity> entityList) {
        List<EntrenadorDetailDTO> list = new ArrayList<>();
        for (EntrenadorEntity entity : entityList) {
            list.add(new EntrenadorDetailDTO(entity));
        }
        return list;
    }
    
    private List<EntrenadorEntity> EntrenadorListDTO2Entity(List<EntrenadorDetailDTO> dtos) {
        List<EntrenadorEntity> list = new ArrayList<>();
        for (EntrenadorDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    private List<EntrenadorDTO> EntrenadorListEntity2DTOSimple(List<EntrenadorEntity> entityList) {
        List<EntrenadorDTO> list = new ArrayList<>();
        for (EntrenadorEntity entity : entityList) {
            list.add(new EntrenadorDTO(entity));
        }
        return list;
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BusinessLogicException
     */
    /**
    @GET
    public List<EntrenadorDetailDTO> listEntrenadores(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        List<EntrenadorEntity> a;
        a = usuarioLogic.
        return EntrenadorListEntity2DTO(a);
    }
    **/
    
}