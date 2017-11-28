package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.romero12
 */
@Stateless
public class RutinaPersistence extends BasePersistence<RutinaEntity> {

    public RutinaPersistence() {
        super(RutinaEntity.class);
    }
    
    public RutinaEntity last(long idUsuario){
        TypedQuery query = manager.createQuery("select u from " + entityClass.getName() + " u where u.usuario.id = :name order by u.fechaInicio desc", entityClass);
        query.setParameter("name", idUsuario);
        List<RutinaEntity> ans = query.getResultList();
        if (ans.isEmpty()) {
            return null;
        }
        return ans.get(0);
    }
}
