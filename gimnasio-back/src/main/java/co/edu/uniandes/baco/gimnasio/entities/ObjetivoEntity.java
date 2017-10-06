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
 * @author jc.bojaca
 */
@Entity
public class ObjetivoEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * la palabra que define el objetivo
     */
    String tipo;
    /**
     * descripcion detallada del objetivo
     */
    String descripcion;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    
    /**
     * lista de usuarios que buscan ese objetivo
     */
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> usuarios = new ArrayList<>();
     /**
     * lista de objetivos del ejercicio
     */
    @PodamExclude
    @OneToMany(mappedBy = "objetivo",cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AtributoDeCalidadEntity> atributos = new ArrayList<>();

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AtributoDeCalidadEntity> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributoDeCalidadEntity> atributos) {
        this.atributos = atributos;
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
        hash = 47 * hash + Objects.hashCode(this.tipo);
        hash = 47 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }
}
