/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author m.sicard10
 */
@Stateless
public class UsuarioLogic extends BaseLogic<UsuarioEntity> {

    private Connection<UsuarioEntity, ObjetivoEntity> conObjetivo;
    private Search<UsuarioEntity, EntrenadorEntity> conEntrenador;

    public UsuarioLogic() {
        super();
    }

    @Inject
    UsuarioLogic(BasePersistence<UsuarioEntity> persistence) {
        super(persistence);
        conObjetivo = new Connection<>(persistence, UsuarioEntity::getObjetivos, ObjetivoEntity.class);
        conEntrenador = new Search<>(persistence, UsuarioEntity::getEntrenadores, EntrenadorEntity.class);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity entity) throws BusinessLogicException {
        UsuarioEntity ent = find(entity.getId());
        entity.setEntrenadores(ent.getEntrenadores());
        entity.setEstados(ent.getEstados());
        entity.setRutinas(ent.getRutinas());
        entity.setObjetivos(ent.getObjetivos());
        return super.update(entity);
    }

    //-----------------------------------
    // OBJETIVO
    //-----------------------------------
    public List<ObjetivoEntity> findAllObjetivo(Long id) throws BusinessLogicException {
        return conObjetivo.findAll(id);
    }

    public ObjetivoEntity findObjetivo(Long idUsuario, Long id) throws BusinessLogicException {
        return conObjetivo.find(idUsuario, id);
    }

    public ObjetivoEntity createObjetivo(Long idUsuario, Long id) throws BusinessLogicException {
        return conObjetivo.create(idUsuario, id);
    }

    public void removeObejtivo(Long idUsuario, Long id) throws BusinessLogicException {
        conObjetivo.remove(idUsuario, id);
    }

    //-----------------------------------
    // ENTRENADOR
    //-----------------------------------
    public List<EntrenadorEntity> findAllEntrenador(Long id) throws BusinessLogicException {
        return conEntrenador.findAll(id);
    }

    public EntrenadorEntity findAllEntrenador(Long id, Long idSub) throws BusinessLogicException {
        return conEntrenador.find(id, idSub);
    }
}
