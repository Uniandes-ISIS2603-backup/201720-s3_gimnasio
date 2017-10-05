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

@Entity
public class EjercicioEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * la definicion especifica del ejercicio
     */
    private String explicacion;
    /**
     * la duracion en minutos del ejercicio
     */
    private Integer duracion;
    /**
     * la cantidad de series asignadas para el ejercicio
     */
    private Integer series;
    /**
     * el atributo que define la regularidad con la que hay que hacer el ejercicio en dias {1="diariamente" , 7="semanalmente")
     */
    private Integer tamanioParticiones;
    /**
     * atributo que define la cantidad de veces que hay que hacer el ejercicio por particion {con el atributo de tamanioParticiones define la frecuencia del ejercicio}
     */
    private Integer repeticionesPorParticion;
    /**
     * el tipo de ejercicio al que pertenece
     */
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * atibuto de rutina a la que pertenece
     */
    @PodamExclude
    @ManyToOne
    private RutinaEntity rutina;
    /**
     * lista de objetivos a los que el ejercicio esta orientado
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ObjetivoEntity> objetivosEjercicio = new ArrayList<>();
    /**
     * lista de maquinas usadas en ele ejercicio 
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MaquinaEntity> maquinas = new ArrayList<>();
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public RutinaEntity getRutina() {
        return rutina;
    }

    public void setRutina(RutinaEntity rutina) {
        this.rutina = rutina;
    }

    public List<ObjetivoEntity> getObjetivosEjercicio() {
        return objetivosEjercicio;
    }

    public void setObjetivosEjercicio(List<ObjetivoEntity> objetivosEjercicio) {
        this.objetivosEjercicio = objetivosEjercicio;
    }

    public List<MaquinaEntity> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaEntity> maquinas) {
        this.maquinas = maquinas;
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
        hash = 97 * hash + Objects.hashCode(this.explicacion);
        hash = 97 * hash + Objects.hashCode(this.duracion);
        hash = 97 * hash + Objects.hashCode(this.series);
        hash = 97 * hash + Objects.hashCode(this.tamanioParticiones);
        hash = 97 * hash + Objects.hashCode(this.repeticionesPorParticion);
        return hash;
    }
}
