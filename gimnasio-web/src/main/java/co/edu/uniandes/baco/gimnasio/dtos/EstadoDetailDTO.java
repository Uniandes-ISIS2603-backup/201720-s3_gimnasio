/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import java.util.Date;

/**
 *
 * @author js.palacios437
 */
public class EstadoDetailDTO extends EstadoDTO{
    
    private Date fecha;
   private Double peso; // atributo que modela el peso
   private Double presionSanguinea; // atributo que modela la presion sanguienea
   private Integer ritmeoCardiaco; // atributo que modela el ritmo cardiaco
    public EstadoDetailDTO()
    {
        
    }
    
    public EstadoDetailDTO(EstadoEntity entity)
    {
        super(entity);
        this.fecha = entity.getFecha();
        this.peso = entity.getPeso();
       this.ritmeoCardiaco = entity.getRitmeoCardiaco();
       this.presionSanguinea = entity.getPresionSanguinea();
        
    }
}
