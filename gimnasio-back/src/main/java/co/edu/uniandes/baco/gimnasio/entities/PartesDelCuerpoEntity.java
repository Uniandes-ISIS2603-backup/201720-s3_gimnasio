/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author js.palacios437
 */

@Entity
public class PartesDelCuerpoEntity extends BaseEntity implements Serializable{
    
    private String partedelcuerpo;
    private String unidadMedida;

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public String getPartedelcuerpo() {
        return partedelcuerpo;
    }

    public void setPartedelcuerpo(String partedelcuerpo) {
        this.partedelcuerpo = partedelcuerpo;
    }
    
    
    
    
}
