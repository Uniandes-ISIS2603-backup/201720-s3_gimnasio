/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class ObjetivoDTO {
    String tipo;
    String descripcion;

    public ObjetivoDTO() {}
    
    public ObjetivoDTO(ObjetivoEntity objetivo){
        this.tipo=objetivo.getTipo();
        this.descripcion=objetivo.getDescripcion();
    }
    
    public ObjetivoEntity toEntity(){
        ObjetivoEntity ent=new ObjetivoEntity();
        ent.setTipo(tipo);
        ent.setDescripcion(descripcion);
        return ent;
    }
    
     public final static List<ObjetivoEntity> listEntity(List<ObjetivoDTO> dtos){
        List<ObjetivoEntity> resp = new ArrayList<>();
        dtos.forEach((dto) -> {
            resp.add(dto.toEntity());
        });
        return resp;
    }
    
    public final static List<ObjetivoDTO> listDTO(List<ObjetivoEntity> entity){
        List<ObjetivoDTO> resp=new ArrayList<>();
        entity.forEach((ent) -> {
            resp.add(new ObjetivoDTO(ent));
        });
        return resp;
    }
    
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) { this.tipo = tipo;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;} 
}
