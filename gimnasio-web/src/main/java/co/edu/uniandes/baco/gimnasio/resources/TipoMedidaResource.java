/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.dtos.TipoMedidaDTO;
import co.edu.uniandes.baco.gimnasio.ejb.TipoMedidaLogic;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
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
 * @author t.kavanagh
 */
@Path("tipoMedidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoMedidaResource{
    @Inject
    private TipoMedidaLogic logic;
    
        private List<TipoMedidaDTO> medidaListEntity2DTO(List<TipoMedidaEntity> entityList) {
        List<TipoMedidaDTO> list = new ArrayList<>();
        for (TipoMedidaEntity entity : entityList) {
            list.add(new TipoMedidaDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     * 
     */
    private List<TipoMedidaEntity> medidaListDTO2Entity(List<TipoMedidaDTO> dtos) {
        List<TipoMedidaEntity> list = new ArrayList<>();
        for (TipoMedidaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
   // @POST
   // public TipoMedidaDTO post(TipoMedidaDTO nuevo) throws BusinessLogicException{
    //    return new TipoMedidaDTO(logic.create(nuevo.toEntity()));
   // }
    @POST
    public List<TipoMedidaDTO> postlista(List<TipoMedidaDTO> listdto) throws BusinessLogicException
     {
         
         List<TipoMedidaEntity> ll =logic.createlist(medidaListDTO2Entity(listdto));
         return medidaListEntity2DTO(ll);
     
     }
    @GET
    public List<TipoMedidaDTO> getAll() throws BusinessLogicException{
        return TipoMedidaDTO.listDTO(logic.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public TipoMedidaDTO get(@PathParam("id") long id) throws BusinessLogicException{
        return new TipoMedidaDTO(logic.find(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public TipoMedidaDTO put(@PathParam("id")long id, TipoMedidaDTO nuevo) throws BusinessLogicException{
        TipoMedidaEntity entity=nuevo.toEntity();
        entity.setId(id);
        return new TipoMedidaDTO(logic.update(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") long id) throws BusinessLogicException{
        logic.remove(id);
    }
}
