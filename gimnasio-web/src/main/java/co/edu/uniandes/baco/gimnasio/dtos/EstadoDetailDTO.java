/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author js.palacios437
 */
public class EstadoDetailDTO extends EstadoDTO{
     private List<MedidaDetailDTO> medidas;
    
    public EstadoDetailDTO(){
        super();
    }
    
     public EstadoDetailDTO(EstadoEntity entity)
    {
        super(entity);
        if (entity != null)
        {
            medidas = new ArrayList<>();
            for(MedidaEntity e: entity.getMedidas())
            {
                medidas.add(new MedidaDetailDTO(e));
            }
        }
    }

    public static List<EstadoDetailDTO> listDetailDTO(List<EstadoEntity> entity) {
       List<EstadoDetailDTO> resp=new ArrayList<>();
        for(EstadoEntity ent:entity){
            resp.add(new EstadoDetailDTO(ent));
        }
        return resp;
    }
    
    @Override
    public EstadoEntity toEntity()
    {
        EstadoEntity e = super.toEntity();
        if (medidas != null)
        {
            List<MedidaEntity> entE = new ArrayList<>();
            for(MedidaDetailDTO d:medidas)
            {
                entE.add(d.toEntity());
            }
            e.setMedidas(entE);
        }
        return e;
    }

    public List<MedidaDetailDTO> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaDetailDTO> medidas) {
        this.medidas = medidas;
    }
}
