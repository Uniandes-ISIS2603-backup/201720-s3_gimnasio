/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author jp.romero12
 */
@Stateless
public class RutinaPersistence extends BasePersistence<RutinaEntity>{

    public RutinaPersistence() {
        super(RutinaEntity.class);
    }
}
