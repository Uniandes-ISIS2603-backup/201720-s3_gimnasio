/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc.bojaca
 */
public class RutinaDTO {

    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private Long id;
    private Double cumplimiento;
    private String fechaInicio;
    private String fechaFinal;

    public RutinaDTO() {
        //javaxs
    }

    public RutinaDTO(RutinaEntity entity) {
        id = entity.getId();
        fechaInicio = format.format(entity.getFechaInicio());
        fechaFinal = format.format(entity.getFechaFinal());
        cumplimiento = entity.getCumplimiento();
    }

    public RutinaEntity toEntity() throws BusinessLogicException {
        try {
            RutinaEntity ent = new RutinaEntity();
            ent.setFechaFinal(format.parse(fechaFinal));
            ent.setCumplimiento(0.0);
            return ent;
        } catch (ParseException ex) {
            Logger.getLogger(RutinaDTO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessLogicException(ex.getMessage());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Double getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Double cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
