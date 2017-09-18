/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author jc.bojaca
 */
@Entity
public class EjercicioEntity extends BaseEntity implements Serializable{
    private ArrayList<String> objetivos;
    private String tipo;
    private String descripcion;
    private String explicacion;
    private Integer duracion;
    private Integer series;
    private Integer tamanioParticiones;
    private Integer repeticionesPorParticion;
    
    public ArrayList<String> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(ArrayList<String> objetivos) {
        this.objetivos = objetivos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

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

    public Integer getTamanioParticiones() {
        return tamanioParticiones;
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
}
