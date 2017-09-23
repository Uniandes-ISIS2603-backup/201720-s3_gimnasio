/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import java.util.List;
import javax.persistence.ManyToMany;

/**
 *
 * @author t.kavanagh
 */
@Entity
public class MaquinaEntity extends BaseEntity implements Serializable{
    private String descripcion;
    @ManyToMany(mappedBy = "maquinas")
    private List<TipoMedidaEntity> tipoMedida;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTiposMedidas(List<TipoMedidaEntity> foo){
        this.tipoMedida = foo;
    }
    
}
