package uy.com.antel.Publicaciones.data;

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

import uy.com.antel.Publicaciones.model.Libro;

@RequestScoped
public class LibroListProducer {

   private List<Libro> libros = new ArrayList<Libro>();

   ManejadorBD mbd = new ManejadorBD();

public List<Libro> getLibros() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException, NamingException {
	libros.clear();
	Connection con = mbd.getConexion();
 	PreparedStatement ps = con.prepareStatement("select l.* from libros l order by titulo");
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
	return libros;
}

}
