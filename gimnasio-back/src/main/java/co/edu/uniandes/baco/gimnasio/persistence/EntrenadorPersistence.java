package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author m.sicard10
 */
@Stateless
public class EntrenadorPersistence extends BasePersistence<EntrenadorEntity>{
    public EntrenadorPersistence() {
        super(EntrenadorEntity.class);
    }
    
    public EntrenadorEntity findByDocumento(String doc)
    {
        TypedQuery querry = manager.createQuery("Select e from EntrenadorEntity e where e.documento = :documento",EntrenadorEntity.class);
        querry = querry.setParameter("documento", doc);
        List<EntrenadorEntity> sameName = querry.getResultList();
        EntrenadorEntity result = null; 
        if (sameName == null ) {
            result = null;
        } else if (sameName.isEmpty()) {
             result = null;
        } else {
            result =  sameName.get(0);
        }
        return result;
    }
}
