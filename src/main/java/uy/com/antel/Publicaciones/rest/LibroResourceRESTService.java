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
import uy.com.antel.Publicaciones.model.Libro;
/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/libros")
@RequestScoped
public class LibroResourceRESTService {

   @GET
   @Produces("application/json")
   public List<Libro> listAll() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException, NamingException {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
    final List<Libro> resultado = new ArrayList<Libro>();
  	ManejadorBD mbd = new ManejadorBD();
  	Connection con = mbd.getConexion();
  	PreparedStatement ps = con.prepareStatement("select l from libros l order by titulo");
  	ResultSet rs = ps.executeQuery();
  	while (rs.next()) 
  	{
  		int id = rs.getInt("idLibro");
  		String titulo = rs.getString("titulo");
  		Date fecha = rs.getDate("fecha");
  		String isbn = rs.getString("isbn");
  		int idEditorial = rs.getInt("idEditorial");
  		Libro l = new Libro();
  		l.setId(id);
  		l.setTitulo(titulo);
  		l.setFecha(fecha);
  		l.setIsbn(isbn);
  		l.setId(idEditorial);
  		resultado.add(l);
  	}
  	rs.close();
  	ps.close();
  	con.close();
  	return resultado;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Libro lookupById(@PathParam("id") int id) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
	  List<Libro> resultado = new ArrayList<Libro>();
	  ManejadorBD mbd = new ManejadorBD();
	  Connection con = mbd.getConexion();
	  PreparedStatement ps = con.prepareStatement("select l from libros l where idLibro = ? order by titulo");
	  ResultSet rs = ps.executeQuery();
	  	while (rs.next()) 
	  	{
	  		String titulo = rs.getString("titulo");
	  		Date fecha = rs.getDate("fecha");
	  		String isbn = rs.getString("isbn");
	  		int idEditorial = rs.getInt("idEditorial");
	  		Libro l = new Libro();
	  		l.setId(id);
	  		l.setTitulo(titulo);
	  		l.setFecha(fecha);
	  		l.setIsbn(isbn);
	  		l.setId(idEditorial);
	  		resultado.add(l);
	  	}
	  	rs.close();
	  	ps.close();
	  	con.close();
	  	return (Libro) resultado;
   }
}
