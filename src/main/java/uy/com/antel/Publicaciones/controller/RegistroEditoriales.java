package uy.com.antel.Publicaciones.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Editorial;


@Stateful
@Model
public class RegistroEditoriales {
		
	   ManejadorBD mbd = new ManejadorBD();
	
	   private Editorial newEditorial;
	   @Inject
	   private Event<Editorial> EditorialEventSrc;

	   @Produces
	   @Named
	   public Editorial getNewEditorial() {
	      return newEditorial;
	   }

	   public void registro() throws Exception {
          Connection con = mbd.getConexion();
          String insEditorial = "insert into editoriales (nombre) values (?)";
          PreparedStatement pstmt = con.prepareStatement(insEditorial);
          pstmt.setString(1, newEditorial.getNombre());
          int res = pstmt.executeUpdate();
          System.out.println("filas insertadas" + res);
	      pstmt.close();
	      con.close();
	      EditorialEventSrc.fire(newEditorial);
	      initnewEditorial();
	   }
	   
	   public void modificar(Editorial editorial) throws Exception {
	          Connection con = mbd.getConexion();
	          String delEditorial = "update table editoriales set nombre = ? where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(delEditorial);
	          pstmt.setString(1, editorial.nombre);
	          pstmt.setInt(2, editorial.id);
	          int res = pstmt.executeUpdate();
	          System.out.println("filas insertadas" + res);
		      pstmt.close();
		      con.close();
		      EditorialEventSrc.fire(newEditorial);
		      initnewEditorial();		   
	   }
	   
	   public void eliminar(int id) throws Exception {
	          Connection con = mbd.getConexion();
	          String updEditorial = "delete from editoriales where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(updEditorial);
	          pstmt.setInt(1, id);
	          int res = pstmt.executeUpdate();
	          System.out.println("filas insertadas" + res);
		      pstmt.close();
		      con.close();
		      EditorialEventSrc.fire(newEditorial);
		      initnewEditorial();	
	   }

	   @PostConstruct
	   public void initnewEditorial() {
		   newEditorial = new Editorial();
	   }
}
