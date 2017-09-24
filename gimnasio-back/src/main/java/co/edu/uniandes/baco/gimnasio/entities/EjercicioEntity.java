/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    private RutinaEntity rutina;
    
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER, orphanRemoval = true)
    private List<ObjetivoEntity> objetivos=new ArrayList<>();
    
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER,orphanRemoval = true)
    private List<PartesDelCuerpoEntity> partesDelCuerpo=new ArrayList<>();
    
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER,orphanRemoval = true)
    private List<MaquinaEntity> maquinas=new ArrayList<>();
     
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public List<MaquinaEntity> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaEntity> maquinas) {
        this.maquinas = maquinas;
    }

    public RutinaEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaEntity rutina) {
        this.rutina = rutina;
    }

    public List<PartesDelCuerpoEntity> getPartesDelCuerpo() {
        return partesDelCuerpo;
    }

    public void setPartesDelCuerpo(List<PartesDelCuerpoEntity> partesDelCuerpo) {
        this.partesDelCuerpo = partesDelCuerpo;
    }
    
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

    @Override
    public boolean equals(Object obj) {
        if(!super.equals(obj))return false;
        EjercicioEntity aux=(EjercicioEntity)obj;
        return descripcion.equals(aux.descripcion) && explicacion.equals(aux.explicacion) && series.equals(aux.series) && tamanioParticiones.equals(aux.tamanioParticiones) && duracion.equals(aux.duracion) && repeticionesPorParticion.equals(aux.repeticionesPorParticion);
    }
}