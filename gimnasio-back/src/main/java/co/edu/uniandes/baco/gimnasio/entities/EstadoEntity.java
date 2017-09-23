/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.palacios437
 */
@Entity
public class EstadoEntity extends BaseEntity implements Serializable{
    
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fecha; // atributo que modela la fecha de toma de datos
   
   @PodamExclude
   @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
   List<MedidaEntity> medidas=new ArrayList<>();

    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
    }
     // getter and setters
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
}
