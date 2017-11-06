package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
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
     *
     */
    private String descripcion;
    /**
     * el tipo de ejercicio al que pertenece
     */
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * lista de objetivos a los que el ejercicio esta orientado
     */
    @PodamExclude
    @ManyToMany
    private List<ObjetivoEntity> objetivosEjercicio = new ArrayList<>();
    /**
     * lista de maquinas usadas en ele ejercicio
     */
    @PodamExclude
    @ManyToMany
    private List<MaquinaEntity> maquinas = new ArrayList<>();
    /**
     * lista de las partes del cuerpo/ medidciones que afecta
     */
    @PodamExclude
    @ManyToMany
    private List<TipoMedidaEntity> tiposMedidas = new ArrayList<>();
    
    /**
     * referencias al ejercicio en las rutinas especificas
     */
    @PodamExclude
    @OneToMany(mappedBy = "ejercicio", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EjercicioInstanciaEntity> instancias = new ArrayList<>();
    
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TipoMedidaEntity> getTiposMedidas() {
        return tiposMedidas;
    }

    public void setTiposMedidas(List<TipoMedidaEntity> tiposMedidas) {
        this.tiposMedidas = tiposMedidas;
    }

    public List<EjercicioInstanciaEntity> getInstancias() {
        return instancias;
    }

    public void setInstancias(List<EjercicioInstanciaEntity> instancias) {
        this.instancias = instancias;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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
        hash = 59 * hash + Objects.hashCode(this.explicacion);
        hash = 59 * hash + Objects.hashCode(this.descripcion);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        return hash;
    }
}
