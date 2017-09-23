package co.edu.uniandes.baco.gimnasio.persistence;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import javax.ejb.Stateless;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoPersistence extends BasePersistence<ObjetivoEntity>{
    public ObjetivoPersistence() {
        super(ObjetivoEntity.class);
    }
   
}