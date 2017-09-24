/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;



/**
 *
 * @author js.palacios437
 */
@Entity
public class MedidaEntity extends BaseEntity implements Serializable{

    
    Double medida;  // atributo que modela una medida
    @PodamExclude
    @ManyToOne
    private EstadoEntity estado;
    @PodamExclude
    @ManyToOne
    private ParteDelCuerpoEntity parte;

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public ParteDelCuerpoEntity getParte() {
        return parte;
    }

    public void setParte(ParteDelCuerpoEntity parte) {
        this.parte = parte;
    }
    
    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }


    
}
