package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import java.util.ArrayList;
import java.util.List;

public class GimnasioDTO {
    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private long id;
    private long nit;
    private String duenio;
    private String nombre;

    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY
    //--------------------------------------------
    /**
     * Constructor por defecto
     */
    public GimnasioDTO() {
        //Constructor por defecto
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param gimnasio: Es la entidad que se va a convertir a DTO
     */
    public GimnasioDTO(GimnasioEntity gimnasio) {
        this.id = gimnasio.getId();
        this.duenio = gimnasio.getDuenio();
        this.nombre = gimnasio.getName();
        this.nit = gimnasio.getNit();
    }
    
     /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public GimnasioEntity toEntity() {
        GimnasioEntity entity = new GimnasioEntity();
        entity.setId(this.id);
        entity.setDuenio(duenio);
        entity.setName(nombre);
        entity.setNit(nit);
        return entity;
    }
    
    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public static final List<GimnasioDTO> listDTO(List<GimnasioEntity> entity) {
        List<GimnasioDTO> resp = new ArrayList<>();
        for (GimnasioEntity ent : entity) {
            resp.add(new GimnasioDTO(ent));
        }
        return resp;
    }
}
