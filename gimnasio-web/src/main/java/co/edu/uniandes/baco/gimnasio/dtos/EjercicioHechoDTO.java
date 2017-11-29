 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ce.robles
 */
public class EjercicioHechoDTO 
{
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
    private String fecha;
    
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
        this.fecha = format.format(entity.getFecha());
        this.seriesReales = entity.getSeriesReales();
    }
    
    //----------------------------------------------------------------------------------------
    //---------------------------------Setters y Getters----------------------------------------
    //----------------------------------------------------------------------------------------
    
    public Long getId() { return id; }

    public void setId(Long id) {  this.id = id; }

    public String getFecha(){ return fecha;}

    public void setFecha(String fecha){ this.fecha = fecha; }

    public Integer getSeriesReales() { return seriesReales; }

    public void setSeriesReales(Integer seriesReales){ this.seriesReales = seriesReales; } 
    
    //----------------------------------------------------------------------------------------
    //-------------------------------------Metodos----------------------------------------
    //----------------------------------------------------------------------------------------
  
    public EjercicioHechoEntity toEntity() throws BusinessLogicException
    {
        try {
        EjercicioHechoEntity ent= new EjercicioHechoEntity();
        ent.setFecha(format.parse(fecha));
        ent.setSeriesReales(seriesReales);
        
        return ent;
        } catch (ParseException ex) {
            Logger.getLogger(RutinaDTO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessLogicException(ex.getMessage());
        }
    }        
}
