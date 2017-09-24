package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ce.robles
 */
public class MedicionMaquinaDTO 
{
    private Long id;
    private Double medicionMaquina;
    
    public MedicionMaquinaDTO(MedicionMaquinaEntity entity) 
    {
        this.id = entity.getId();
        this.medicionMaquina = entity.getMedicionManquina();
    }
    
    public MedicionMaquinaEntity toEntity()
    {
        MedicionMaquinaEntity ent= new MedicionMaquinaEntity();
        ent.setMedicionManquina(medicionMaquina);
        
        return ent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMedicionMaquina() {
        return medicionMaquina;
    }

    public void setMedicionMaquina(Double medicionMaquina) {
        this.medicionMaquina = medicionMaquina;
    }
    
    public final static List<MedicionMaquinaEntity> listEntity(List<MedicionMaquinaDTO> dtos)
    {
        List<MedicionMaquinaEntity> resp = new ArrayList<>();
        
        dtos.forEach((dto) -> 
        {
            resp.add(dto.toEntity());
        });
        return resp;
    }
    
    public final static List<MedicionMaquinaDTO> listDTO(List<MedicionMaquinaEntity> entity)
    {
        List<MedicionMaquinaDTO> resp =new ArrayList<>();
        entity.forEach((ent) -> {
            resp.add(new MedicionMaquinaDTO(ent));
        });
        return resp;
    }
}
