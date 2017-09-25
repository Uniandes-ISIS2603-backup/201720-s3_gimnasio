/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.baco.gimnasio.dtos;
import co.edu.uniandes.baco.gimnasio.dtos.GimnasioDTO;
import co.edu.uniandes.baco.gimnasio.entities.EntrenadorEntity;
import co.edu.uniandes.baco.gimnasio.entities.GimnasioEntity;
import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;
import co.edu.uniandes.baco.gimnasio.entities.ParteDelCuerpoEntity;
import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISIS2603
 */
public class GimnasioDetailDTO extends GimnasioDTO {
    
    //------------------------
    //atributos
    //------------------------
    private List <UsuarioDTO> ususrios ;
    
    private List <EntrenadorDTO> entrenadores;
    
    private List <MaquinaDTO> maquinas;
    
    private List <ParteDelCuerpoDTO> partesdelcuerpo;

    /**
     * Constructor por defecto
     */
    public GimnasioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public GimnasioDetailDTO(GimnasioEntity entity) {
        super(entity);
        if (entity.getUsuarios() != null)
        {
            
            this.ususrios = new ArrayList<UsuarioDTO>();
            for(UsuarioEntity u : entity.getUsuarios())
            {
                ususrios.add(new UsuarioDTO(u));
            }
        }
        if (entity.getEntrenadores()!= null)
        {
            
            this.entrenadores = new ArrayList<EntrenadorDTO>();
            for(EntrenadorEntity u : entity.getEntrenadores())
            {
                entrenadores.add(new EntrenadorDTO(u));
            }
        }
        
        if (entity.getMaquinas()!= null)
        {
            
            this.maquinas = new ArrayList<MaquinaDTO>();
            for(MaquinaEntity u : entity.getMaquinas())
            {
                maquinas.add(new MaquinaDTO(u));
            }
        }
        
        if (entity.getPartesDelCuerpo()!= null)
        {
            
            this.partesdelcuerpo = new ArrayList<ParteDelCuerpoDTO>();
            for(ParteDelCuerpoEntity u : entity.getPartesDelCuerpo())
            {
                partesdelcuerpo.add(new ParteDelCuerpoDTO(u));
            }
        }
        



    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public GimnasioEntity toEntity() {
        GimnasioEntity GimnasioE = super.toEntity();
        if (getUsusrios() != null)
        {
            
            this.ususrios = new ArrayList<UsuarioDTO>();
            for(UsuarioEntity u : GimnasioE.getUsuarios())
            {
                ususrios.add(new UsuarioDTO(u));
            }
        }
        if (getEntrenadores()!= null)
        {
            
            this.entrenadores = new ArrayList<EntrenadorDTO>();
            for(EntrenadorEntity u : GimnasioE.getEntrenadores())
            {
                entrenadores.add(new EntrenadorDTO(u));
            }
        }
        
        if (getMaquinas()!= null)
        {
            
            this.maquinas = new ArrayList<MaquinaDTO>();
            for(MaquinaEntity u : GimnasioE.getMaquinas())
            {
                maquinas.add(new MaquinaDTO(u));
            }
        }
        
        if (getPartesdelcuerpo()!= null)
        {
            
            this.partesdelcuerpo = new ArrayList<ParteDelCuerpoDTO>();
            for(ParteDelCuerpoEntity u : GimnasioE.getPartesDelCuerpo())
            {
                partesdelcuerpo.add(new ParteDelCuerpoDTO(u));
            }
        }
        return GimnasioE;
    }

    public List<UsuarioDTO> getUsusrios() {
        return ususrios;
    }

    public void setUsusrios(List<UsuarioDTO> ususrios) {
        this.ususrios = ususrios;
    }

    public List<EntrenadorDTO> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<EntrenadorDTO> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public List<MaquinaDTO> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaDTO> maquinas) {
        this.maquinas = maquinas;
    }

    public List<ParteDelCuerpoDTO> getPartesdelcuerpo() {
        return partesdelcuerpo;
    }

    public void setPartesdelcuerpo(List<ParteDelCuerpoDTO> partesdelcuerpo) {
        this.partesdelcuerpo = partesdelcuerpo;
    }
    

}
