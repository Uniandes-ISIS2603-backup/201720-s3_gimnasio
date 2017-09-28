package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author t.kavanagh
 **/
@Entity
public class TipoMedidaEntity extends BaseEntity implements Serializable{
    /**
     * atributo que modela la descripcion del tipod e medida
     */
    private String descripcion;
    /**
     * atributo que modela la unidad de la medida ej:cm,m,precion
     */
    private String unidad;
    /**
     * atributo que modela si la medida es automatica o no
     */
    private boolean automatico;
    /**
     * metodo get de automatico
     * @return el balor de automatico
     */
    public boolean isAutomatico() {
        return automatico;
    }
    /**
     * metodo set de atomatico
     * @param automatico valor booleano que determina si es automatico
     */
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }
    /**
     * metodo get de descripcion
     * @return la descripcion 
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * metodo set de descripcion
     * @param descripcion la descripcion del tipo medida
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * metodo get de UNidad
     * @return la unidad 
     */
    public String getUnidad() {
        return unidad;
    }
    /**
     * metodo set de la unidad
     * @param unidad tipo de unidad
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
