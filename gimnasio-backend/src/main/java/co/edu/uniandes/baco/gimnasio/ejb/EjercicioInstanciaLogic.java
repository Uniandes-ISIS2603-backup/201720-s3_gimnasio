/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.dtos.Graphic;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioHechoEntity;
import co.edu.uniandes.baco.gimnasio.entities.EjercicioInstanciaEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.MedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.RegrecionEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.persistence.BasePersistence;
import co.edu.uniandes.baco.gimnasio.persistence.RegresionPersistence;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private RegresionPersistence regPersistence;

    public EjercicioInstanciaLogic() {
        super();
    }

    @Inject
    public EjercicioInstanciaLogic(RutinaLogic rutinaLogic, EjercicioLogic ejercicioLogic, RegresionPersistence regPersistence, BasePersistence<EjercicioInstanciaEntity> persistence) {
        super(persistence, rutinaLogic, RutinaEntity::getEjercicios, EjercicioInstanciaEntity::setRutina);
        this.ejercicioLogic = ejercicioLogic;
        this.regPersistence = regPersistence;
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
        EjercicioEntity ejercicio = ejercicioLogic.find(idEjercicio);
        ans.setEjercicio(ejercicio);
        for (TipoMedidaEntity x : ejercicio.getTiposMedidas()) {
            RegrecionEntity nueva = new RegrecionEntity();
            nueva.setRegresion(0.0);
            nueva.setEjercicio(ans);
            nueva.setTipoMedida(x);
            regPersistence.create(nueva);
        }
        RutinaEntity rut = ans.getRutina();
        int cant = rut.getEjercicios().size();
        rut.setCumplimiento(rut.getCumplimiento() * ((cant - 1) / cant));
        return ans;
    }

    @Override
    public EjercicioInstanciaEntity update(Long id, EjercicioInstanciaEntity s) throws BusinessLogicException {
        EjercicioInstanciaEntity ans = super.update(id, s);
        calcularCumplimiento(ans);
        return ans;
    }

    @Override
    public void remove(long idUsuario, long id) throws BusinessLogicException {
        EjercicioInstanciaEntity ans = find(idUsuario, id);
        RutinaEntity rut = ans.getRutina();
        int cant = rut.getEjercicios().size();
        rut.setCumplimiento((rut.getCumplimiento() - (ans.getCumplimiento() / cant)) * (cant / (cant - 1)));
        super.remove(idUsuario, id);
    }

    public void calcularCumplimentoAll() throws BusinessLogicException {
        for (EjercicioInstanciaEntity e : findAll()) {
            e.setCumplimiento(0.0);
            e.getRutina().setCumplimiento(0.0);
        }
        for (EjercicioInstanciaEntity e : findAll()) {
            if (!e.getEjerciciosHechos().isEmpty()) {
                calcularCumplimiento(e);
            }
            for (RegrecionEntity r : e.getRegreciones()) {
                if (r.getTipoMedida().isAutomatico()) {
                    calcularRegrecion(e, r);
                } else {
                    calcularRegrecion2(e, r);
                }
            }
        }
    }

    public Graphic cumplimeto(long idRutina, long id) throws BusinessLogicException {
        EjercicioInstanciaEntity e = find(idRutina, id);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Double> valores = new ArrayList<>();
        List<String> ejerx = new ArrayList<>();
        RutinaEntity rutina = e.getRutina();
        Calendar ini = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        Calendar aux = Calendar.getInstance();
        ini.setTime(rutina.getFechaInicio());
        fin.setTime(rutina.getFechaFinal().before(new Date()) ? rutina.getFechaFinal() : new Date());
        List<EjercicioHechoEntity> list = new ArrayList<>(e.getEjerciciosHechos());
        if (!list.isEmpty()) {
            list.sort((a, b) -> (int) (a.getFecha().getTime() - b.getFecha().getTime()));
            int i = 0; //recorre la lista
            ini.add(Calendar.DAY_OF_MONTH, e.getTamanioParticiones());
            aux.setTime(list.get(i).getFecha());
            while (ini.before(fin)) { //recorre las particiones
                int series = 0; //series hechas en la particio
                while (!aux.after(ini) && i < list.size()) {
                    series += list.get(i++).getSeriesReales();
                    if (i < list.size()) {
                        aux.setTime(list.get(i).getFecha());
                    }
                }
                valores.add((double) series);
                ejerx.add(format.format(ini.getTime()));
                ini.add(Calendar.DAY_OF_MONTH, e.getTamanioParticiones());
            }
        }
        return new Graphic(valores, ejerx);
    }

    public void calcularRegrecion2(EjercicioInstanciaEntity instancia, RegrecionEntity regrecionEntity) {
        TipoMedidaEntity tipo = regrecionEntity.getTipoMedida();
        Date ini = instancia.getRutina().getFechaInicio();
        Date fin = instancia.getRutina().getFechaFinal();
        List<Double> values = new LinkedList<>();
        for (EstadoEntity x : instancia.getRutina().getUsuario().getEstados()) {
            if (!x.getFecha().before(ini) && !x.getFecha().after(fin)) {
                auxcalcularRegrecion2(x, tipo, values);
            }
        }
        regrecionEntity.setRegresion(lineal(values));
    }

    public void auxcalcularRegrecion2(EstadoEntity x, TipoMedidaEntity tipo, List<Double> values) {
        for (MedidaEntity y : x.getMedidas()) {
            if (y.getParte().equals(tipo)) {
                values.add(y.getMedida());
            }
        }
    }

    public void calcularRegrecion(EjercicioInstanciaEntity instancia, RegrecionEntity regrecionEntity) {
        TipoMedidaEntity tipo = regrecionEntity.getTipoMedida();
        Date ini = instancia.getRutina().getFechaInicio();
        Date fin = instancia.getRutina().getFechaFinal();
        List<Double> values = new LinkedList<>();
        for (EjercicioHechoEntity x : instancia.getEjerciciosHechos()) {
            if (!x.getFecha().before(ini) && !x.getFecha().after(fin)) {
                auxalcularRegrecion(x, tipo, values);
            }
        }
        regrecionEntity.setRegresion(lineal(values));
    }

    public void auxalcularRegrecion(EjercicioHechoEntity x, TipoMedidaEntity tipo, List<Double> values) {
        for (MedicionMaquinaEntity y : x.getMedicionMaquinaEnt()) {
            if (y.getTipoMedida().equals(tipo)) {
                values.add(y.getMedicionManquina());
            }
        }
    }

    public Double lineal(List<Double> y) {
        int n = y.size();
        if (n <= 1) {
            return 0.0;
        }
        double sx = 0.0;
        double sy = 0.0;
        for (int i = 0; i < n; i++) {
            sx += (i + 1);
            sy += y.get(i);
        }
        double xbar = sx / n;
        double ybar = sy / n;
        double xxbar = 0.0;
        double xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += ((i + 1) - xbar) * ((i + 1) - xbar);
            xybar += ((i + 1) - xbar) * (y.get(i) - ybar);
        }
        return xybar / xxbar;
    }

    public void calcularCumplimiento(EjercicioInstanciaEntity e) {
        Calendar ini = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        Calendar aux = Calendar.getInstance();
        RutinaEntity rutina = e.getRutina();
        ini.setTime(rutina.getFechaInicio());
        fin.setTime(rutina.getFechaFinal().before(new Date()) ? rutina.getFechaFinal() : new Date());
        List<EjercicioHechoEntity> list = new ArrayList<>(e.getEjerciciosHechos());
        if (!list.isEmpty()) {
            list.sort((a, b) -> (int) (a.getFecha().getTime() - b.getFecha().getTime()));
            int part = 1; //cuanta las particiones
            int i = 0; //recorre la lista
            double cont = 0; //conteo para el promedio
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
            double cump = (cont / part) * 100;
            int cant = rutina.getEjercicios().size();
            rutina.setCumplimiento(rutina.getCumplimiento() - (e.getCumplimiento() / cant) + (cump / cant));
            e.setCumplimiento(cump);
        }
    }

//-----------------------------------
// EJERCICIO
//-----------------------------------
    public EjercicioEntity findEjercicio(long idRutina, long id) throws BusinessLogicException {
        return find(idRutina, id).getEjercicio();
    }
}
