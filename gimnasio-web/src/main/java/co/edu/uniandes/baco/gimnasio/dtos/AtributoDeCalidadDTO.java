/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;

/**
 *
 * @author camilo
 */
public class AtributoDeCalidadDTO {
     //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private Long id;
     private int regresion;
     //--------------------------------------------
    // CONSTRUCTOR & TOENTITY 
    //--------------------------------------------

    public AtributoDeCalidadDTO() {
        //JAVAXS
    }

    public AtributoDeCalidadDTO(AtributoDeCalidadEntity entity) {
        this.regresion=entity.getRegresion();
        this.id=entity.getId();
    }
     /**
     * convierte un dto a entity
     * @return 
     */
    public AtributoDeCalidadEntity toEntity(){
        AtributoDeCalidadEntity aux=new AtributoDeCalidadEntity();
        aux.setRegresion(regresion);
        return aux;
    }
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRegresion() {
        return regresion;
    }

    public void setRegresion(int regresion) {
        this.regresion = regresion;
    }
    
}
