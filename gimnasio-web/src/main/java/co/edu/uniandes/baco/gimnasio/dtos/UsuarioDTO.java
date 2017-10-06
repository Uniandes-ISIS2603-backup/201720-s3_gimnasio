/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.Date;

/**
 *
 * @author m.sicard10
 */
public class UsuarioDTO {
    //---------------
    //atributos
    //---------------
    private long id;
    private String nombre;
    private Boolean genero;
    private Date fechaDeNacimiento;
    
    //----------------
    //Metodos
    //----------------

    /**
     * Constructor por defecto
     */
    public UsuarioDTO(){
    }
    
    public UsuarioDTO(UsuarioEntity u) {
        if (u!= null)
        {
            id = u.getId();
            nombre = u.getNombre();
            genero = u.isGenero();
            fechaDeNacimiento = u.getFechaDeNacimiento();
        }
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setFechaDeNacimiento(fechaDeNacimiento);
        nuevo.setGenero(genero);
        nuevo.setNombre(nombre);
        return nuevo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    
}
