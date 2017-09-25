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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.romero12
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    private String nombre;
    private Boolean genero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeNacimiento;
    
    @PodamExclude
    @ManyToMany(mappedBy = "usuarios")
    private List<EntrenadorEntity> entrenadores = new ArrayList();
    
    @PodamExclude
    @ManyToOne
    private GimnasioEntity gimnasio;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutinaEntity> rutinas=new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadoEntity> estados=new ArrayList<>();
    
    public List<RutinaEntity> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<RutinaEntity> rutinas) {
        this.rutinas = rutinas;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the genero
     */
    public Boolean getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    /**
     * @return the fechaDeNacimiento
     */
    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public List<EstadoEntity> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoEntity> estados) {
        this.estados = estados;
    }

    /**
     * @param fechaDeNacimiento the fechaDeNacimiento to set
     */
    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<EntrenadorEntity> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<EntrenadorEntity> entrenadores) {
        this.entrenadores = entrenadores;
    }
}
