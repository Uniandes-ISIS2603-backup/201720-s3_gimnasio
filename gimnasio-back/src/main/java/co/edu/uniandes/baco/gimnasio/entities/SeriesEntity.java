/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;
import javax.persistence.Entity;
import java.io.Serializable;
/**
 *
 * @author jp.romero12
 */
@Entity
public class SeriesEntity extends BaseEntity implements Serializable{
    
    private int series;

    /**
     * @return the series
     */
    public int getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(int series) {
        this.series = series;
    }
    
}
