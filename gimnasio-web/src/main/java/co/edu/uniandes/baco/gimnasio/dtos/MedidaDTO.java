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
    
    private Long id;
    private Double medida;
    public MedidaDTO()
    {
        
    }
    
    public MedidaDTO(MedidaEntity entity)
    {
        this.id = entity.getId();
        this.medida = entity.getMedida();
    }
    
    public MedidaEntity toEntity()
    {
        MedidaEntity ent = new MedidaEntity();
        ent.setId(this.id);
        ent.setMedida(this.medida);
        return ent;
    }

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
