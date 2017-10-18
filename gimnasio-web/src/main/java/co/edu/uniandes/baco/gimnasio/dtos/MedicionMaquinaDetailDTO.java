package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.MedicionMaquinaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce.robles
 */
public class MedicionMaquinaDetailDTO extends MedicionMaquinaDTO
{
    private String descripcion;
    private String unidad;
    
    public MedicionMaquinaDetailDTO() 
    {
        super();
    }
    
    public MedicionMaquinaDetailDTO(MedicionMaquinaEntity entity) 
    {
        super(entity);
        if (entity.getTipoMedida() != null) 
        {
            this.descripcion = entity.getTipoMedida().getTipoMedida();
            this.unidad = entity.getTipoMedida().getUnidad();
        }
    }    
    
    public static final List<MedicionMaquinaDetailDTO> listDetailDTO(List<MedicionMaquinaEntity> entity) {
        List<MedicionMaquinaDetailDTO> resp = new ArrayList<>();
        for (MedicionMaquinaEntity ent : entity) {
            resp.add(new MedicionMaquinaDetailDTO(ent));
        }
        return resp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
    
}
