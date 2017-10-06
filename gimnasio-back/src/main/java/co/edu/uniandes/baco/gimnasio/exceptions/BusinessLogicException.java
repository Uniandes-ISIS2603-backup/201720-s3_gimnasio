package co.edu.uniandes.baco.gimnasio.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author ISIS2603
 */
@ApplicationException(rollback = true)
public class BusinessLogicException extends Exception {

    public BusinessLogicException() {
        //este es el basico constructor de la exepcion
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public BusinessLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinessLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
