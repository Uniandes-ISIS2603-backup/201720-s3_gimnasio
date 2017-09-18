/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;

/**
 *
 * @author m.sicard10
 */
public class EntrenadorDetailDTO extends EntrenadorDTO{
    /**
     * Constructor por defecto
     */
    public EntrenadorDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EntrenadorDetailDTO(EntrenadorEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public EntrenadorEntity toEntity() {
        EntrenadorEntity e = super.toEntity();
        return e;
    }
}
