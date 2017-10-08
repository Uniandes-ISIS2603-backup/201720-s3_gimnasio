package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Generic entity with ID and name fields to inherit from.
 *
 * This entity sets a standar of fields and functions all entities in a project
 * should have. For example, all entities should be compared by ID when not
 * null, otherwise use the object equals method.
 *
 * @author jd.patino10
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param obj
     * @return retorna trur si los dos objetos (entities) tienen el mismo id
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        BaseEntity mc = (BaseEntity) obj;
        if (this.getId() != null && mc.getId() != null) {
            return this.getId().equals(((BaseEntity) obj).getId());
        }
        return false;
    }

    /**
     *
     * @return el hashcode del id que define la entidad
     */
    @Override
    public int hashCode() {
        if (getId() != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }
}
