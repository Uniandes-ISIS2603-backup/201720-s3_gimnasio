/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.RutinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m.sicard10
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    /*
    *relacion con todos los entrenadores de un usuario
    */
    private List<EntrenadorDTO> entrenadores;
    private List<RutinaDTO> rutinas;
    
    //--------------
    //Metodos
    //--------------
     public UsuarioDetailDTO() {
        super();
    }
    public UsuarioDetailDTO(UsuarioEntity u) {
        super(u);
        if (u != null)
        {
            entrenadores = new ArrayList<>();
            for(EntrenadorEntity e: u.getEntrenadores())
            {
                entrenadores.add(new EntrenadorDTO(e));
            }
        }
        if (u != null)
        {
            rutinas = new ArrayList<>();
            for(RutinaEntity e2: u.getRutinas())
            {
                rutinas.add(new RutinaDTO(e2));
            }
        }
    }
    
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity e = super.toEntity();
        if (entrenadores != null)
        {
            List<EntrenadorEntity> entE = new ArrayList<>();
            for(EntrenadorDTO d:entrenadores)
            {
                entE.add(d.toEntity());
            }
            e.setEntrenadores(entE);
        }
        return e;
    }

    public List<RutinaDTO> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<RutinaDTO> rutinas) {
        this.rutinas = rutinas;
    }
    
    public List<EntrenadorDTO> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<EntrenadorDTO> entrenadores) {
        this.entrenadores = entrenadores;
    }
}
