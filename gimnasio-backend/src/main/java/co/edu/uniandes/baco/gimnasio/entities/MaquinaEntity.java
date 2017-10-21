package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author t.kavanagh
 */
@Entity
public class MaquinaEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    
    /**
     * informacion de la maquina
     */
    private String informacion;
    
    //--------------------------------------------
    // DATOS Entity
    //--------------------------------------------
    
    /**
     * tipos de medida que puede tomar la maquina al ser usada
     */
    @PodamExclude
    @ManyToMany
    private List<TipoMedidaEntity> tipoMedida;
    
    /**
     * lista de usuarios que buscan ese objetivo
     */
    @PodamExclude
    @ManyToMany(mappedBy = "maquinas")
    private List<EjercicioEntity> ejercicios = new ArrayList<>();
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
    
    public List<EjercicioEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioEntity> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<TipoMedidaEntity> getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(List<TipoMedidaEntity> tipoMedida) {
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
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.informacion);
        return hash;
    }
}
