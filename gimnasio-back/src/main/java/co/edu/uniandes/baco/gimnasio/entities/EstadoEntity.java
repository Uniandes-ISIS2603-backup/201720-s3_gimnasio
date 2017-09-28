/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author js.palacios437
 */
@Entity
public class EstadoEntity extends BaseEntity implements Serializable{
    
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fecha;
   
   @PodamExclude
   @OneToMany(mappedBy = "estado",cascade = CascadeType.REFRESH , orphanRemoval = true, fetch= FetchType.LAZY )
   private List<MedidaEntity> medidas;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
