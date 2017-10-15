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

    public RutinaDetailDTO() {
        super();
    }

    public RutinaDetailDTO(RutinaEntity entity) {
        super(entity);
    }
    
    public static final List<RutinaDetailDTO> listDetailDTO(List<RutinaEntity> entity){
        List<RutinaDetailDTO> resp=new ArrayList<>();
        for(RutinaEntity ent:entity){
            resp.add(new RutinaDetailDTO(ent));
        }
        return resp;
    }
}
