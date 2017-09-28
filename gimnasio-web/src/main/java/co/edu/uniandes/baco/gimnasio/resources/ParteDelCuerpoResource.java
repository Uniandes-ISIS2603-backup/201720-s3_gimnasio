/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.dtos.ParteDelCuerpoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author js.palacios437
 */
@Path("partesDelCuerpo")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ParteDelCuerpoResource {
    
    @Inject
    ParteDelCuerpoLogic cplogic; //inject de la clase logic
    
    /**
     * metodo post para crear una nueva medida de cuenpo
     * @param Pcuer el DTO de la parte del cuerpo
     * @return retorna el DTO con la parte del cuerpo creada
     * @throws BusinessLogicException 
     */

    @POST
    public ParteDelCuerpoDTO creat(ParteDelCuerpoDTO Pcuer) throws BusinessLogicException
    {
        ParteDelCuerpoEntity pcentity = Pcuer.toEntity();
        ParteDelCuerpoEntity pcnew = cplogic.create(pcentity);
        return new ParteDelCuerpoDTO(pcnew);
        
    }
    @GET
  public List<ParteDelCuerpoDTO> getPartesDelCuerpo()throws BusinessLogicException
   {
     List<ParteDelCuerpoDTO> list = listEntitytoDetailDTO(cplogic.findAll());
      return list;
   }
    /**
     * da la parte del cuerpo segun su id
     * @param id  el id de lo que se queire busca
     * @return la aprte del cuerpo encontrada
     * @throws BusinessLogicException cuando no se encuentra la parte del cuerpo
     */
    @GET
    @Path("{id: \\d+}")
    public ParteDelCuerpoDTO getpartedelcuerpo(@PathParam("id")Long id)throws BusinessLogicException
    {
        ParteDelCuerpoEntity en = cplogic.find(id);
        if(en!=null)
        {
           return new ParteDelCuerpoDTO(en);
        }
        else
        {
             throw new BusinessLogicException(); 
        }
    }
    /**
     * realiza el update de una parte del cuerpo especifica
     * @param id de la parte de cuerpo a buscar
     * @param partedelcuerpo la nueva infomacion de la parte del cuerpo
     * @return el DTO resultante del emrgue
     * @throws BusinessLogicException  cuando la parte del cuerpo no existe
     */
    @PUT
    @Path("{id: \\d+}") 
    public  ParteDelCuerpoDTO upDateParteDelCuerpo(@PathParam("id") Long id,ParteDelCuerpoDTO partedelcuerpo)throws BusinessLogicException
    {
        ParteDelCuerpoEntity ent = cplogic.find(id);
        if(ent!=null)
        {
          ParteDelCuerpoEntity en = partedelcuerpo.toEntity();
          ent = cplogic.update(en);
                  
          return new ParteDelCuerpoDTO(en);
        }
        else
        {
            throw new BusinessLogicException();
        }
       }
    /**
     * metodo que borra una aprte del cuerpo
     * @param id el id de la parte del cuerpo a borrar
     * @throws BusinessLogicException si no existe esa parte de l cuerpo
     */
    @DELETE
    @Path("{id: \\d+}") 
    public void deletepartedelcuerpo(@PathParam("id")Long id)throws BusinessLogicException
    {
        ParteDelCuerpoEntity ent = cplogic.find(id);
        if(ent!=null)
        {
          
          cplogic.remove(id);
          
        }
        else
        {
            throw new BusinessLogicException();
        }  
    }
       private List<ParteDelCuerpoDTO> listEntitytoDetailDTO(List<ParteDelCuerpoEntity> entityList) {
        List<ParteDelCuerpoDTO> list = new ArrayList<>();
        for(ParteDelCuerpoEntity entity : entityList) {
            list.add(new ParteDelCuerpoDTO(entity));
        }
        return list;
    }
}
