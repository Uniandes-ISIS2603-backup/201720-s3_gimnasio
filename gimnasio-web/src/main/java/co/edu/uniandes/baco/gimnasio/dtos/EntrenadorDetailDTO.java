/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import com.gs.collections.impl.list.fixed.AbstractArrayAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m.sicard10
 */
public class EntrenadorDetailDTO extends EntrenadorDTO{
    //------------------------
    //atributos
    //------------------------
    private List <UsuarioDTO> ususrios;
    //------------------------
    //metodos
    //------------------------
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
        if (entity.getUsuarios() != null)
        {
            
            this.ususrios = new ArrayList<UsuarioDTO>();
            for(UsuarioEntity u : entity.getUsuarios())
            {
                ususrios.add(new UsuarioDTO(u));
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public EntrenadorEntity toEntity() {
        EntrenadorEntity e = super.toEntity();
        if (getUsusrios() != null)
        {
            List <UsuarioEntity> ususariosEntity = new ArrayList<UsuarioEntity>();
            for(UsuarioDTO u : ususrios)
            {
                ususariosEntity.add(u.toEntity());
            }
            e.setUsuarios(ususariosEntity);
        }
        return e;
    }

    public List<UsuarioDTO> getUsusrios() {
        return ususrios;
    }

    public void setUsusrios(List<UsuarioDTO> ususrios) {
        this.ususrios = ususrios;
    }
    
    
}
