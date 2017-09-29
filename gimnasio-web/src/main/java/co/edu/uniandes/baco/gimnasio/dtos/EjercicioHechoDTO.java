/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ce.robles
 */
public class EjercicioHechoDTO 
{
    private Long id;
    private Date fecha;
    private Integer seriesReales;

    public EjercicioHechoDTO(EjercicioHechoEntity entity) 
    {
        this.id = entity.getId();
        this.fecha = entity.getFechaInicio();
        this.seriesReales = entity.getSeriesReales();
    }
    /**
     * convierte un dto a entity
     * @return 
     */
    public EjercicioHechoEntity toEntity()
    {
        EjercicioHechoEntity ent= new EjercicioHechoEntity();
        ent.setFechaInicio(fecha);
        ent.setSeriesReales(seriesReales);
        
        return ent;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Date getFecha() 
    {
        return fecha;
    }

    public void setFecha(Date fecha) 
    {
        this.fecha = fecha;
    }

    public Integer getSeriesReales() 
    {
        return seriesReales;
    }

    public void setSeriesReales(Integer seriesReales) 
    {
        this.seriesReales = seriesReales;
    }  
    
       
    public final static List<EjercicioHechoEntity> listEntity(List<EjercicioHechoDTO> dtos)
    {
        List<EjercicioHechoEntity> resp = new ArrayList<>();
        
        dtos.forEach((dto) -> 
        {
            resp.add(dto.toEntity());
        });
        return resp;
    }
    
    public final static List<EjercicioHechoDTO> listDTO(List<EjercicioHechoEntity> entity)
    {
        List<EjercicioHechoDTO> resp =new ArrayList<>();
        entity.forEach((ent) -> {
            resp.add(new EjercicioHechoDTO(ent));
        });
        return resp;
    }
    
}
