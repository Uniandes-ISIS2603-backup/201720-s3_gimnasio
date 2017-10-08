/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m.sicard10
 */
public class EntrenadorDetailDTO extends EntrenadorDTO{
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    private List <UsuarioDTO> usuarios;
    //--------------------------------------------
    // CONSTRUCTOR & LIST
    //--------------------------------------------
    /**
     * Constructor por defecto
     */
    public EntrenadorDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EntrenadorDetailDTO(EntrenadorEntity entity) {
        super(entity);
        if (entity.getUsuarios() != null){
            this.usuarios = new ArrayList<>();
            for(UsuarioEntity u : entity.getUsuarios())
            {
                usuarios.add(new UsuarioDTO(u));
            }
        }
    }

    public static final List<EntrenadorDetailDTO> listDetailDTO(List<EntrenadorEntity> entity) {
        List<EntrenadorDetailDTO> resp = new ArrayList<>();
        for (EntrenadorEntity ent : entity) {
            resp.add(new EntrenadorDetailDTO(ent));
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
