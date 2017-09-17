/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.grupo.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import java.util.Date;

/**
 *
 * @author js.palacios437
 */
public class EstadoDetailDTO extends EstadoDTO{
    
    private Date fecha;
    private Double peso;
    private Double presionSan;
    private Integer ritmocardiaco;
    
    public EstadoDetailDTO()
    {
        
    }
    
    public EstadoDetailDTO(EstadoEntity entity)
    {
        super(entity);
        this.fecha = entity.getFecha();
        this.peso = entity.getPeso();
        this.presionSan = entity.getPresionSanguinea();
       // this.ritmocardiaco = entity.getRitmeoCardiaco();
        
    }
}
