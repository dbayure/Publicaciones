package uy.com.antel.Publicaciones.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.naming.NamingException;

import uy.com.antel.Publicaciones.model.Libro;

@RequestScoped
@Local ({LibroListProducer.class, LibroListProducer.class})
public class LibroListProducer {

   private List<Libro> libros;

   ManejadorBD mbd = new ManejadorBD();
   
   @Produces
   @Named
   public List<Libro> getLibros() {
      return libros;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Libro libro) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, NamingException {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
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
  		libros.add(l);
   	}
   	rs.close();
   	ps.close();
   	con.close();
   }
}
