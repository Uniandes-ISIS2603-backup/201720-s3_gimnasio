/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo
 */
public class MedidaDetailDTO extends MedidaDTO {
    private String descripcion;
    private String unidad;

    public MedidaDetailDTO() {
        super();
    }

    public MedidaDetailDTO(MedidaEntity entity) {
        super(entity);
        if (entity.getParte() != null) {
            this.descripcion = entity.getParte().getTipoMedida();
            this.unidad = entity.getParte().getUnidad();
        }
    }

    public static final List<MedidaDetailDTO> listDTO(List<MedidaEntity> entity) {
        List<MedidaDetailDTO> resp = new ArrayList<>();
        for (MedidaEntity ent : entity) {
            resp.add(new MedidaDetailDTO(ent));
        }
        return resp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
