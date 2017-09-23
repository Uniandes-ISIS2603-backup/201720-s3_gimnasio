/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.MaquinaEntity;

/**
 *
 * @author t.kavanagh
 */
public class MaquinaDetailDTO extends MaquinaDTO{
    
    public MaquinaDetailDTO(MaquinaEntity foo) {
        super(foo);
    }
    
    public MaquinaEntity toEntity(){
        MaquinaEntity foo = super.toEntity();
        return foo;
    }
}
