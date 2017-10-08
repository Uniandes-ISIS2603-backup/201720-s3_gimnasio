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
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;

public class MaquinaDTO {
    private long id;
    private String descripcion;

    public MaquinaDTO() {
        //javax necesita un constructor vacio
    }
    
    public MaquinaDTO(MaquinaEntity foo) {
        if(foo!=null){
            id = foo.getId();
            descripcion = foo.getInformacion();

        }
    }
     /**
     * convierte un dto a entity
     * @return 
     */
    public MaquinaEntity toEntity() {
        MaquinaEntity entity = new MaquinaEntity();
        entity.setInformacion(this.descripcion);
        return entity;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
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
