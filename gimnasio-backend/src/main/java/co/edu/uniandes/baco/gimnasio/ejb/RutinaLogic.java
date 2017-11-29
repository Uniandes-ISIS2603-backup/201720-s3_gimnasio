/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.EjercicioHechoPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.MedicionMaquinaPersistence;
import co.edu.uniandes.baco.gimnasio.persistence.RutinaPersistence;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
public class RutinaLogic extends SubResource<UsuarioEntity, RutinaEntity> {

    private EjercicioHechoPersistence ejercicioHechoPersistence;
    private MedicionMaquinaPersistence medicionMaquinaPersistence;

    public RutinaLogic() {
        super();
    }

    @Inject
    public RutinaLogic(UsuarioLogic logic, BasePersistence<RutinaEntity> persistence, EjercicioHechoPersistence ejercicioHechoPersistence, MedicionMaquinaPersistence medicionMaquinaPersistence) {
        super(persistence, logic, UsuarioEntity::getRutinas, RutinaEntity::setUsuario);
        this.ejercicioHechoPersistence = ejercicioHechoPersistence;
        this.medicionMaquinaPersistence = medicionMaquinaPersistence;
    }

    public RutinaEntity getLast(long idUsuario) {
        return ((RutinaPersistence) persistence).last(idUsuario);
    }

    public void agregarMedidaMaquina(long idUsuario, long idMaquina, MedicionMaquinaEntity medicionMaquinaEntity) throws BusinessLogicException {
        RutinaEntity rutina = ((RutinaPersistence) persistence).last(idUsuario);
        MaquinaEntity maqu = new MaquinaEntity();
        maqu.setId(idMaquina);
        Date fecha = new Date();
        for (EjercicioInstanciaEntity x : rutina.getEjercicios()) {
            int ind = x.getEjercicio().getMaquinas().indexOf(maqu);
            if (ind >= 0) {
                List<EjercicioHechoEntity> ejer = x.getEjerciciosHechos();
                if (ejer.size() > 0) {
                    ejer.sort((EjercicioHechoEntity a, EjercicioHechoEntity b) -> (int) (b.getFecha().getTime() - a.getFecha().getTime()));
                    if (ejer.get(0).getFecha().equals(fecha)) {
                        MedicionMaquinaEntity aux = medicionMaquinaPersistence.create(medicionMaquinaEntity);
                        aux.setEjercicioHecho(ejer.get(0));
                    } else {
                        EjercicioHechoEntity ejercicioHechoEntity = new EjercicioHechoEntity();
                        ejercicioHechoEntity.setFecha(fecha);
                        ejercicioHechoEntity = ejercicioHechoPersistence.create(ejercicioHechoEntity);
                        ejercicioHechoEntity.setEjercicios(x);
                        MedicionMaquinaEntity aux = medicionMaquinaPersistence.create(medicionMaquinaEntity);
                        aux.setEjercicioHecho(ejercicioHechoEntity);
                    }
                } else {
                    EjercicioHechoEntity ejercicioHechoEntity = new EjercicioHechoEntity();
                    ejercicioHechoEntity.setFecha(fecha);
                    ejercicioHechoEntity = ejercicioHechoPersistence.create(ejercicioHechoEntity);
                    ejercicioHechoEntity.setEjercicios(x);
                    MedicionMaquinaEntity aux = medicionMaquinaPersistence.create(medicionMaquinaEntity);
                    aux.setEjercicioHecho(ejercicioHechoEntity);
                }
            }
        }
    }
}
