/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.PartesDelCuerpoEntity;

/**
 *
 * @author js.palacios437
 */
public class ParteDelCuerpoDTO {
    
    private Long id;
    private String parteDelCuerpo;

    public ParteDelCuerpoDTO()
    {
        
    }
    public ParteDelCuerpoDTO(PartesDelCuerpoEntity entity)
    {
        this.id= entity.getId();
        this.parteDelCuerpo = entity.getPartedelcuerpo();
    }
    public PartesDelCuerpoEntity toEntity()
    {
        PartesDelCuerpoEntity enti = new PartesDelCuerpoEntity();
        enti.setId(this.id);
        enti.setPartedelcuerpo(this.parteDelCuerpo);
        return enti;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParteDelCuerpo() {
        return parteDelCuerpo;
    }

    public void setParteDelCuerpo(String parteDelCuerpo) {
        this.parteDelCuerpo = parteDelCuerpo;
    }
    
    
}
