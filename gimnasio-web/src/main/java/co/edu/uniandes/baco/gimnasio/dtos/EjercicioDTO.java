package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;

/**
 * @author jc.bojaca
 */
public class EjercicioDTO {
    private String tipo;
    private String descripcion;
    private String explicacion;
    private Integer duracion;
    private Integer series;
    private Integer tamanioParticiones;
    private Integer repeticionesPorParticion;
    
    public EjercicioDTO(){}
    
    public EjercicioDTO(EjercicioEntity entity){
        this.descripcion = entity.getDescripcion();
        this.explicacion = entity.getExplicacion();
        this.duracion = entity.getDuracion();
        this.series = entity.getSeries();
        this.tamanioParticiones = entity.getTamanioParticiones();
        this.repeticionesPorParticion = entity.getRepeticionesPorParticion();
    }
    
    public EjercicioEntity toEntity(){
        EjercicioEntity ent=new EjercicioEntity();
        ent.setDescripcion(descripcion);
        ent.setExplicacion(explicacion);
        ent.setDuracion(duracion);
        ent.setSeries(series);
        ent.setTamanioParticiones(tamanioParticiones);
        ent.setRepeticionesPorParticion(repeticionesPorParticion);
        return ent;
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

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
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
