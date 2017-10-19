/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity>{
    private Search<ObjetivoEntity, EjercicioEntity> conEjercicio;
    private Search<ObjetivoEntity, UsuarioEntity> conUsuario;
    
    public ObjetivoLogic() {
        super();
    }
    
    @Inject public ObjetivoLogic(BasePersistence<ObjetivoEntity> persistence){
        super(persistence);
        this.conEjercicio=new Search<>(persistence, ObjetivoEntity::getEjercicios, EjercicioEntity.class);
        this.conUsuario=new Search<>(persistence, ObjetivoEntity::getUsuarios, UsuarioEntity.class);
    }
    
    @Override
    public ObjetivoEntity create(ObjetivoEntity entity)throws BusinessLogicException{
        if(null!=((ObjetivoPersistence)persistence).findByTipo(entity.getTipo()))
            throw new BusinessLogicException("ya existe un objetivo con ese tipo");
        return super.create(entity); 
    }

    @Override
    public ObjetivoEntity update(ObjetivoEntity entity) throws BusinessLogicException {
        ObjetivoEntity old=find(entity.getId());
        entity.setAtributos(old.getAtributos());
        return super.update(entity);
    }
    
    public List<UsuarioEntity> findAllUsuario(Long id) throws BusinessLogicException{
        return conUsuario.findAll(id);
    }
    
    public UsuarioEntity findEjerciccio(Long id,Long idEjercicio) throws BusinessLogicException{
        return conUsuario.find(id,idEjercicio);
    }
    
    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException{
        return conEjercicio.findAll(id);
    }
    
    public EjercicioEntity findAllEjercicio(Long id,Long idEjercicio) throws BusinessLogicException{
        return conEjercicio.find(id, idEjercicio);
    }
}
