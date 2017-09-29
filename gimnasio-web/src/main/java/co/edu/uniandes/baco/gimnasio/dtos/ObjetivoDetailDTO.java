/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo
 */
public class ObjetivoDetailDTO extends ObjetivoDTO{
    
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    private List<UsuarioDTO> usuarios;
    private List<AtributoDeCalidadDetailDTO> calidad;
    
    //--------------------------------------------
    // CONSTRUCTOR & LIST
    //--------------------------------------------

    public ObjetivoDetailDTO() {
        super();
    }

    public ObjetivoDetailDTO(ObjetivoEntity objetivo) {
        super(objetivo);
         if (objetivo.getUsuarios() != null) {
            this.usuarios = new ArrayList<>();
            for (UsuarioEntity aux : objetivo.getUsuarios()) {
                usuarios.add(new UsuarioDTO(aux));
            }
        }
         if(objetivo.getAtributos() != null){
             this.calidad=new ArrayList<>();
             for(AtributoDeCalidadEntity aux:objetivo.getAtributos()){
                 calidad.add(new AtributoDeCalidadDetailDTO(aux));
             }
         }
    }
    public static final List<ObjetivoDetailDTO> listDetailDTO(List<ObjetivoEntity> entity) {
        List<ObjetivoDetailDTO> resp = new ArrayList<>();
        for (ObjetivoEntity ent : entity) {
            resp.add(new ObjetivoDetailDTO(ent));
        }
        return resp;
    }
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public List<AtributoDeCalidadDetailDTO> getCalidad() {
        return calidad;
    }

    public void setCalidad(List<AtributoDeCalidadDetailDTO> calidad) {
        this.calidad = calidad;
    }
    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }  
}
