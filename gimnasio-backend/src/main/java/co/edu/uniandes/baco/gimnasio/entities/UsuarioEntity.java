package co.edu.uniandes.baco.gimnasio.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.romero12
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * nombre del usuario
     */
    private String nombre;
    /**
     * genero del usuario
     */
    private boolean genero;
    /**
     * fecha de nacimiento del usuario
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeNacimiento;

    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    
    /**
     *  los entrenadores asginados ael usuario
     */
    @PodamExclude
    @ManyToMany(mappedBy = "usuarios")
    private List<EntrenadorEntity> entrenadores = new ArrayList();

    /**
     * los objetivos del usuario
     */
    @PodamExclude
    @ManyToMany(mappedBy = "usuarios")
    private List<ObjetivoEntity> objetivos = new ArrayList<>();

    /**
     * las rutinas del usuario
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutinaEntity> rutinas = new ArrayList<>();

    /**
     * el conjuto de todos los estados del usuario(historico de medidas)
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadoEntity> estados = new ArrayList<>();
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<EntrenadorEntity> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<EntrenadorEntity> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public List<ObjetivoEntity> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoEntity> objetivos) {
        this.objetivos = objetivos;
    }

    public List<RutinaEntity> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<RutinaEntity> rutinas) {
        this.rutinas = rutinas;
    }

    public List<EstadoEntity> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoEntity> estados) {
        this.estados = estados;
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
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.fechaDeNacimiento);
        return hash;
    }
    
}
