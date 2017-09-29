package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.bojaca
 */
@Entity
public class EjercicioEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * atributo descripcion
     */
    private String descripcion;
    /**
     * atributo explicacion
     */
    private String explicacion;
    /**
     * atributo duracion
     */
    private Integer duracion;
    /**
     * atributo series
     */
    private Integer series;
    /**
     * atributos tamañoPrticnes
     */
    private Integer tamanioParticiones;
    /**
     * atributo repeticones
     */
    private Integer repeticionesPorParticion;
    /**
     * atributo tipo
     */
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * atibuto de rutina a la que pertenese
     */
    @PodamExclude
    @ManyToOne
    private RutinaEntity rutina;
    /**
     * lista de objetivos del ejercicio
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ObjetivoEntity> objetivos = new ArrayList<>();
    /**
     * lista de maquians del ejercicio
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MaquinaEntity> maquinas = new ArrayList<>();
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public List<MaquinaEntity> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaEntity> maquinas) {
        this.maquinas = maquinas;
    }

    public RutinaEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaEntity rutina) {
        this.rutina = rutina;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<ObjetivoEntity> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoEntity> objetivos) {
        this.objetivos = objetivos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getTamanioParticiones() {
        return tamanioParticiones;
    }

    public void setTamanioParticiones(Integer tamanioParticiones) {
        this.tamanioParticiones = tamanioParticiones;
    }

    public Integer getRepeticionesPorParticion() {
        return repeticionesPorParticion;
    }

    public void setRepeticionesPorParticion(Integer repeticionesPorParticion) {
        this.repeticionesPorParticion = repeticionesPorParticion;
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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        hash = 97 * hash + Objects.hashCode(this.explicacion);
        hash = 97 * hash + Objects.hashCode(this.duracion);
        hash = 97 * hash + Objects.hashCode(this.series);
        hash = 97 * hash + Objects.hashCode(this.tamanioParticiones);
        hash = 97 * hash + Objects.hashCode(this.repeticionesPorParticion);
        return hash;
    }
}
