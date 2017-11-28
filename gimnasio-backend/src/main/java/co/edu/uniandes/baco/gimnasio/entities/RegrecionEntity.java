package co.edu.uniandes.baco.gimnasio.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class RegrecionEntity extends BaseEntity {

    //--------------------------------------------
    // DATOS BASE
    //-------------------------------------------
    private Double regresion;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * objetivo al que pertenece
     */
    @PodamExclude
    @ManyToOne
    private EjercicioInstanciaEntity ejercicio;
    /**
     * atributo que mide
     */
    @PodamExclude
    @ManyToOne
    private TipoMedidaEntity tipoMedida;

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public double getRegresion() {
        return regresion;
    }

    public void setRegresion(double regresion) {
        this.regresion = regresion;
    }

    public TipoMedidaEntity getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(TipoMedidaEntity tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public EjercicioInstanciaEntity getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioInstanciaEntity ejercicio) {
        this.ejercicio = ejercicio;
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
        hash = (int) (53 * hash + this.regresion);
        return hash;
    }
}
