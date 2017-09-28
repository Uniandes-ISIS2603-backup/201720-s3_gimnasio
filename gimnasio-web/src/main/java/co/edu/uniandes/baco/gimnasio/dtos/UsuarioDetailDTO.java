/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.EstadoEntity;
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
    private List<EstadoDTO> estados;
    
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
            entrenadores = new ArrayList<>();
            for(EntrenadorEntity e: u.getEntrenadores())
            {
                entrenadores.add(new EntrenadorDTO(e));
            }
        }
        if (u != null)
        {
            estados = new ArrayList<>();
            for(EstadoEntity e2: u.getEstados())
            {
                estados.add(new EstadoDTO(e2));
            }
        }
    }

    public List<EstadoDTO> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoDTO> estados) {
        this.estados = estados;
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
    
    public static final List<UsuarioDetailDTO> listDetailDTO(List<UsuarioEntity> entity){
        List<UsuarioDetailDTO> resp=new ArrayList<>();
        for(UsuarioEntity ent:entity){
            resp.add(new UsuarioDetailDTO(ent));
        }
        return resp;
    }
}
