/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author camilo
 */
@Stateless
public class RegresionPersistence extends BasePersistence<RegrecionEntity> {

    public RegresionPersistence() {
        super(RegrecionEntity.class);
    }
    
    public void removeByTipo(long idInstancia,long tipo){
        TypedQuery query = manager.createQuery("delete from " + entityClass.getName() + " u where u.tipoMedida_id = :name AND u.ejercicio_id = :name2", entityClass);
        query.setParameter("name", tipo+"");
        query.setParameter("name2", tipo+"");
        query.executeUpdate();
    }
}
