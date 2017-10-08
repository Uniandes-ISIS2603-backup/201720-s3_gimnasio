package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GimnasioLogic extends BaseLogic <GimnasioEntity>{
    public GimnasioLogic() {
        super();
    }
    
    @Inject public GimnasioLogic(BasePersistence<GimnasioEntity> persistence) {
        super(persistence);
    }
}
