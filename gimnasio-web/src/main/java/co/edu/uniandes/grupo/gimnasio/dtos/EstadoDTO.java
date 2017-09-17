/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.grupo.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;

/**
 *
 * @author js.palacios437
 */
public class EstadoDTO {
    
    private Long id;
    
    
    public EstadoDTO()
    {
        
    }
    
    public EstadoDTO(EstadoEntity entity)
    {
        this.id= entity.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public EstadoEntity toEntity()
    {
        EstadoEntity ent = new EstadoEntity();
        ent.setId(this.id);
        return ent;
    }
}
