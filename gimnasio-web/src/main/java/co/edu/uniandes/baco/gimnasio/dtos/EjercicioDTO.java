package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.Tipo;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;

/**
 * @author jc.bojaca
 */
public class EjercicioDTO {

    //--------------------------------------------
    // DATOS BASE
    //--------------------------------------------
    private Long id;
    private String tipo;
    private String explicacion;
    private Integer duracion;
    private Integer series;
    private Integer tamanioParticiones;
    private Integer repeticionesPorParticion;

    //--------------------------------------------
    // CONSTRUCTOR & TOENTITY
    //--------------------------------------------
    public EjercicioDTO() {
        //JAVAX
    }

    public EjercicioDTO(EjercicioEntity entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo().name();
        this.explicacion = entity.getExplicacion();
        this.duracion = entity.getDuracion();
        this.series = entity.getSeries();
        this.tamanioParticiones = entity.getTamanioParticiones();
        this.repeticionesPorParticion = entity.getRepeticionesPorParticion();
    }
     /**
     * convierte un dto a entity
     * @return 
     */
    public EjercicioEntity toEntity() throws BusinessLogicException {
        EjercicioEntity ent = new EjercicioEntity();
        ent.setExplicacion(explicacion);
        ent.setDuracion(duracion);
        ent.setSeries(series);
        ent.setTamanioParticiones(tamanioParticiones);
        ent.setRepeticionesPorParticion(repeticionesPorParticion);
        try {
            ent.setTipo(Tipo.valueOf(tipo));
        } catch (java.lang.IllegalArgumentException e) {
            throw new BusinessLogicException("no se puede agregar un objeto sin categoria(existe: no_pertenece)", e);
        }
        return ent;
    }

    //--------------------------------------------
    // GETS & SETS
    //--------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getTamanioParticiones() {
        return tamanioParticiones;
    }

    public void setTamanioParticiones(Integer tamanioParticiones) {
        this.tamanioParticiones = tamanioParticiones;
    }

    public Integer getRepeticionesPorParticion() {
        return repeticionesPorParticion;
    }

    public void setRepeticionesPorParticion(Integer repeticionesPorParticion) {
        this.repeticionesPorParticion = repeticionesPorParticion;
    }
}
