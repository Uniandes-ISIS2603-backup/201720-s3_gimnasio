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
import javax.persistence.Temporal;

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
    
    @ManyToMany(mappedBy = "usuarios")
    private List<EntrenadorEntity> entrenadores = new ArrayList();

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