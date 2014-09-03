package uy.com.antel.Publicaciones.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Editorial;
/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/libros")
@RequestScoped
public class EditorialResourceRESTService {

   @GET
   @Produces("application/json")
   public List<Editorial> listAll() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException, NamingException {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
    final List<Editorial> resultado = new ArrayList<Editorial>();
  	ManejadorBD mbd = new ManejadorBD();
  	Connection con = mbd.getConexion();
  	PreparedStatement ps = con.prepareStatement("select e from editoriales e order by nombre");
  	ResultSet rs = ps.executeQuery();
  	while (rs.next()) 
  	{
  		int id = rs.getInt("idEditoriales");
  		String nombre = rs.getString("nombre");
  		Editorial e = new Editorial();
  		e.setId(id);
  		e.setNombre(nombre);
  		resultado.add(e);
  	}
  	rs.close();
  	ps.close();
  	con.close();
  	return resultado;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Editorial lookupById(@PathParam("id") int id) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
	  List<Editorial> resultado = new ArrayList<Editorial>();
	  ManejadorBD mbd = new ManejadorBD();
	  Connection con = mbd.getConexion();
	  PreparedStatement ps = con.prepareStatement("select e from editoriales e where idEditoriales = ? order by nombre");
	  ResultSet rs = ps.executeQuery();
	  	while (rs.next()) 
	  	{
	  		String nombre = rs.getString("nombre");
	  		Editorial e = new Editorial();
	  		e.setId(id);
	  		e.setNombre(nombre);
	  		resultado.add(e);
	  	}
	  	rs.close();
	  	ps.close();
	  	con.close();
	  	return (Editorial) resultado;
   }
}
