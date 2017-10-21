/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.exceptions;

/**
 *
 * @author jc.bojaca
 */
public class NoExisteException extends BusinessLogicException {

    public NoExisteException() {
        //constructor base
    }

    /**
     * contrutiror con un id
     *
     * @param id
     */
    public NoExisteException(Long id) {
        super("El recurso con id=" + id + " no existe.");
    }

    /**
     * contructor con 2 ids padre e hijo
     *
     * @param id
     * @param id2
     */
    public NoExisteException(Long id, Long id2) {
        super("El subrecurso con id=" + id2 + ", no existe en como sub recurso del recurso con id=" + id + ".");
    }
}
