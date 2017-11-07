package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.robles
 */

@Entity
public class EjercicioHechoEntity extends BaseEntity implements Serializable {

    //----------------------------------------------------------------------------------------
    // -----------------------------------Atributos-------------------------------------------
    //----------------------------------------------------------------------------------------

    /**
     * Fecha en la que se realizo el ejercicioHecho.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    /**
     * Numero de series realizadas en el ejercicioHecho.
     */
    private Integer seriesReales;

    /**
     * Relacion con EjercicioInstanciaEntity.
     */
    @PodamExclude
    @ManyToOne
    private EjercicioInstanciaEntity ejercicio;    

    /**
     * Relacion con MedicionMaquinaEntity.
     */
    @PodamExclude
    @OneToMany(mappedBy = "ejercicioHecho", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedicionMaquinaEntity> medicionMaquinaEnt = new ArrayList<>();

    //----------------------------------------------------------------------------------------
    //---------------------------------Setters y Getters--------------------------------------
    //----------------------------------------------------------------------------------------

    public Integer getSeriesReales() { return seriesReales; }

    public void setSeriesReales(Integer seriesReales) { this.seriesReales = seriesReales;}

    public List<MedicionMaquinaEntity> getMedicionMaquinaEnt() {return medicionMaquinaEnt;}

    public void setMedicionMaquinaEnt(List<MedicionMaquinaEntity> medicionMaquinaEnt) { this.medicionMaquinaEnt = medicionMaquinaEnt; }

    public Date getFecha() {return fecha;}

    public void setFecha(Date fecha) {this.fecha = fecha; }

    public EjercicioInstanciaEntity getEjercicios() {return ejercicio;  }

    public void setEjercicios(EjercicioInstanciaEntity ejercicio) { this.ejercicio = ejercicio; }

    //----------------------------------------------------------------------------------------
    //---------------------------------Otros metodos.-----------------------------------------
    //----------------------------------------------------------------------------------------
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
        hash = 53 * hash + Objects.hashCode(this.fecha);
        hash = 53 * hash + Objects.hashCode(this.seriesReales);
        return hash;
    }
}
