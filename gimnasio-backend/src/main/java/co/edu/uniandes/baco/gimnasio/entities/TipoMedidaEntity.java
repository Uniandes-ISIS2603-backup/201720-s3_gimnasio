package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

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
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * lista de usuarios que buscan ese objetivo
     */
    @PodamExclude
    @ManyToMany(mappedBy = "tiposMedidas")
    private List<EjercicioEntity> ejercicios = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "parte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedidaEntity> medidas;
    
    /**
     * lista de objetivos del ejercicio
     */
    @PodamExclude
    @OneToMany(mappedBy = "tipoMedida",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AtributoDeCalidadEntity> atributos = new ArrayList<>();
    
    /**
     * lista de objetivos del ejercicio
     */
    @PodamExclude
    @OneToMany(mappedBy = "tipoMedida",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedicionMaquinaEntity> medicion = new ArrayList<>();
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

    public List<EjercicioEntity> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioEntity> ejercicios) {
        this.ejercicios = ejercicios;
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

    public List<AtributoDeCalidadEntity> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributoDeCalidadEntity> atributos) {
        this.atributos = atributos;
    }

    public List<MedicionMaquinaEntity> getMedicion() {
        return medicion;
    }

    public void setMedicion(List<MedicionMaquinaEntity> medicion) {
        this.medicion = medicion;
    }

    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
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
