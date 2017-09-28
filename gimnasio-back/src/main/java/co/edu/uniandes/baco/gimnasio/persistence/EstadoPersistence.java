/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import java.util.Date;
import javax.ejb.Stateless;
/**
 *
 * @author js.palacios437
 */
@Stateless
public class EstadoPersistence extends BasePersistence<EstadoEntity>{
    public EstadoPersistence() {
        super(EstadoEntity.class);
    }
   
}
