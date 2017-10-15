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
    private String descricpion;

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
        this.descricpion=entity.getDescripcion();
    }
     /**
     * convierte un dto a entity
     * @return 
     * @throws co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException 
     */
    public EjercicioEntity toEntity() throws BusinessLogicException {
        EjercicioEntity ent = new EjercicioEntity();
        ent.setExplicacion(explicacion);
        ent.setDescripcion(descricpion);
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

    public String getDescricpion() {
        return descricpion;
    }

    public void setDescricpion(String descricpion) {
        this.descricpion = descricpion;
    }
    
    
    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
}
