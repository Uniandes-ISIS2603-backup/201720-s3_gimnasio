package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Date;
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
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public Integer getSeriesReales() {
        return seriesReales;
    }

    public void setSeriesReales(Integer seriesReales) {
        this.seriesReales = seriesReales;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
        hash = 53 * hash + Objects.hashCode(this.fechaInicio);
        hash = 53 * hash + Objects.hashCode(this.seriesReales);
        return hash;
    }
}
