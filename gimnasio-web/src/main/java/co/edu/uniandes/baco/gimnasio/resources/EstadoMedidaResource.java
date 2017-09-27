/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.MedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.EstadoLogic;
import co.edu.uniandes.baco.gimnasio.ejb.MedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
 * @author js.palacios437
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoMedidaResource {

@Inject
private EstadoLogic estadologic;
@Inject
private MedidaLogic melogic;
private List<MedidaDTO> medidaListEntity2DTO(List<MedidaEntity> list)
{
    List<MedidaDTO> lts = new ArrayList<>();
    for(MedidaEntity med:list)
    {
       lts.add(new MedidaDTO(med));
    }
    return lts;
}

    private List<MedidaEntity> medidaListDTO2Entity(List<MedidaDTO> dtos) {
        List<MedidaEntity> list = new ArrayList<>();
        for (MedidaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
   
    @GET
    public List<MedidaDTO> listmedidas(@PathParam("Estadoid") Long Estadoid) throws BusinessLogicException {
        
        return medidaListEntity2DTO(melogic.findAll(Estadoid));
    }
    
   @GET
    @Path("{medidaId: \\d+}")
    public MedidaDTO getMedidas(@PathParam("Estadoid") Long estadoId, @PathParam("medidaId") Long medidasId) throws BusinessLogicException {
        return new MedidaDTO(melogic.find(estadoId, medidasId));
    }
    
    @PUT  
    public MedidaDTO updateMedida(@PathParam("Estadoid") Long estadoId,MedidaEntity  entity )throws BusinessLogicException
    {
        return new MedidaDTO(melogic.update(estadoId, entity));
    }
    @POST
    public MedidaDTO addBooks(@PathParam("Estadoid") Long Estadoid,MedidaDTO medida) throws BusinessLogicException {
        return new MedidaDTO(melogic.create(Estadoid,medida.toEntity()));
    }

    /**@DELETE
    @Path("{medidaId: \\d+}")
    public void removeBooks(@PathParam("Estadoid") Long Estadoid, @PathParam("medidaId") Long medidaId) throws BusinessLogicException{
        estadologic.removeMedida(medidaId, Estadoid);
    }  
    * **/

}
