package uy.com.antel.Publicaciones.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Revista;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/sexos")
@RequestScoped
public class RevistaResourceRESTService {

   @GET
   @Produces("application/json")
   public List<Revista> listAll() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
      final List<Revista> resultado = new ArrayList<Revista>();
    	ManejadorBD mbd = new ManejadorBD();
    	Connection con = mbd.getConexion();
    	PreparedStatement ps = con.prepareStatement("select r from revistas r order by titulo");
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) 
    	{
    		int id = rs.getInt("idRevistas");
    		String titulo = rs.getString("titulo");
    		String numero = rs.getString("numero");
    		Date fecha = rs.getDate("fecha");
    		int idEditorial = rs.getInt("idEditorial");
    		Revista r = new Revista();
    		r.setId(id);
    		r.setTitulo(titulo);
    		r.setNumero(numero);
    		r.setFecha(fecha);
    		r.setId(idEditorial);
    		resultado.add(r);
    	}
    	rs.close();
    	ps.close();
    	con.close();
    	return resultado;
     }

     @GET
     @Path("/{id:[0-9][0-9]*}")
     @Produces("application/json")
     public Revista lookupById(@PathParam("id") int id) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
  	  List<Revista> resultado = new ArrayList<Revista>();
  	  ManejadorBD mbd = new ManejadorBD();
  	  Connection con = mbd.getConexion();
  	  PreparedStatement ps = con.prepareStatement("select r from revistas r where idRevistas = ? order by titulo");
  	  ResultSet rs = ps.executeQuery();
  	  	while (rs.next()) 
  	  	{
  	  		String titulo = rs.getString("titulo");
  	  		String numero = rs.getString("numero");
  	  		Date fecha = rs.getDate("fecha");
  	  		int idEditorial = rs.getInt("idEditorial");
  	  	Revista r = new Revista();
  	  		r.setId(id);
  	  		r.setTitulo(titulo);
  	  		r.setNumero(numero);
  	  		r.setFecha(fecha);
  	  		r.setId(idEditorial);
  	  		resultado.add(r);
  	  	}
  	  	rs.close();
  	  	ps.close();
  	  	con.close();
  	  	return (Revista) resultado;
     }
}
