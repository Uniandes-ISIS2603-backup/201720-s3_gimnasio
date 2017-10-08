/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import javax.ejb.Stateless;


/**
 * @author ce.robles/ Carlos Eduardo Robles 201617129
 **/
@Stateless
public class TipoMedidaPersistence extends BasePersistence<TipoMedidaEntity>{

    public TipoMedidaPersistence() {
        super(TipoMedidaEntity.class);
    }
}
