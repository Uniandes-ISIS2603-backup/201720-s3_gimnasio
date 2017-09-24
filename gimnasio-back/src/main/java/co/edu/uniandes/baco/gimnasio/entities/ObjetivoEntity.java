package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author jc.bojaca
 */

@Entity
public class ObjetivoEntity extends BaseEntity implements Serializable{
    String tipo;
    String descripcion;
    
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> usuarios=new ArrayList<>();

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    } 
    
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;} 
}
