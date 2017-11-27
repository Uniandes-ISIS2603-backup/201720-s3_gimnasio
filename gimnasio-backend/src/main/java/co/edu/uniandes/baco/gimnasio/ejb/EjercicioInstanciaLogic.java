/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bojaca
 */
@Stateless
public class EjercicioInstanciaLogic extends SubResource<RutinaEntity, EjercicioInstanciaEntity> {

    /**
     * injecion de la logica de rutina
     */
    private EjercicioLogic ejercicioLogic;

    public EjercicioInstanciaLogic() {
        super();
    }

    @Inject
    public EjercicioInstanciaLogic(RutinaLogic rutinaLogic, EjercicioLogic ejercicioLogic, BasePersistence<EjercicioInstanciaEntity> persistence) {
        super(persistence, rutinaLogic, RutinaEntity::getEjercicios, EjercicioInstanciaEntity::setRutina);
        this.ejercicioLogic = ejercicioLogic;
    }

    /**
     * metodo que crea o asocia un ejercicio a una rutina
     *
     * @param idRutina id de la rutina
     * @param entity el ejercicio
     * @param idEjercicio
     * @return el ejercicio creado
     * @throws BusinessLogicException si la rutina no existe
     */
    public EjercicioInstanciaEntity create(long idRutina, EjercicioInstanciaEntity entity, long idEjercicio) throws BusinessLogicException {
        EjercicioInstanciaEntity ans = create(idRutina, entity);
        ans.setEjercicio(ejercicioLogic.find(idEjercicio));
        return ans;
    }

    public void calcularCumplimentoAll() throws BusinessLogicException {
        for (EjercicioInstanciaEntity e : findAll()) {
            if (!e.getEjerciciosHechos().isEmpty()) {
                calcularCumplimiento(e);
            } else {
                e.setCumplimiento(0.0);
            }
        }
    }

    public void calcularCumplimiento(EjercicioInstanciaEntity e) {
        Calendar ini = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        Calendar aux = Calendar.getInstance();
        RutinaEntity rutina = e.getRutina();
        ini.setTime(rutina.getFechaInicio());
        fin.setTime(rutina.getFechaFinal());
        List<EjercicioHechoEntity> list = e.getEjerciciosHechos();
        list.sort((a, b) -> (int) (a.getFecha().getTime() - b.getFecha().getTime()));
        int part = 1; //cuanta las particiones
        int i = 0; //recorre la lista
        double cont = 0; //coteo para el promedio
        ini.add(Calendar.DAY_OF_MONTH, e.getTamanioParticiones());
        aux.setTime(list.get(i).getFecha());
        while (ini.before(fin)) { //recorre las particiones
            int series = 0; //series hechas en la particion
            int veces = 0; // ejercicios hechos en una particion
            while (!aux.after(ini) && i < list.size()) {
                veces++;
                series += list.get(i++).getSeriesReales();
                if (i < list.size()) {
                    aux.setTime(list.get(i).getFecha());
                }
            }
            cont += (double) ((veces * e.getSeries()) + series) / (2 * e.getSeries() * e.getRepeticionesPorParticion());
            ini.add(Calendar.DAY_OF_MONTH, e.getTamanioParticiones());
            part++;
        }
        double cump=(cont / part) * 100;
        int cant=rutina.getEjercicios().size();
        rutina.setCumplimiento(rutina.getCumplimiento()-(e.getCumplimiento()/cant)+(cump/cant));
        e.setCumplimiento(cump);
    }

    //-----------------------------------
    // EJERCICIO
    //-----------------------------------
    public EjercicioEntity findEjercicio(long idRutina, long id) throws BusinessLogicException {
        return find(idRutina, id).getEjercicio();
    }
}
