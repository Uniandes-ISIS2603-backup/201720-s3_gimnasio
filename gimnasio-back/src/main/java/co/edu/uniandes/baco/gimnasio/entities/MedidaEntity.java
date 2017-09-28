package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.palacios437
 */
@Entity
public class MedidaEntity extends BaseEntity implements Serializable {

    Double medida;  // atributo que modela una medida

    @PodamExclude
    @ManyToOne
    private EstadoEntity estado;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private TipoMedidaEntity parte;

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public TipoMedidaEntity getParte() {
        return parte;
    }

    public void setParte(TipoMedidaEntity parte) {
        this.parte = parte;
    }

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }
}
