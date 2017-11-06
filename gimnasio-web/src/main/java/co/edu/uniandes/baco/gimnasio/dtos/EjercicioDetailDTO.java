package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class EjercicioDetailDTO extends EjercicioDTO {

    //--------------------------------------------
    // DATOS ENTITY
    //--------------------------------------------
    private List<ObjetivoDTO> objetivos;
    private List<EjercicioHechoDTO> ejerciciosHechos;
    private List<MaquinaDTO> maquinas;
    private List<TipoMedidaDTO> tipoMedida;
    private List<EjercicioInstanciaDTO> instancias;

    //--------------------------------------------
    // CONSTRUCTOR & LIST
    //--------------------------------------------
    public EjercicioDetailDTO() {
        super();
    }

    public EjercicioDetailDTO(EjercicioEntity entity) {
        super(entity);
        if (entity.getObjetivosEjercicio()!= null) {
            this.objetivos = new ArrayList<>();
            for (ObjetivoEntity aux : entity.getObjetivosEjercicio()) {
                objetivos.add(new ObjetivoDTO(aux));
            }
        }
        if (entity.getMaquinas() != null) {
            this.maquinas = new ArrayList<>();
            for (MaquinaEntity aux2 : entity.getMaquinas()) {
                maquinas.add(new MaquinaDTO(aux2));
            }
        }
        if (entity.getTiposMedidas() != null){
            this.tipoMedida = new ArrayList<>();
            for(TipoMedidaEntity aux : entity.getTiposMedidas()){
                tipoMedida.add(new TipoMedidaDTO(aux));
            }
        }
        if (entity.getInstancias() != null){
            this.instancias = new ArrayList<>();
            for(EjercicioInstanciaEntity aux : entity.getInstancias()){
                instancias.add(new EjercicioInstanciaDTO(aux));
            }
        }
    }

    public static final List<EjercicioDetailDTO> listDetailDTO(List<EjercicioEntity> entity) {
        List<EjercicioDetailDTO> resp = new ArrayList<>();
        for (EjercicioEntity ent : entity) {
            resp.add(new EjercicioDetailDTO(ent));
        }
        return resp;
    }

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public List<MaquinaDTO> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaDTO> maquinas) {
        this.maquinas = maquinas;
    }

    public List<EjercicioInstanciaDTO> getInstancias() {
        return instancias;
    }

    public void setInstancias(List<EjercicioInstanciaDTO> instancias) {
        this.instancias = instancias;
    }

    public List<TipoMedidaDTO> getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(List<TipoMedidaDTO> tipoMedida) {
        this.tipoMedida = tipoMedida;
    }      

    public List<ObjetivoDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoDTO> objetivos) {
        this.objetivos = objetivos;
    }

    public List<EjercicioHechoDTO> getEjerciciosHechos() {
        return ejerciciosHechos;
    }

    public void setEjerciciosHechos(List<EjercicioHechoDTO> ejerciciosHechos) {
        this.ejerciciosHechos = ejerciciosHechos;
    }
    
    
}
