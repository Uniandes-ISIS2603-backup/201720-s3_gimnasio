/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.exceptions;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jc.bojaca
 */
public class NoExisteException extends WebApplicationException{

    public NoExisteException() {
        //constructor base
    }
    
    public NoExisteException(Long id){
        super("El recurso con id=" + id + " no existe.", 404);
    }
    
}
