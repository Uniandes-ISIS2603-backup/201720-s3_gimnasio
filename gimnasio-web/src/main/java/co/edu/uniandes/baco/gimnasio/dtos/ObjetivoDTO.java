package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class ObjetivoDTO {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private long id;
    private String tipo;
    private String descripcion;

    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY & LIST
    //--------------------------------------------
    public ObjetivoDTO() {
        //JAVAX
    }

    public ObjetivoDTO(ObjetivoEntity objetivo) {
        this.id = objetivo.getId();
        this.tipo = objetivo.getTipo();
        this.descripcion = objetivo.getDescripcion();
    }

    public ObjetivoEntity toEntity() {
        ObjetivoEntity ent = new ObjetivoEntity();
        ent.setTipo(tipo);
        ent.setDescripcion(descripcion);
        return ent;
    }

    public static final List<ObjetivoDTO> listDTO(List<ObjetivoEntity> entity) {
        List<ObjetivoDTO> resp = new ArrayList<>();
        for (ObjetivoEntity ent : entity) {
            resp.add(new ObjetivoDTO(ent));
        }
        return resp;
    }

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
