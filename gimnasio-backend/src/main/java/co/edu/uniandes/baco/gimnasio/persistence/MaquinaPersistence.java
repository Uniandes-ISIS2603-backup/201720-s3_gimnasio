/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class MaquinaPersistence extends BasePersistence<MaquinaEntity>{

    public MaquinaPersistence() {
        super(MaquinaEntity.class);
    }
}
