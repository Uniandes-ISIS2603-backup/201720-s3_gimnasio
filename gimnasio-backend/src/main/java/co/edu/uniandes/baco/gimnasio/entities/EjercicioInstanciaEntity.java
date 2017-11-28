/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.bojaca
 */
@Entity
public class EjercicioInstanciaEntity extends BaseEntity implements Serializable {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * *
     * efectividad del ejercicio
     */
    private Double efectividad;
    /**
     * medida del cumplimieto del ejercicio
     */
    private Double cumplimiento;
    /**
     * la duracion en minutos del ejercicio
     */
    private Integer duracion;
    /**
     * la cantidad de series asignadas para el ejercicio
     */
    private Integer series;
    /**
     * el atributo que define la regularidad con la que hay que hacer el
     * ejercicio en dias {1="diariamente" , 7="semanalmente")
     */
    private Integer tamanioParticiones;
    /**
     * atributo que define la cantidad de veces que hay que hacer el ejercicio
     * por particion {con el atributo de tamanioParticiones define la frecuencia
     * del ejercicio}
     */
    private Integer repeticionesPorParticion;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    @PodamExclude
    @ManyToOne
    private RutinaEntity rutina;

    @PodamExclude
    @ManyToOne
    private EjercicioEntity ejercicio;
    
    /**
     * lista de objetivos del ejercicio
     */
    @PodamExclude
    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RegrecionEntity> regreciones = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EjercicioHechoEntity> ejerciciosHechos;

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public EjercicioEntity getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioEntity ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<RegrecionEntity> getRegreciones() {
        return regreciones;
    }

    public void setRegreciones(List<RegrecionEntity> regreciones) {
        this.regreciones = regreciones;
    }
    
    public List<EjercicioHechoEntity> getEjerciciosHechos() {
        return ejerciciosHechos;
    }

    public void setEjerciciosHechos(List<EjercicioHechoEntity> ejerciciosHechos) {
        this.ejerciciosHechos = ejerciciosHechos;
    }

    public Integer getTamanioParticiones() {
        return tamanioParticiones;
    }

    public Double getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(Double efectividad) {
        this.efectividad = efectividad;
    }

    public Double getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Double cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public void setTamanioParticiones(Integer tamanioParticiones) {
        this.tamanioParticiones = tamanioParticiones;
    }

    public Integer getRepeticionesPorParticion() {
        return repeticionesPorParticion;
    }

    public void setRepeticionesPorParticion(Integer repeticionesPorParticion) {
        this.repeticionesPorParticion = repeticionesPorParticion;
    }

    public RutinaEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaEntity rutina) {
        this.rutina = rutina;
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
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.duracion);
        hash = 31 * hash + Objects.hashCode(this.series);
        hash = 31 * hash + Objects.hashCode(this.tamanioParticiones);
        hash = 31 * hash + Objects.hashCode(this.repeticionesPorParticion);
        return hash;
    }
}
