/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.BaseEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.ManyToMany;

/**
 *
 * @author jc.bojaca
 * @param <T>
 */
public abstract class BaseLogic<T extends BaseEntity> {

    /**
     * persistencia de la clase
     */
    protected BasePersistence<T> persistence;

    /**
     * constructor para la contenedora de aplicaciones
     */
    public BaseLogic() {
        //falla sin este constructor (no identifica el otroen primea instancia)
    }

    /**
     * cosntructor prar recibir la persistencia
     *
     * @param persistence
     */
    public BaseLogic(BasePersistence<T> persistence) {
        this.persistence = persistence;
    }

    /**
     * metodo que encuentra una entity
     *
     * @param id de la entity
     * @return la entity encontrada
     * @throws BusinessLogicException si no existe
     */
    public T find(Long id) throws BusinessLogicException {
        T t = persistence.find(id);
        if (t == null) {
            throw new NoExisteException(id);
        }
        return t;
    }

    /**
     * metodo que persiste una entiti
     *
     * @param entity
     * @return la entity creada
     * @throws BusinessLogicException
     */
    public T create(T entity) throws BusinessLogicException {
        return persistence.create(entity);
    }

    /**
     * metodo qque actuliza una entity
     *
     * @param entity
     * @return la entity actulizada
     * @throws BusinessLogicException
     */
    public T update(T entity) throws BusinessLogicException {
        try {
            T old = persistence.find(entity.getId());
            if (old == null) {
                throw new NoExisteException(entity.getId());
            }
            Logger.getLogger(BaseLogic.class.getSimpleName()).log(Level.INFO, "esta");
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ManyToMany.class)) {
                    Logger.getLogger(BaseLogic.class.getSimpleName()).log(Level.INFO, "entro");
                    field.setAccessible(true);
                    field.set(entity, field.get(old));
                }
            }
            return persistence.update(entity);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BaseLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * metodo que elimina una entity
     *
     * @param id el id de la entity
     * @throws BusinessLogicException si no existe la entity
     */
    public void remove(Long id) throws BusinessLogicException {
        if (persistence.find(id) == null) {
            throw new NoExisteException(id);
        }
        persistence.delete(id);
    }

    /**
     * metodo que encuentra todas las entitys
     *
     * @return lista de entitys
     * @throws BusinessLogicException
     */
    public List<T> findAll() throws BusinessLogicException {
        return persistence.findAll();
    }
}
