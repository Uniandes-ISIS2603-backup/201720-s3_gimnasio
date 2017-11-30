/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.dtos.EjercicioEfectivo;
import co.edu.uniandes.baco.gimnasio.entities.AtributoDeCalidadEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.ObjetivoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jc.bojaca
 */
@Stateless
public class ObjetivoLogic extends BaseLogic<ObjetivoEntity> {

    private Search<ObjetivoEntity, EjercicioEntity> conEjercicio;
    private Search<ObjetivoEntity, UsuarioEntity> conUsuario;

    public ObjetivoLogic() {
        super();
    }

    @Inject
    public ObjetivoLogic(BasePersistence<ObjetivoEntity> persistence) {
        super(persistence);
        this.conEjercicio = new Search<>(persistence, ObjetivoEntity::getEjercicios, EjercicioEntity.class);
        this.conUsuario = new Search<>(persistence, ObjetivoEntity::getUsuarios, UsuarioEntity.class);
    }

    @Override
    public ObjetivoEntity create(ObjetivoEntity entity) throws BusinessLogicException {
        if (null != ((ObjetivoPersistence) persistence).findByTipo(entity.getTipo())) {
            throw new BusinessLogicException("ya existe un objetivo con ese tipo");
        }
        return super.create(entity);
    }

    @Override
    public ObjetivoEntity update(ObjetivoEntity entity) throws BusinessLogicException {
        ObjetivoEntity old = find(entity.getId());
        entity.setAtributos(old.getAtributos());
        return super.update(entity);
    }

    //-----------------------------------
    //USUARIOS
    //-----------------------------------
    public List<UsuarioEntity> findAllUsuario(Long id) throws BusinessLogicException {
        return conUsuario.findAll(id);
    }

    public UsuarioEntity findEjerciccio(Long id, Long idEjercicio) throws BusinessLogicException {
        return conUsuario.find(id, idEjercicio);
    }

    //-----------------------------------
    //EJERCICIOS
    //-----------------------------------
    public List<EjercicioEfectivo> findAllEjercicioReg(Long id) throws BusinessLogicException {
        List<EjercicioEfectivo> list = new ArrayList<>();
        EjercicioEfectivo ejercicioEfectivo;
        List<EjercicioInstanciaEntity> list2;
        ObjetivoEntity objetivoEntity=find(id);
        for (EjercicioEntity x : conEjercicio.findAll(id)) {
            ejercicioEfectivo = new EjercicioEfectivo();
            ejercicioEfectivo.setDescricpion(x.getDescripcion());
            ejercicioEfectivo.setExplicacion(x.getExplicacion());
            ejercicioEfectivo.setId(x.getId());
            ejercicioEfectivo.setTipo(x.getTipo().name());
            list2 = x.getInstancias();
            if (!list2.isEmpty()) {
                double prom = 0;
                double count = 0;
                for (EjercicioInstanciaEntity y : list2) {
                    prom += efectividad(y,objetivoEntity);
                    count++;
                }
                ejercicioEfectivo.setEfectividad(prom/count);
            } else {
                ejercicioEfectivo.setEfectividad(0.0);
            }
            list.add(ejercicioEfectivo);
        }
        return list;
    }
    
    public double efectividad(EjercicioInstanciaEntity y,ObjetivoEntity objetivo){
        double normal=0;
        double mantener=0;
        int count=0;
        for(AtributoDeCalidadEntity atributo:objetivo.getAtributos()){
            for(RegrecionEntity regrecionEntity:y.getRegreciones()){
                if(atributo.getTipoMedida().equals(regrecionEntity.getTipoMedida())){
                    if(atributo.getRegresion()==0)
                        mantener += regrecionEntity.getRegresion()>0?regrecionEntity.getRegresion():-regrecionEntity.getRegresion();
                    else if(atributo.getRegresion() > 0)
                        normal += regrecionEntity.getRegresion();
                    else
                        normal += -regrecionEntity.getRegresion();
                    count++;
                }
            }
        }
        return (count>0)? (normal-mantener)/count :0;
    }

    public List<EjercicioEntity> findAllEjercicio(Long id) throws BusinessLogicException {
        return conEjercicio.findAll(id);
    }

    public EjercicioEntity findAllEjercicio(Long id, Long idEjercicio) throws BusinessLogicException {
        return conEjercicio.find(id, idEjercicio);

    }
}
