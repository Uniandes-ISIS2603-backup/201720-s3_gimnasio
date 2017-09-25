/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author t.kavanagh
 */
@Entity
public class MaquinaEntity extends BaseEntity implements Serializable{
    private String descripcion;
    
    @PodamExclude
    @OneToMany
    private List<TipoMedidaEntity> tipoMedida;
        @PodamExclude
        @ManyToOne
    private GimnasioEntity gimnasio;

    public String getDescripcion() {
        return descripcion;
    }

    public List<TipoMedidaEntity> getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(List<TipoMedidaEntity> tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTiposMedidas(List<TipoMedidaEntity> foo){
        this.tipoMedida = foo;
    }
}
