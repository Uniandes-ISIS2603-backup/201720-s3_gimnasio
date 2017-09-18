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

public class TipoMedidaDTO {

    private Long id;
    private String descripcion;
    private String unidad;

    /**
     * Constructor por defecto
     */
    public TipoMedidaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param TipoMEdida: Es la entidad que se va a convertir a DTO
     */
    public TipoMedidaDTO(TipoMedidaEntity TipoMedida) {
        this.id = TipoMedida.getId();
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
        entity.setId(this.id);
        return entity;
    }
    
}
