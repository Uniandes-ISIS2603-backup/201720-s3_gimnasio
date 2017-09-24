/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ce.robles
 */
@Entity
public class MedicionMaquinaEntity extends BaseEntity implements Serializable
{    
    private Double medicionManquina;

    public Double getMedicionManquina() 
    {
        return medicionManquina;
    }

    public void setMedicionManquina(Double medicionManquina)
    {
        this.medicionManquina = medicionManquina;
    }
}
