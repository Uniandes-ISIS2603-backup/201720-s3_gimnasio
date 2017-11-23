/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;

/**
 *
 * @author jc.bojaca
 */
public class EjercicioInstanciaDTO{
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private Double efectividad;
    private Double cumplimiento;
    private Integer duracion;
    private Long id;
    private Integer series;
    private Integer tamanioParticiones;
    private Integer repeticionesPorParticion;
    
    
    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY
    //--------------------------------------------
    public EjercicioInstanciaDTO() {
        //javax
    }
    
    public EjercicioInstanciaDTO(EjercicioInstanciaEntity entity){
        this.id=entity.getId();
        efectividad=entity.getEfectividad();
        cumplimiento=entity.getCumplimiento();
        duracion=entity.getDuracion();
        series=entity.getSeries();
        tamanioParticiones=entity.getTamanioParticiones();
        repeticionesPorParticion=entity.getRepeticionesPorParticion();
    }
    
    public EjercicioInstanciaEntity toEntity(){
        EjercicioInstanciaEntity ent=new EjercicioInstanciaEntity();
        ent.setDuracion(duracion);
        ent.setRepeticionesPorParticion(repeticionesPorParticion);
        ent.setSeries(series);
        ent.setTamanioParticiones(tamanioParticiones);
        ent.setCumplimiento(0.0);
        ent.setEfectividad(0.0);
        return ent;
    }
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public Double getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(Double efectividad) {
        this.efectividad = efectividad;
    }

    public Double getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Double cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getTamanioParticiones() {
        return tamanioParticiones;
    }

    public void setTamanioParticiones(Integer tamanioParticiones) {
        this.tamanioParticiones = tamanioParticiones;
    }

    public Integer getRepeticionesPorParticion() {
        return repeticionesPorParticion;
    }

    public void setRepeticionesPorParticion(Integer repeticionesPorParticion) {
        this.repeticionesPorParticion = repeticionesPorParticion;
    }
}
