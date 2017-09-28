/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author m.sicard10
 */
@Entity
public class EntrenadorEntity extends BaseEntity implements Serializable {
    /**
     * nombre de el entrenador
     */
    private String name;
    
    /**
     * fecha de nacimiento del entrenador
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    /**
     * documento del entrenador
     */
    private String documento;
    
    @PodamExclude
    @ManyToMany (cascade = CascadeType.REFRESH)
    private List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
   //--------------------------------------------------------------------------
   //METODOS
   //--------------------------------------------------------------------------
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
