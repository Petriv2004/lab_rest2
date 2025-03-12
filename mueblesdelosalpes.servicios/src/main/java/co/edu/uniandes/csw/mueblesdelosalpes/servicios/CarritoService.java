/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCarritoMockLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author RayAj
 */
@Path("/Carrito")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarritoService {

    @EJB
    private IServicioCarritoMockLocal carritoEJB;

    @GET
    @Path("/inventario")
    public ArrayList<Mueble> obtenerInventario() {
        return carritoEJB.getInventario();
    }

    @PUT
    public void modificarInventario(ArrayList<Mueble> inventario) {
        carritoEJB.setInventario(inventario);
    }

    @GET
    @Path("/precioTotal")
    public double obtenerPrecioTotal() {
        return carritoEJB.getPrecioTotalInventario();
    }

    @GET
    @Path("/totalUnidades")
    public int obtenerTotalUnidades() {
        return carritoEJB.getTotalUnidades();
    }

    @POST
    @Path("/comprar")
    public void comprar(Usuario usuario) {
        carritoEJB.comprar(usuario);
    }

    @POST
    @Path("/agregar")
    public void agregarMueble(Mueble mueble) {
        carritoEJB.agregarItem(mueble);
    }

    @DELETE
    @Path("/remover/{referencia}")
    public void removerMueble(@PathParam("referencia") int referencia, @QueryParam("removerCero") boolean removerCero) {
        Mueble mueble = new Mueble();
        mueble.setReferencia(referencia);
        carritoEJB.removerItem(mueble, removerCero);
    }

    @PUT
    @Path("/recalcular")
    public void recalcularInventario() {
        carritoEJB.recalcularInventarioTotal();
    }

    @DELETE
    @Path("/limpiar")
    public void limpiarCarrito() {
        carritoEJB.limpiarLista();
    }

}
