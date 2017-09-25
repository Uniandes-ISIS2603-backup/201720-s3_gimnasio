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
package co.edu.uniandes.baco.gimnasio.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603
 */
@Entity
public class GimnasioEntity extends BaseEntity implements Serializable {
    
    /**
     * nombre del gimnasio
     */
   private String name;
   
   /**
    * nombre del duenio
    */
   private String duenio;
   
   /**
    * nit de la empresa
    */
   private long nit;
   
   
    @PodamExclude
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaquinaEntity> maquinas;

    @PodamExclude
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntrenadorEntity> entrenadores;

    @PodamExclude
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParteDelCuerpoEntity> partesDelCuerpo;

    @PodamExclude
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioEntity> usuarios;

   
   //--------------------------------------------------------------------------
   //METODOS
   //--------------------------------------------------------------------------
   public String getName() {
        
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    
    public List<MaquinaEntity> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<MaquinaEntity> maquinas) {
        this.maquinas = maquinas;
    }

    public List<EntrenadorEntity> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<EntrenadorEntity> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ParteDelCuerpoEntity> getPartesDelCuerpo() {
        return partesDelCuerpo;
    }

    public void setPartesDelCuerpo(List<ParteDelCuerpoEntity> partesDelCuerpo) {
        this.partesDelCuerpo = partesDelCuerpo;
    }
    
    
   
}
