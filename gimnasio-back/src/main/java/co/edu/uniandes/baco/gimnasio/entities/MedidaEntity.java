/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author js.palacios437
 */
@Entity
public class MedidaEntity extends BaseEntity implements Serializable{
    Double medida; // atributo que modela una medida
   
    @OneToOne
    PartesDelCuerpoEntity parte;

    // getter and setters
    public PartesDelCuerpoEntity getParte() {
        return parte;
    }

    public void setParte(PartesDelCuerpoEntity parte) {
        this.parte = parte;
    }
    
    
    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }


    
}
