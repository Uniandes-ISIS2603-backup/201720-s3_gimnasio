/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce.robles
 */
public class EjercicioHechoDetailDTO extends EjercicioHechoDTO {

    //----------------------------------------------------------------------------------------
    // -----------------------------------Atributos------------------------------------------
    //----------------------------------------------------------------------------------------

    private List<MedicionMaquinaDetailDTO> medicionMaquina;
    private EjercicioDTO ejercicio;

    //----------------------------------------------------------------------------------------
    // -----------------------------------Constructores------------------------------------------
    //----------------------------------------------------------------------------------------
    
    public EjercicioHechoDetailDTO() {
        super();
    }

    public EjercicioHechoDetailDTO(EjercicioHechoEntity ent) {
        super(ent);
        
        if(ent.getMedicionMaquinaEnt() != null)
        {
            this.medicionMaquina = new ArrayList<>();
            for(MedicionMaquinaEntity aux: ent.getMedicionMaquinaEnt())
            {
                medicionMaquina.add(new MedicionMaquinaDetailDTO(aux));
            }
        }
    }
    
    //----------------------------------------------------------------------------------------
    // ---------------------------------Setters y Getters-------------------------------------
    //----------------------------------------------------------------------------------------
    
    public EjercicioDTO getEjercicio() { return ejercicio; }

    public void setEjercicio(EjercicioDTO ejercicio) { this.ejercicio = ejercicio; }

    public List<MedicionMaquinaDetailDTO> getMedicionMaquina() { return medicionMaquina; }

    public void setMedicionMaquina(List<MedicionMaquinaDetailDTO> medicionMaquina) { this.medicionMaquina = medicionMaquina;  }
    
    //----------------------------------------------------------------------------------------
    // -----------------------------------Metodos------------------------------------------
    //----------------------------------------------------------------------------------------

    public static final List<EjercicioHechoDetailDTO> listDetailDTO(List<EjercicioHechoEntity> entity) {
        List<EjercicioHechoDetailDTO> resp = new ArrayList<>();
        for (EjercicioHechoEntity ent : entity) {
            resp.add(new EjercicioHechoDetailDTO(ent));
        }
        return resp;
    }    
}
