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
 * @author js.palacios437
 */
@Entity
public class MedidaEntity extends BaseEntity implements Serializable{
    
    Double medida; // atributo que modela una medida
    String parteDelCuerpo;// atributo que modela una aprte del cuerpo

    // getter and setters
    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }

    public String getParteDelCuerpo() {
        return parteDelCuerpo;
    }

    public void setParteDelCuerpo(String parteDelCuerpo) {
        this.parteDelCuerpo = parteDelCuerpo;
    }
    
    
}
