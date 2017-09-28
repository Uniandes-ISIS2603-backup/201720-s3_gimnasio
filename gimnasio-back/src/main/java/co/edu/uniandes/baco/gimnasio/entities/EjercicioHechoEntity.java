package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author ce.robles
 */
@Entity
public class EjercicioHechoEntity extends BaseEntity implements Serializable {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;

    private Integer seriesReales;

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
}
