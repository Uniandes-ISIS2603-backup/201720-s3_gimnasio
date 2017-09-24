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
import java.util.List;

public class MaquinaDTO {

    public static List<MaquinaDTO> findall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private long id;
    private String descripcion;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Constructor por defecto
     */
    public MaquinaDTO(MaquinaEntity foo) {
        if(foo!=null){
            id = foo.getId();
            descripcion = foo.getDescripcion();

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
    public MaquinaEntity toEntity() {
        MaquinaEntity entity = new MaquinaEntity();
        entity.setDescripcion(this.descripcion);
        entity.setId(this.id);
        return entity;
    }
    
}
