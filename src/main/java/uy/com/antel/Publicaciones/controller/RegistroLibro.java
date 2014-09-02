package uy.com.antel.Publicaciones.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Editorial;
import uy.com.antel.Publicaciones.model.Libro;
import uy.com.antel.formmrree.model.Funcionario;


@Stateful
@Model
public class RegistroLibro {
	   ManejadorBD mbd = new ManejadorBD();
		
	   private Libro newLibro;
	   @Inject
	   private Event<Libro> LibroEventSrc;

	   @Produces
	   @Named
	   public Libro getnewLibro() {
	      return newLibro;
	   }

	   public void registro() throws Exception {
       Connection con = mbd.getConexion();
       String insLibro = "insert into libros (titulo, fecha, isbn, idEditorial) values (?,?,?,?)";
       PreparedStatement pstmt = con.prepareStatement(insLibro);
       pstmt.setString(1, newLibro.getTitulo());
       pstmt.setDate(1, (java.sql.Date) newLibro.getFecha());
       pstmt.setString(1, newLibro.getIsbn());
       pstmt.setInt(1, newLibro.getIdEditorial());
       int res = pstmt.executeUpdate();
       System.out.println("filas insertadas" + res);
	      pstmt.close();
	      con.close();
	      LibroEventSrc.fire(newLibro);
	      initnewLibro();
	   }
	   
	   public void modificar(Libro libro) throws Exception {
	          Connection con = mbd.getConexion();
	          String updLibro = "update table libros set titulo = ?, isbn = ? where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(updLibro);
	          pstmt.setString(1, libro.getTitulo());
	          pstmt.setString(2, libro.getIsbn());
	          int res = pstmt.executeUpdate();
	          System.out.println("filas insertadas" + res);
		      pstmt.close();
		      con.close();
		      LibroEventSrc.fire(newLibro);
		      initnewLibro();		   
	   }
	   
	   public void eliminar(int id) throws Exception {
	          Connection con = mbd.getConexion();
	          String delLibro = "delete from libros where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(delLibro);
	          pstmt.setInt(1, id);
	          int res = pstmt.executeUpdate();
	          System.out.println("filas insertadas" + res);
		      pstmt.close();
		      con.close();
		      LibroEventSrc.fire(newLibro);
		      initnewLibro();	
	   }

	   @PostConstruct
	   public void initnewLibro() {
		   newLibro = new Libro();
	   }
}
