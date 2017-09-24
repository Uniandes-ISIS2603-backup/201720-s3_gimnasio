/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author js.palacios437
 */

@Entity
public class PartesDelCuerpoEntity extends BaseEntity implements Serializable{
    
    private String partedelcuerpo;
    private String unidadMedida;


    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
    }
    
    @OneToMany(mappedBy ="parte")
    private List<MedidaEntity> medidas = new ArrayList<MedidaEntity>();


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
