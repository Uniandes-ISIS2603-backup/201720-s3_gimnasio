package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author jc.bojaca
 */
@Entity
public class EjercicioHechoEntity extends BaseEntity implements Serializable{
    private int series;
      
    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
