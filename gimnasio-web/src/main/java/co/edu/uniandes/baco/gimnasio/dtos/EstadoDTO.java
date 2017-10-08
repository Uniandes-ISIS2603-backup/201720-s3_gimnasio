/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import java.util.Date;

/**
 *
 * @author js.palacios437
 */
public class EstadoDTO {
    /**
     * atributo id
     */
    private Long id;
        /**
     * atributo fecha
     */
    private Date fecha;
    
    /**
     * contructor vacio
     */
    public EstadoDTO() {
        //nada
    }
    /**
     * contructor de la clase
     * @param entity 
     */
    public EstadoDTO(EstadoEntity entity) {
        this.id = entity.getId();
         this.fecha = entity.getFecha();
    }
    /**
     * convierte un dto a entity
     * @return 
     */
    public EstadoEntity toEntity() {
        EstadoEntity ent = new EstadoEntity();
        ent.setId(this.id);
        ent.setFecha(fecha);
        return ent;
    }
///Getter and setters//
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
}
