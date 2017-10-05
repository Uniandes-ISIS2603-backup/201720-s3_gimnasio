package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.palacios437
 */
@Entity
public class EstadoEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * fecha en la que tomo la medida
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * el conjunto de medidas tomadas para ese estado
     */
    @PodamExclude
    @OneToMany(mappedBy = "estado", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedidaEntity> medidas;
    /**
     *  el usuario al que le pertenecen las medidas
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.medidas);
        hash = 29 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

}
