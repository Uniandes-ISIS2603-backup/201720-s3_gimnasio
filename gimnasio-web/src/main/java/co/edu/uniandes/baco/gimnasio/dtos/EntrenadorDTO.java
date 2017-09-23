/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import java.util.Date;
import javax.persistence.Temporal;

/**
 *
 * @author m.sicard10
 */
public class EntrenadorDTO  {
    
    private Long id;
    
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

    public EntrenadorDTO() {
       
    }

    public  EntrenadorDTO(EntrenadorEntity entity) {
        this.id = entity.getId();
        this.fechaNacimiento = entity.getFechaNacimiento();
        this.documento = entity.getDocumento();
        this.name = entity.getName();
        
    }
    
    public EntrenadorEntity toEntity()
    {
        EntrenadorEntity ent = new EntrenadorEntity();
        ent.setId(this.id);
        ent.setDocumento(this.documento);
        ent.setName(this.name);
        ent.setFechaNacimiento(this.fechaNacimiento);
        return ent;
    }

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
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
