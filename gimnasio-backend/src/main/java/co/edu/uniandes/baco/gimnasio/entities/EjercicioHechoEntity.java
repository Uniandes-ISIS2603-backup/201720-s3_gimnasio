package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author ce.robles
 */
@Entity
public class EjercicioHechoEntity extends BaseEntity implements Serializable {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * fecha en la que se hizo el ejercicio
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    /**
     * numero de series realizadas
     */
    private Integer seriesReales;
    
    private List<EjercicioEntity> ejercicios = new ArrayList<>();
    
    private List<MedicionMaquinaEntity> atributos = new ArrayList<>();
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public Integer getSeriesReales() {
        return seriesReales;
    }

    public void setSeriesReales(Integer seriesReales) {
        this.seriesReales = seriesReales;
    }
    
      public List<MedicionMaquinaEntity> getAtributos() 
    {
        return atributos;
    }
    

    public void setAtributos(List<MedicionMaquinaEntity> atributos) 
    {
        this.atributos = atributos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public List<EjercicioEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioEntity> ejercicios)
    {
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
        hash = 53 * hash + Objects.hashCode(this.fechaInicio);
        hash = 53 * hash + Objects.hashCode(this.seriesReales);
        return hash;
    }
}
