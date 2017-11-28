/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class EjercicioInstanciaDetailDTO extends EjercicioInstanciaDTO{
    private String descricpion;
    private String tipo;
    private String explicacion;
    private int num;
    private List<RegresionDTO> regrecion;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    public EjercicioInstanciaDetailDTO() {    
        super();
    }
    
    public EjercicioInstanciaDetailDTO(EjercicioInstanciaEntity entity) {
        super(entity);
        this.tipo = entity.getEjercicio().getTipo().name();
        this.explicacion = entity.getEjercicio().getExplicacion();
        this.descricpion=entity.getEjercicio().getDescripcion();
        regrecion= new ArrayList<>();
        num=entity.getRegreciones().size();
        for(RegrecionEntity x:entity.getRegreciones()){
            regrecion.add(new RegresionDTO(x));
        }
    }

    //--------------------------------------------
    // CONSTRUCTOR & LIST
    //--------------------------------------------
    public static final List<EjercicioInstanciaDetailDTO> listDetailDTO(List<EjercicioInstanciaEntity> entity) {
        List<EjercicioInstanciaDetailDTO> resp = new ArrayList<>();
        for (EjercicioInstanciaEntity ent : entity) {
            resp.add(new EjercicioInstanciaDetailDTO(ent));
        }
        return resp;
    }
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public String getDescricpion() {
        return descricpion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
        
    public void setDescricpion(String descricpion) {
        this.descricpion = descricpion;
    }

    public List<RegresionDTO> getRegrecion() {
        return regrecion;
    }

    public void setRegrecion(List<RegresionDTO> regrecion) {
        this.regrecion = regrecion;
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
}
