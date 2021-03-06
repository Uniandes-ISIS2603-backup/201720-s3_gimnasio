/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t.kavanagh
 */
public class MaquinaDetailDTO extends MaquinaDTO{
    private List<TipoMedidaDTO> tipoMedida;
    private List<EjercicioDTO> ejercicios;

    public MaquinaDetailDTO() {
        super();
    }
    
    public MaquinaDetailDTO(MaquinaEntity foo) {
        super(foo);
        if (foo.getTipoMedida() != null){
            this.tipoMedida = new ArrayList<>();
            for(TipoMedidaEntity aux : foo.getTipoMedida()){
                tipoMedida.add(new TipoMedidaDTO(aux));
            }
        }
         if(foo.getEjercicios() != null){
             this.ejercicios=new ArrayList<>();
             for(EjercicioEntity aux:foo.getEjercicios()){
                 ejercicios.add(new EjercicioDTO(aux));
             }
         }
    }

    public List<TipoMedidaDTO> getTipoMedida() {
        return tipoMedida;
    }

    public List<EjercicioDTO> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioDTO> ejercicios) {
        this.ejercicios = ejercicios;
    }
    
    public void setTipoMedida(List<TipoMedidaDTO> tipoMedida) {
        this.tipoMedida = tipoMedida;
    }
    
    public static final List<MaquinaDetailDTO> listDetailDTO(List<MaquinaEntity> entity){
        List<MaquinaDetailDTO> resp=new ArrayList<>();
        for(MaquinaEntity ent:entity){
            resp.add(new MaquinaDetailDTO(ent));
        }
        return resp;
    }    
}
