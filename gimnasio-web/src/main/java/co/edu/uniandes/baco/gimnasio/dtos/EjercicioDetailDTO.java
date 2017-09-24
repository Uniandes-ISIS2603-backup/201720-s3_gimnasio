/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.EjercicioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ObjetivoEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.bojaca
 */
public class EjercicioDetailDTO extends EjercicioDTO{
    private List<ObjetivoDTO> objetivos;
    private List<ParteDelCuerpoDTO> partesDelCuerpo=new ArrayList<>();
    private List<MaquinaDTO> maquinas=new ArrayList<>();

    public EjercicioDetailDTO() {
        super();
    }
    
    public EjercicioDetailDTO(EjercicioEntity entity) {
        super(entity);
        if (entity.getObjetivos() != null){
            this.objetivos = new ArrayList<>();
            for(ObjetivoEntity aux : entity.getObjetivos()){
                objetivos.add(new ObjetivoDTO(aux));
            }
        }
        if (entity.getMaquinas() != null){
            this.maquinas = new ArrayList<>();
            for(MaquinaEntity aux2 : entity.getMaquinas()){
                maquinas.add(new MaquinaDTO(aux2));
            }
        }
        if (entity.getPartesDelCuerpo() != null){
            this.partesDelCuerpo = new ArrayList<>();
            for(ParteDelCuerpoEntity aux3 : entity.getPartesDelCuerpo()){
                partesDelCuerpo.add(new ParteDelCuerpoDTO(aux3));
            }
        }
    }

    public static final List<EjercicioDetailDTO> listDetailDTO(List<EjercicioEntity> entity){
        List<EjercicioDetailDTO> resp=new ArrayList<>();
        for(EjercicioEntity ent:entity){
            resp.add(new EjercicioDetailDTO((ent)));
        }
        return resp;
    }

    public List<ParteDelCuerpoDTO> getPartesDelCuerpo() {
        return partesDelCuerpo;
    }

    public void setPartesDelCuerpo(List<ParteDelCuerpoDTO> partesDelCuerpo) {
        this.partesDelCuerpo = partesDelCuerpo;
    }

    public List<MaquinaDTO> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaDTO> maquinas) {
        this.maquinas = maquinas;
    }

    public List<ObjetivoDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoDTO> objetivos) {
        this.objetivos = objetivos;
    }
}
