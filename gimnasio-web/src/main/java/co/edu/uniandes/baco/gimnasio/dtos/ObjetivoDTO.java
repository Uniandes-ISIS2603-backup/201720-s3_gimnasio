/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class ObjetivoDTO {
    private long id;
    private String tipo;
    private String descripcion;
    
    public ObjetivoDTO(ObjetivoEntity objetivo){
        this.id=objetivo.getId();
        this.tipo=objetivo.getTipo();
        this.descripcion=objetivo.getDescripcion();
    }
    
    public ObjetivoEntity toEntity(){
        ObjetivoEntity ent=new ObjetivoEntity();
        ent.setTipo(tipo);
        ent.setDescripcion(descripcion);
        return ent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
     public static final List<ObjetivoEntity> listEntity(List<ObjetivoDTO> dtos){
        List<ObjetivoEntity> resp = new ArrayList<>();
        for(ObjetivoDTO dto:dtos){
            resp.add(dto.toEntity());
        }
        return resp;
    }
    
    public static final List<ObjetivoDTO> listDTO(List<ObjetivoEntity> entity){
        List<ObjetivoDTO> resp=new ArrayList<>();
        for(ObjetivoEntity ent:entity){
            resp.add(new ObjetivoDTO(ent));
        }
        return resp;
    }
    
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) { this.tipo = tipo;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;} 
}
