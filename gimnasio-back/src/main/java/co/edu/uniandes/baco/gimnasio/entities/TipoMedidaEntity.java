package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * @author t.kavanagh
 *
 */
@Entity
public class TipoMedidaEntity extends BaseEntity implements Serializable {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * atributo que modela la descripcion del tipod e medida
     */
    private String tipoMedida;
    /**
     * atributo que modela la unidad de la medida ej:cm,m,precion
     */
    private String unidad;
    /**
     * atributo que modela si la medida es automatica o no
     */
    private boolean automatico;

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    /**
     * metodo get de automatico
     *
     * @return el balor de automatico
     */
    public boolean isAutomatico() {
        return automatico;
    }

    /**
     * metodo set de atomatico
     *
     * @param automatico valor booleano que determina si es automatico
     */
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    /**
     * metodo get de UNidad
     *
     * @return la unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * metodo set de la unidad
     *
     * @param unidad tipo de unidad
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
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
        hash = 47 * hash + Objects.hashCode(this.tipoMedida);
        hash = 47 * hash + Objects.hashCode(this.unidad);
        return hash;
    }
}
