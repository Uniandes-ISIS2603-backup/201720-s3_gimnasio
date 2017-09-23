/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.dtos;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;

/**
 *
 * @author t.kavanagh
 */
public class TipoMedidaDetailDTO extends TipoMedidaDTO{
    
    public TipoMedidaDetailDTO(TipoMedidaEntity foo) {
        super(foo);
    }
    
    public TipoMedidaEntity toEntity(){
        TipoMedidaEntity foo = super.toEntity();
        return foo;
    }
}
