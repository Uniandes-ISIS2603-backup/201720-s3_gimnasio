/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
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
    }
    public static final List<ObjetivoDetailDTO> listDetailDTO(List<ObjetivoEntity> entity) {
        List<ObjetivoDetailDTO> resp = new ArrayList<>();
        for (ObjetivoEntity ent : entity) {
            resp.add(new ObjetivoDetailDTO((ent)));
        }
        return resp;
    }
     //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
