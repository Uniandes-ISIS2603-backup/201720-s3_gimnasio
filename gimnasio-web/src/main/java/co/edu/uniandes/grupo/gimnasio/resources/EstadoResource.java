/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.grupo.gimnasio.resources;
import co.edu.uniandes.baco.gimnasio.ejb.*;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author js.palacios437
 */
@Path("Estado")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstadoResource {
 
  @Inject
  EstadoLogic logic;

}
