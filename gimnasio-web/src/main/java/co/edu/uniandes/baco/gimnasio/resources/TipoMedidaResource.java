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
import static co.edu.uniandes.baco.gimnasio.resources.URLS.*;
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
@Path(URLS.TIPOMEDIDA)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoMedidaResource {
   /**
    * injecion de la logica de tipo medida
    */
    private TipoMedidaLogic logic;

    public TipoMedidaResource() {
        //constructor para la parte web
    }

     @Inject public TipoMedidaResource(TipoMedidaLogic logic) {
        this.logic = logic;
    }
     
    /**
     * Convierte una lista de  dto a entity
     * @param dtos 
     * @return Lista de entitys
     */
    private List<TipoMedidaEntity> medidaListDTO2Entity(List<TipoMedidaDTO> dtos) {
        List<TipoMedidaEntity> list = new ArrayList<>();
        for (TipoMedidaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    /**
     * metodo que crear un tipo de medida
     * @param nuevo
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public TipoMedidaDTO post(TipoMedidaDTO nuevo) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.create(nuevo.toEntity()));
    }
    /**
     * metodo de post que agrega carga masiva de estados
     * @param listdto lsita de dtos
     * @return la lsita agregada
     * @throws BusinessLogicException 
     */
    @POST
    @Path("addList")
    public List<TipoMedidaDTO> postList(List<TipoMedidaDTO> listdto) throws BusinessLogicException {
        return TipoMedidaDTO.listDTO(logic.createList(medidaListDTO2Entity(listdto)));
    }
    /**
     * metodo para obtener todos los tipos de medida
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<TipoMedidaDTO> getAll() throws BusinessLogicException {
        return TipoMedidaDTO.listDTO(logic.findAll());
    }
    /**
     * obtiene un tipo de medida especific
     * @param id el del tipod e mdeida
     * @return el tipo de medida
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO get(@PathParam(TIPOMEDIDAID) long id) throws BusinessLogicException {
        return new TipoMedidaDTO(logic.find(id));
    }
    /**
     * metodo que actuliza un tipo de mdeida
     * @param id de la medida
     * @param nuevo la medida  a actulizar
     * @return el tipo de medida actulizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public TipoMedidaDTO put(@PathParam(TIPOMEDIDAID) long id, TipoMedidaDTO nuevo) throws BusinessLogicException {
        TipoMedidaEntity entity = nuevo.toEntity();
        entity.setId(id);
        return new TipoMedidaDTO(logic.update(entity));
    }
   /**
    * metodo que elimina un tipo de medida
    * @param id de la medida
    * @throws BusinessLogicException 
    */
    @DELETE
    @Path("{"+TIPOMEDIDAID+": \\d+}")
    public void delete(@PathParam(TIPOMEDIDAID) long id) throws BusinessLogicException {
        logic.remove(id);
    }
}
