/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.bojaca
 */
@Entity
public class EjercicioEntity extends BaseEntity implements Serializable{
    private String descripcion;
    private String explicacion;
    private Integer duracion;
    private Integer series;
    private Integer tamanioParticiones;
    private Integer repeticionesPorParticion;
    
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER)
    private List<ObjetivoEntity> objetivos;
     
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public Tipo getTipo(){return tipo;}
    public void setTipo(Tipo tipo){this.tipo = tipo;}
    
    public List<ObjetivoEntity> getObjetivos() {return objetivos;}
    public void setObjetivos(List<ObjetivoEntity> objetivos){this.objetivos = objetivos;}
    
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

    public String getExplicacion() {return explicacion; }
    public void setExplicacion(String explicacion) {this.explicacion = explicacion;}

    public Integer getDuracion() {return duracion;}
    public void setDuracion(Integer duracion) {this.duracion = duracion;} 

    public Integer getSeries() {return series;}
    public void setSeries(Integer series){this.series = series;}

    public Integer getTamanioParticiones() {return tamanioParticiones;}
    public void setTamanioParticiones(Integer tamanioParticiones) {this.tamanioParticiones = tamanioParticiones;}

    public Integer getRepeticionesPorParticion() {return repeticionesPorParticion;}
    public void setRepeticionesPorParticion(Integer repeticionesPorParticion) {this.repeticionesPorParticion = repeticionesPorParticion;}
}