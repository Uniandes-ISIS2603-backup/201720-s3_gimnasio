/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author js.palacios437
 */
@Entity
public class EstadoEntity extends BaseEntity implements Serializable{
    
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fecha; // atributo que modela la fecha de toma de datos
   private Double peso; // atributo que modela el peso
   private Double presionSanguinea; // atributo que modela la presion sanguienea
   private Integer ritmeoCardiaco; // atributo que modela el ritmo cardiaco

     // getter and setters
    public Date getFecha() {
        return fecha;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getPresionSanguinea() {
        return presionSanguinea;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setPresionSanguinea(Double presionSanguinea) {
        this.presionSanguinea = presionSanguinea;
    }

    public void setRitmeoCardiaco(Integer ritmeoCardiaco) {
        this.ritmeoCardiaco = ritmeoCardiaco;
    }
    public Integer getRitmeoCardiaco()
    {
        return this.ritmeoCardiaco;
    }
    
   
    
    
}
