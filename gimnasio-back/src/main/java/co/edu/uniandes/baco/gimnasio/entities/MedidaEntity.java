package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.palacios437
 */
@Entity
public class MedidaEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    
    /**
     * atributo que modela una medida
     */
    Double medida; 
    
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    
    /**
     * estado al que pertenece la medida
     */
    @PodamExclude
    @ManyToOne
    private EstadoEntity estado;

    /**
     * dato que mide la medida
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.REFRESH)
    private TipoMedidaEntity parte;

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    
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
        hash = 97 * hash + Objects.hashCode(this.medida);
        return hash;
    }
}
