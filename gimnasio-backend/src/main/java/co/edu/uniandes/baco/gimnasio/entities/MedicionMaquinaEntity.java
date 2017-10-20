/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    @PodamExclude
    @ManyToOne
    private EjercicioHechoEntity ejercicioEnt;
    
    @PodamExclude
    @ManyToOne
    private TipoMedidaEntity tipoMedida;
    
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
    
     public TipoMedidaEntity getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(TipoMedidaEntity tipoMedida) {
        this.tipoMedida = tipoMedida;
    }
    
    public EjercicioHechoEntity getEjercicioEnt() {
        return ejercicioEnt;
    }

    public void setEjercicioEnt(EjercicioHechoEntity ejercicioEnt) {
        this.ejercicioEnt = ejercicioEnt;
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
