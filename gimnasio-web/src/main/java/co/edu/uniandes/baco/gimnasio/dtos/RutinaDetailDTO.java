/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class RutinaDetailDTO extends RutinaDTO{
    private List<EjercicioDTO> ejericcios;

    public RutinaDetailDTO() {
        super();
    }

    public RutinaDetailDTO(RutinaEntity entity) {
        super(entity);
        if (entity.getEjercicios() != null){
            this.ejericcios = new ArrayList<>();
            for(EjercicioEntity aux : entity.getEjercicios()){
                ejericcios.add(new EjercicioDTO(aux));
            }
        }
    }
    
    public static final List<RutinaDetailDTO> listDetailDTO(List<RutinaEntity> entity){
        List<RutinaDetailDTO> resp=new ArrayList<>();
        for(RutinaEntity ent:entity){
            resp.add(new RutinaDetailDTO(ent));
        }
        return resp;
    }

    public List<EjercicioDTO> getEjericcios() {
        return ejericcios;
    }

    public void setEjericcios(List<EjercicioDTO> ejericcios) {
        this.ejericcios = ejericcios;
    } 
}
