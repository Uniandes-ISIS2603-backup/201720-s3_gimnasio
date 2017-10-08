/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo
 */
public class AtributoDeCalidadDetailDTO extends AtributoDeCalidadDTO{
      
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    private String descripcion;
    private String unidad;
    
    //--------------------------------------------
    // CONSTRUCTOR & LIST
    //--------------------------------------------

    public AtributoDeCalidadDetailDTO() {
        super();
    }
     public AtributoDeCalidadDetailDTO(AtributoDeCalidadEntity entity) {
        super(entity);
        if (entity.getTipoMedida() != null) {
            this.descripcion = entity.getTipoMedida().getTipoMedida();
            this.unidad = entity.getTipoMedida().getUnidad();
        }
    }
     
    public static final List<AtributoDeCalidadDetailDTO> listDTO(List<AtributoDeCalidadEntity> entity) {
        List<AtributoDeCalidadDetailDTO> resp = new ArrayList<>();
        for (AtributoDeCalidadEntity ent : entity) {
            resp.add(new AtributoDeCalidadDetailDTO(ent));
        }
        return resp;
    }
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
     
}
