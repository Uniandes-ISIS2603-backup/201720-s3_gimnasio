/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;
/**
 *
 * @author jc.bojaca
 */
public class EjercicioEfectivo{
    private Double efectividad;
    private Long id;
    private String tipo;
    private String explicacion;
    private String descricpion;

    public EjercicioEfectivo() {
        //cdsas
    }

    public EjercicioEfectivo(Double efectividad, Long id, String tipo, String explicacion, String descricpion) {
        this.efectividad = efectividad;
        this.id = id;
        this.tipo = tipo;
        this.explicacion = explicacion;
        this.descricpion = descricpion;
    }
    
    public Double getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(Double efectividad) {
        this.efectividad = efectividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getDescricpion() {
        return descricpion;
    }

    public void setDescricpion(String descricpion) {
        this.descricpion = descricpion;
    }
    
    
    
}
