package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoPersistence extends BasePersistence<ObjetivoEntity>{
    public ObjetivoPersistence() {
        super(ObjetivoEntity.class);
    }    
    
    public ObjetivoEntity findByTipo(String name){
        @SuppressWarnings("JPQLValidation")
        TypedQuery query = manager.createQuery("select u from "+entityClass.getName()+" u where u.tipo = :name", entityClass);
        query.setParameter("name", name);
        List<ObjetivoEntity> ans =query.getResultList();
        if(ans.isEmpty())return null;
        return ans.get(0);
    }
   
}