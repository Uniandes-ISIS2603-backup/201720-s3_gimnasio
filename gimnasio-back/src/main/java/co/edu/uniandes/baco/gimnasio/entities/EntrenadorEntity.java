/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Date;

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
    private Date fechaNacimiento;
    
    /**
     * documento del entrenador
     */
    private String documento;
    
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
    
    
    
    
    
}
