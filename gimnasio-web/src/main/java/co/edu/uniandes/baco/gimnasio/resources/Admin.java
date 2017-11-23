/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.resources;

import co.edu.uniandes.baco.gimnasio.ejb.EjercicioInstanciaLogic;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.bojaca
 */
@Path("ADMIN")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Admin {
    
    private EjercicioInstanciaLogic logic;

    public Admin() {
        //constructor para la parte web
    }

    @Inject
    public Admin(EjercicioInstanciaLogic logic) {
        this.logic = logic;
    }
    
    @GET
    public void getAll() throws BusinessLogicException {
       logic.calcularCumplimento();
    }
}
