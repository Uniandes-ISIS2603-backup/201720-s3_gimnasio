/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioInstanciaPersistence extends BasePersistence<EjercicioInstanciaEntity> {

    public EjercicioInstanciaPersistence() {
        super(EjercicioInstanciaEntity.class);
    }
}
