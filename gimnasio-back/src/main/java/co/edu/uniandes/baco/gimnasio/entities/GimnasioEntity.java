package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ISIS2603
 */
@Entity
public class GimnasioEntity extends BaseEntity implements Serializable {

    /**
     * nombre del gimnasio
     */
    private String name;

    /**
     * nombre del duenio
     */
    private String duenio;

    /**
     * nit de la empresa
     */
    private long nit;
    //--------------------------------------------------------------------------
    //METODOS
    //--------------------------------------------------------------------------

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }
}
