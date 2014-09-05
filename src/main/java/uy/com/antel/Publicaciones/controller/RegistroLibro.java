package uy.com.antel.Publicaciones.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ejb.Stateful;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Libro;


@Stateful
public class RegistroLibro {
	   ManejadorBD mbd = new ManejadorBD();

	   public void registro(Libro newLibro) throws Exception {
       Connection con = mbd.getConexion();
       String insLibro = "insert into libros (titulo, fecha, isbn, idEditorial) values (?,?,?,?)";
       PreparedStatement pstmt = con.prepareStatement(insLibro);
       pstmt.setString(1, newLibro.getTitulo());
       pstmt.setDate(2, (java.sql.Date) newLibro.getFecha());
       pstmt.setString(3, newLibro.getIsbn());
       pstmt.setInt(4, newLibro.getIdEditorial());
       int res = pstmt.executeUpdate();
       System.out.println("filas insertadas" + res);
	      pstmt.close();
	      con.close();
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
	   }

}
