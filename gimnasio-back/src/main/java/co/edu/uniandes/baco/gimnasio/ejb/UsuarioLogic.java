/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;


/**
 *
 * @author m.sicard10
 */
@Stateless
public class UsuarioLogic extends BaseLogic<UsuarioEntity>{

    public List<UsuarioEntity> findalA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
