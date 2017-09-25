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
public class RutinaEntity extends BaseEntity implements Serializable{
    @PodamExclude
    @ManyToOne
    UsuarioEntity usuario;
    
    @PodamExclude
    @OneToMany(mappedBy = "rutina", orphanRemoval = true, cascade = CascadeType.REFRESH)
    private List<EjercicioEntity> ejercicios=new ArrayList<>();
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fenchaFinal;

    public List<EjercicioEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioEntity> ejercicios) {
        this.ejercicios = ejercicios;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
