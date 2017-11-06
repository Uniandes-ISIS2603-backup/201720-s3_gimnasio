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
    //----------------------------------------------------------------------------------------
    // -----------------------------------Atributos------------------------------------------
    //----------------------------------------------------------------------------------------

    /**
     * Id del EjercicioHecho.
     */
    private long id;
    
    /**
     * Fecha en la que se realizo el EjercicioHecho.
     */
    private Date fecha;
    
    /**
     * Series que se realizaron en el EjercicioHecho.
     */
    private Integer seriesReales;

    //----------------------------------------------------------------------------------------
    //---------------------------------Constructores----------------------------------------
    //----------------------------------------------------------------------------------------

    public  EjercicioHechoDTO(){
        //JAVAX
    }
    
    public EjercicioHechoDTO(EjercicioHechoEntity entity) 
    {
        this.id = entity.getId();
        this.fecha = entity.getFecha();
        this.seriesReales = entity.getSeriesReales();
    }
    
    //----------------------------------------------------------------------------------------
    //---------------------------------Setters y Getters----------------------------------------
    //----------------------------------------------------------------------------------------
    
    public Long getId() { return id; }

    public void setId(Long id) {  this.id = id; }

    public Date getFecha(){ return fecha;}

    public void setFecha(Date fecha){ this.fecha = fecha; }

    public Integer getSeriesReales() { return seriesReales; }

    public void setSeriesReales(Integer seriesReales){ this.seriesReales = seriesReales; } 
    
    //----------------------------------------------------------------------------------------
    //-------------------------------------Metodos----------------------------------------
    //----------------------------------------------------------------------------------------
  
    public EjercicioHechoEntity toEntity()
    {
        EjercicioHechoEntity ent= new EjercicioHechoEntity();
        ent.setFecha(fecha);
        ent.setSeriesReales(seriesReales);
        
        return ent;
    }        
}
