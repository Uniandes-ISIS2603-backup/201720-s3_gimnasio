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
public class EntrenadorPersistence extends BasePersistence<EntrenadorEntity> {

    public EntrenadorPersistence() {
        super(EntrenadorEntity.class);
    }

    /**
     * metodo que encuentra un entrenador por documento
     *
     * @param doc el documento
     * @return el entrenador
     */
    public EntrenadorEntity findByDocumento(String doc) {
        TypedQuery querry = manager.createQuery("Select e from EntrenadorEntity e where e.documento = :documento", EntrenadorEntity.class);
        querry = querry.setParameter("documento", doc);
        List<EntrenadorEntity> ans = querry.getResultList();
        if (ans.isEmpty()) {
            return null;
        }
        return ans.get(0);
    }
}
