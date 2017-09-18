package co.edu.uniandes.baco.gimnasio.ejb;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.TipoMedidaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class TipoMedidaLogic {

    private static final Logger LOGGER = Logger.getLogger(TipoMedidaLogic.class.getName());

    @Inject
    private TipoMedidaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public TipoMedidaEntity createTipoMedida(TipoMedidaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de TipoMedida");
        // Invoca la persistencia para crear tipo medida
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de TipoMedida");
        return entity;
    }

    /**
     * 
     * Obtener todas los tipos medidas existentes en la base de datos.
     *
     * @return una lista de tipos medidas.
     */
    public List<TipoMedidaEntity> getTipoMedida() {
        LOGGER.info("Inicia proceso de consultar todas las tipo medida");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TipoMedidaEntity> TipoMedidas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Gimnasioes");
        return TipoMedidas;
    }


}