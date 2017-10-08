/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import java.util.Date;

/**
 *
 * @author jc.bojaca
 */
public class RutinaDTO {
    private Long id;
    private Date fechaInicio;
    private Date fechaFinal;

    public RutinaDTO() {
        //javaxs
    }
    
    public RutinaDTO(RutinaEntity entity){
        id=entity.getId();
        fechaInicio=entity.getFechaInicio();
        fechaFinal=entity.getFechaFinal();
    }
    
    public RutinaEntity toEntity(){
        RutinaEntity ent=new RutinaEntity();
        ent.setFechaInicio(fechaInicio);
        ent.setFechaFinal(fechaFinal);
        return ent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
