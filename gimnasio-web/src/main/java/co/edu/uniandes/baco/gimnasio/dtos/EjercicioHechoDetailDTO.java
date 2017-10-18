/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce.robles
 */
public class EjercicioHechoDetailDTO extends EjercicioHechoDTO {

    private List<EjercicioDTO> ejercicios;

    public EjercicioHechoDetailDTO() {
        super();
    }

    public EjercicioHechoDetailDTO(EjercicioHechoEntity ent) {
        super(ent);
    }

    public static final List<EjercicioHechoDetailDTO> listDetailDTO(List<EjercicioHechoEntity> entity) {
        List<EjercicioHechoDetailDTO> resp = new ArrayList<>();
        for (EjercicioHechoEntity ent : entity) {
            resp.add(new EjercicioHechoDetailDTO(ent));
        }
        return resp;
    }

    public List<EjercicioDTO> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioDTO> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
