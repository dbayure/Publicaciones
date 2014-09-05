package uy.com.antel.Publicaciones.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ejb.Stateful;

import uy.com.antel.Publicaciones.data.ManejadorBD;
import uy.com.antel.Publicaciones.model.Editorial;


@Stateful
public class RegistroEditoriales {

	   public ManejadorBD mbd = new ManejadorBD();

	   public void registro(Editorial newEditorial) throws Exception {
          Connection con = mbd.getConexion();
          String insEditorial = "insert into editoriales (nombre) values (?)";
          PreparedStatement pstmt = con.prepareStatement(insEditorial);
          pstmt.setString(1, newEditorial.getNombre());
          int res = pstmt.executeUpdate();
          System.out.println("filas insertadas" + res);
	      pstmt.close();
	      con.close();
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
	   }

}
