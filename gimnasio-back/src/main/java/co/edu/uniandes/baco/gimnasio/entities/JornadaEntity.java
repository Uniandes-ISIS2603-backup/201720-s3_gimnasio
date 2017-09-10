package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 * @author jc.bojaca
 */
@Entity
public class JornadaEntity extends BaseEntity implements Serializable{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaIni;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaFin;
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Date horaIni) {
        this.horaIni = horaIni;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFIn) {
        this.horaFin = horaFIn;
    }
}
