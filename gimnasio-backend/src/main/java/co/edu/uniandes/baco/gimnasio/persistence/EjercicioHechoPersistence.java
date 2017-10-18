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
    
    public EjercicioHechoEntity findByFecha(Long id,Date fecha){
        TypedQuery query = manager.createQuery("select u from "+ entityClass.getName()+" u ,EjercicioInstanciaEntity s where u.fechaInicio = :fecha and s.id = :id", entityClass);
        query.setParameter("fecha", fecha);
        query.setParameter("id", id);
        List<EjercicioHechoEntity> ans =query.getResultList();
        if(ans.isEmpty())
            return null;
        return ans.get(0);
    }       
}
