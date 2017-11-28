/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class RegresionDTO {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------

    private Long id;
    private double regresion;
    private String descripcion;
    private String unidad;
    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY 
    //--------------------------------------------

    public RegresionDTO() {
        //JAVAXS
    }

    public RegresionDTO(RegrecionEntity entity) {
        this.regresion = entity.getRegresion();
        this.id = entity.getId();
        if (entity.getTipoMedida() != null) {
            this.descripcion = entity.getTipoMedida().getTipoMedida();
            this.unidad = entity.getTipoMedida().getUnidad();
        }
    }

    public static final List<RegresionDTO> listDTO(List<RegrecionEntity> entity) {
        List<RegresionDTO> resp = new ArrayList<>();
        for (RegrecionEntity ent : entity) {
            resp.add(new RegresionDTO(ent));
        }
        return resp;
    }
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getRegresion() {
        return regresion;
    }

    public void setRegresion(double regresion) {
        this.regresion = regresion;
    }
}
