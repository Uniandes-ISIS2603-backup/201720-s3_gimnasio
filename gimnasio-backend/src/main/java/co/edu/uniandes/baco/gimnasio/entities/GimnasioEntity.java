package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author ISIS2603
 */
@Entity
public class GimnasioEntity extends BaseEntity implements Serializable {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
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

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
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

    //--------------------------------------------
    // METODOS
    //--------------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.duenio);
        hash = 41 * hash + (int) (this.nit ^ (this.nit >>> 32));
        return hash;
    }
}
