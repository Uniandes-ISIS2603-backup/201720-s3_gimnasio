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
import java.util.ArrayList;
import java.util.List;

public class TipoMedidaDTO {
    /**
     * atributo id
     */
    private Long id;
    /**
     * atributo descripcion
     */
    private String descripcion;
    /**
     * atributo unidad
     */
    private String unidad;
    /**
     * atributo automatico
     */
    private boolean automatico;

    /**
     * contruictor vacio
     */  
    public TipoMedidaDTO() {
        //javaxs
    }
    
    /**
     * Constructor por defecto
     */
    public TipoMedidaDTO(TipoMedidaEntity foo) {      
        if(foo!=null){
            id = foo.getId();
            descripcion = foo.getDescripcion();
            unidad = foo.getUnidad();
            automatico=foo.isAutomatico();
        }
    }
    /**
     * metodo que convierte un dto a entity
     * @return 
     */
    public TipoMedidaEntity toEntity() {
        TipoMedidaEntity entity = new TipoMedidaEntity();
        entity.setDescripcion(this.descripcion);
        entity.setUnidad(this.unidad);
        entity.setAutomatico(this.isAutomatico());
        return entity;
    }
    
    public static final List<TipoMedidaDTO> listDTO(List<TipoMedidaEntity> entity) {
        List<TipoMedidaDTO> resp = new ArrayList<>();
        for (TipoMedidaEntity ent : entity) {
            resp.add(new TipoMedidaDTO(ent));
        }
        return resp;
    }
   ///getters and setters//
    public boolean isAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }
    
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
}
