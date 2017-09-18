/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author jc.bojaca
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        if (this.getId() != null && ((BaseEntity) obj).getId() != null) {
            return this.getId().equals(((BaseEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     * 
     * @return el hashcode del id que define la entidad
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
