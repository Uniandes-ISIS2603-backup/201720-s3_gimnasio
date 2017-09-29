/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author camilo
 */
@Entity
public class AtributoDeCalidadEntity extends BaseEntity {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    /**
     * atributo que define el objetivo especifico sobre el tipo de medida      {0<} desea disminurir sus medidiciones sobre ese atributo
     * {0>} desea aumenar sus medidciones sobre ese atributo {0=} desea
     * conservar sus medicionse sobre es atributo
     */
    private int regresion;
    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    /**
     * objetivo al que pertenece
     */
    @PodamExclude
    @ManyToOne
    private ObjetivoEntity objetivo;
    /**
     * atributo que mide
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.REFRESH)
    private TipoMedidaEntity tipoMedida;
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    public int getRegresion() {
        return regresion;
    }

    public void setRegresion(int regresion) {
        this.regresion = regresion;
    }

    public ObjetivoEntity getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ObjetivoEntity objetivo) {
        this.objetivo = objetivo;
    }

    public TipoMedidaEntity getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(TipoMedidaEntity tipoMedida) {
        this.tipoMedida = tipoMedida;
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
        int hash = 3;
        hash = 53 * hash + this.regresion;
        return hash;
    }
}