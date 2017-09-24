/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author js.palacios437
 */
public class ParteDelCuerpoDTO {
    private Long id;
    private String parteDelCuerpo;
    private String unidadDeMedida;

    public ParteDelCuerpoDTO()
    {
        
    }
    public ParteDelCuerpoDTO(ParteDelCuerpoEntity entity)
    {
        this.id= entity.getId();
        this.parteDelCuerpo = entity.getPartedelcuerpo();
        this.unidadDeMedida=entity.getUnidadMedida();
    }
    public ParteDelCuerpoEntity toEntity(){
        ParteDelCuerpoEntity enti = new ParteDelCuerpoEntity();
        enti.setPartedelcuerpo(this.parteDelCuerpo);
        enti.setUnidadMedida(this.unidadDeMedida);
        return enti;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParteDelCuerpo() {
        return parteDelCuerpo;
    }

    public void setParteDelCuerpo(String parteDelCuerpo) {
        this.parteDelCuerpo = parteDelCuerpo;
    }
    
    public static final List<ParteDelCuerpoDTO> listDTO(List<ParteDelCuerpoEntity> entity){
        List<ParteDelCuerpoDTO> resp=new ArrayList<>();
        for(ParteDelCuerpoEntity ent:entity){
            resp.add(new ParteDelCuerpoDTO(ent));
        }
        return resp;
    }  
}
