package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
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
    private List<MaquinaDTO> maquinas;

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

    public List<ObjetivoDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoDTO> objetivos) {
        this.objetivos = objetivos;
    }
}
