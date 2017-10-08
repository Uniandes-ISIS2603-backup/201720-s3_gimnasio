/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import java.util.Date;

/**
 *
 * @author m.sicard10
 */
public class EntrenadorDTO  {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private Long id;
    private String name;
    private Date fechaNacimiento;
    private String documento;

    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY
    //--------------------------------------------
    
    /**
     * constructor vacio
     */
    public EntrenadorDTO() {
       //Empty constructor
    }
    /**
     * metodo que genera un dto de un entity (Constructor)
     * @param entity 
     */
    public  EntrenadorDTO(EntrenadorEntity entity) {
        this.id = entity.getId();
        this.fechaNacimiento = entity.getFechaNacimiento();
        this.documento = entity.getDocumento();
        this.name = entity.getNombre();
        
    }
    /**
     * genera una entidad de tipo entrenador
     * @return entrenador
     */
    public EntrenadorEntity toEntity()
    {
        EntrenadorEntity ent = new EntrenadorEntity();
        ent.setId(this.id);
        ent.setDocumento(this.documento);
        ent.setNombre(this.name);
        ent.setFechaNacimiento(this.fechaNacimiento);
        return ent;
    }

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    
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
