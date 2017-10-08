/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author ce.robles
 */
@Entity
public class MedicionMaquinaEntity extends BaseEntity implements Serializable{
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private Double medicionManquina;
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    
    public Double getMedicionManquina() 
    {
        return medicionManquina;
    }

    public void setMedicionManquina(Double medicionManquina)
    {
        this.medicionManquina = medicionManquina;
    }
    
    //--------------------------------------------
    // METODOS
    //--------------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.medicionManquina);
        return hash;
    }
}
