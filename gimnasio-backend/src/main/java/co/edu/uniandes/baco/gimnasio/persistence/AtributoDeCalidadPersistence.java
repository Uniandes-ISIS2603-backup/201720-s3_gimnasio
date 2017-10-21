/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import javax.ejb.Stateless;

/**
 *
 * @author camilo
 */
@Stateless
public class AtributoDeCalidadPersistence extends BasePersistence<AtributoDeCalidadEntity> {

    public AtributoDeCalidadPersistence() {
        super(AtributoDeCalidadEntity.class);
    }

}
