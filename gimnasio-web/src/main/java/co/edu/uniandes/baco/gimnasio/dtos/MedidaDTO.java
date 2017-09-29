/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;


import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;

/**
 *
 * @author js.palacios437
 */
public class MedidaDTO {
    /**
     * atributo id
     */
    private Long id;
     /**
     * atributo medida
     */
    private Double medida;
    /**
     * contructor vacio
     */
    public MedidaDTO()
    {
        
    }
    /**
     * metodo contructor
     * @param entity 
     */
    public MedidaDTO(MedidaEntity entity){
        this.id = entity.getId();
        this.medida = entity.getMedida();
    }
    /**
     * convierte un dto a entity
     * @return 
     */
    public MedidaEntity toEntity()
    {
        MedidaEntity ent = new MedidaEntity();
        ent.setId(this.id);
        ent.setMedida(this.medida);
        return ent;
    }
    
    //Getter and setter//
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    } 
}
