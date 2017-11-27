/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.romero12
 */
@Entity
public class RutinaEntity extends BaseEntity implements Serializable {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * fecha de inicio de la rutina
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    /**
     * fecha de finalizacion de la rutina
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinal;
    /**
     * porcentaje de cumplimiento de la rutina
     */
    private Double cumplimiento;

    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * usuario al que pertenece la rutina
     */
    @PodamExclude
    @ManyToOne
    UsuarioEntity usuario;

    /**
     * conjunto de ejercios que componen la rutina
     */
    @PodamExclude
    @OneToMany(mappedBy = "rutina", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EjercicioInstanciaEntity> ejercicios = new ArrayList<>();

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Double getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Double cumplimiento) {
        this.cumplimiento = cumplimiento;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<EjercicioInstanciaEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioInstanciaEntity> ejercicios) {
        this.ejercicios = ejercicios;
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
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFinal);
        return hash;
    }
}
