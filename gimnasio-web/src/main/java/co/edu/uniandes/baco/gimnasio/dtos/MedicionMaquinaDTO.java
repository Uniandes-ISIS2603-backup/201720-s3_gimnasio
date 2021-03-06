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
    //----------------------------------------------------------------------------------------
    //------------------------------------Atributos------------------------------------------
    //----------------------------------------------------------------------------------------

    /**
     * Id de la medicionMaquina.
     */
    private Long id;
    
    /**
     * Medicion de la maquina.
     */
    private Double medicionMaquina;
       
    //----------------------------------------------------------------------------------------
    //------------------------------------Constructores---------------------------------------
    //----------------------------------------------------------------------------------------

    public MedicionMaquinaDTO() 
    {
       //JAVAX
    }
    
    public MedicionMaquinaDTO(MedicionMaquinaEntity entity) 
    {
        this.id = entity.getId();
        this.medicionMaquina = entity.getMedicionManquina();
    }
    
    //----------------------------------------------------------------------------------------
    //------------------------------------Setters y Getters-----------------------------------
    //----------------------------------------------------------------------------------------
    
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Double getMedicionMaquina() { return medicionMaquina; }

    public void setMedicionMaquina(Double medicionMaquina) { this.medicionMaquina = medicionMaquina; }
    
    //----------------------------------------------------------------------------------------
    //------------------------------------------Metodos---------------------------------------
    //----------------------------------------------------------------------------------------
   
    public MedicionMaquinaEntity toEntity()
    {
        MedicionMaquinaEntity ent= new MedicionMaquinaEntity();
        ent.setMedicionManquina(medicionMaquina);
        
        return ent;
    }
    
    public static final  List<MedicionMaquinaEntity> listEntity(List<MedicionMaquinaDTO> dtos)
    {
        List<MedicionMaquinaEntity> resp = new ArrayList<>();
        
        for(MedicionMaquinaDTO i:dtos)
            resp.add(i.toEntity());
        return resp;
    }
    
}
