package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author m.sicard10
 */
@Entity
public class EntrenadorEntity extends BaseEntity implements Serializable {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * nombre de el entrenador
     */
    private String nombre;

    /**
     * fecha de nacimiento del entrenador
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    /**
     * documento del entrenador
     */
    private String documento;
    
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * los usuarios que tienen asignado el entrenador
     */
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> usuarios = new ArrayList<>();
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
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
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 53 * hash + Objects.hashCode(this.documento);
        return hash;
    }
}
