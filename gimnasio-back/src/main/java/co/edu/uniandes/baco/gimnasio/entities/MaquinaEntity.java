package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author t.kavanagh
 */
@Entity
public class MaquinaEntity extends BaseEntity implements Serializable {
    private String descripcion;

    @PodamExclude
    @OneToMany
    private List<TipoMedidaEntity> tipoMedida;

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

    public void setTiposMedidas(List<TipoMedidaEntity> foo) {
        this.tipoMedida = foo;
    }
}
