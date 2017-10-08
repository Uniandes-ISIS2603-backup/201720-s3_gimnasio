package co.edu.uniandes.baco.gimnasio.dtos;


import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * GimnasioDTO Objeto de transferencia de datos de Gimnasioes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author ISIS2603
 */
public class GimnasioDTO {

    private Long id;
    private String descripcion;
    private String uniadad;
    private long nit;
   private String duenio;
   private String name;

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
        this.name = gimnasio.getName();
        this.nit = gimnasio.getNit();
    }

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

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public GimnasioEntity toEntity() {
        GimnasioEntity entity = new GimnasioEntity();
        entity.setId(this.id);
        entity.setDuenio(duenio);
        entity.setName(name);
        entity.setNit(nit);
        return entity;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUniadad() {
        return uniadad;
    }

    public void setUniadad(String uniadad) {
        this.uniadad = uniadad;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public static final List<GimnasioDTO> listDTO(List<GimnasioEntity> entity){
        List<GimnasioDTO> resp=new ArrayList<>();
        for(GimnasioEntity ent:entity){
            resp.add(new GimnasioDTO(ent));
        }
        return resp;
    }  
}
