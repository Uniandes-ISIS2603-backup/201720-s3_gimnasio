/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class EjercicioDetailDTO extends EjercicioDTO{
    private List<ObjetivoDTO> objetivos;

    public EjercicioDetailDTO(EjercicioEntity entity) {
        super(entity);
        if (entity.getObjetivos() != null){
            this.objetivos = new ArrayList<>();
            for(ObjetivoEntity aux : entity.getObjetivos()){
                objetivos.add(new ObjetivoDTO(aux));
            }
        }
    }

    public static final List<EjercicioDetailDTO> listDetailDTO(List<EjercicioEntity> entity){
        List<EjercicioDetailDTO> resp=new ArrayList<>();
        for(EjercicioEntity ent:entity){
            resp.add(new EjercicioDetailDTO((ent)));
        }
        return resp;
    }
    

    public List<ObjetivoDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoDTO> objetivos) {
        this.objetivos = objetivos;
    }
}
