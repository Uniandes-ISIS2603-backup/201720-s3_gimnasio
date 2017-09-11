/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jp.romero12
 */
@Entity
public class RutinaEntity extends BaseEntity implements Serializable{
    
    private String tipo;
    private Date fechaInicio;
    private Date fenchaFinal;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fenchaFinal
     */
    public Date getFenchaFinal() {
        return fenchaFinal;
    }

    /**
     * @param fenchaFinal the fenchaFinal to set
     */
    public void setFenchaFinal(Date fenchaFinal) {
        this.fenchaFinal = fenchaFinal;
    }
    
}
