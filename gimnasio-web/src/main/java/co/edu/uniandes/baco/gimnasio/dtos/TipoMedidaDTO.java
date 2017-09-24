/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

/**
 *
 * @author t.kavanagh
 */


import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import java.util.List;

public class TipoMedidaDTO {

    public TipoMedidaDTO() {
        //javaxs
    }
    
    

    public static List<TipoMedidaDTO> findall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Long id;
    private String descripcion;
    private String unidad;

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

    /**
     * Constructor por defecto
     */
    public TipoMedidaDTO(TipoMedidaEntity foo) {
        if(foo!=null){
            id = foo.getId();
            descripcion = foo.getDescripcion();
            unidad = foo.getUnidad();
        }
    }




    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TipoMedidaEntity toEntity() {
        TipoMedidaEntity entity = new TipoMedidaEntity();
        entity.setDescripcion(this.descripcion);
        entity.setUnidad(this.unidad);
        entity.setId(this.id);
        return entity;
    }
    
}
