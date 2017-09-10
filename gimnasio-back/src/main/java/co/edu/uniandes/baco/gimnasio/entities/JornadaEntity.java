package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 * @author jc.bojaca
 */
@Entity
public class JornadaEntity extends BaseEntity implements Serializable{
    private Date fechaIni;
    private Date fechaFIn;
    
    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFIn() {
        return fechaFIn;
    }

    public void setFechaFIn(Date fechaFIn) {
        this.fechaFIn = fechaFIn;
    }
}
