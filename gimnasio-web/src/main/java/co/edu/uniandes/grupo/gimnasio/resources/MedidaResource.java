/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.grupo.gimnasio.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.baco.gimnasio.ejb.*;
/**
 *
 * @author js.palacios437
 */

@Path("Medidas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class MedidaResource {
    
@Inject    
MedidaLogic logic;
}
