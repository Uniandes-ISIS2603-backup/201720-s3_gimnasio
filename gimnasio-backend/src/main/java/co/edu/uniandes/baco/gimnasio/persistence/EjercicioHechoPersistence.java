package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ce.robles
 */
@Stateless
public class EjercicioHechoPersistence extends BasePersistence<EjercicioHechoEntity>{
    
    public EjercicioHechoPersistence() 
    {
        super(EjercicioHechoEntity.class);
    }
    
    public EjercicioHechoEntity findByFecha(Date fecha)
    {
        TypedQuery query = manager.createQuery("select u from "+ entityClass.getName()+" u where u.fecha = :fecha", entityClass);
        query.setParameter("fecha", fecha);
        List<EjercicioHechoEntity> ans =query.getResultList();
        if(ans.isEmpty())
            return null;
        return ans.get(0);
    }       
}
