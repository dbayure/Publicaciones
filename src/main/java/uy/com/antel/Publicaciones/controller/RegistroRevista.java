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
import uy.com.antel.Publicaciones.model.Revista;


@Stateful
@Model
public class RegistroRevista {

	   ManejadorBD mbd = new ManejadorBD();
		
	   private Revista newRevista;
	   @Inject
	   private Event<Revista> revistaEventSrc;

	   @Produces
	   @Named
	   public Revista getnewRevista() {
	      return newRevista;
	   }

		public void registro() throws Exception {
	    Connection con = mbd.getConexion();
	    String insRevista = "insert into revistas (titulo, numero, fecha, idEditorial) values (?,?,?,?)";
	    PreparedStatement pstmt = con.prepareStatement(insRevista);
	    pstmt.setString(1, newRevista.getTitulo());
	    pstmt.setString(2, newRevista.getNumero());
	    pstmt.setDate(3, (java.sql.Date) newRevista.getFecha());
	    pstmt.setInt(4, newRevista.getIdEditorial());
	    int res = pstmt.executeUpdate();
	    System.out.println("filas insertadas" + res);
		      pstmt.close();
		      con.close();
		      revistaEventSrc.fire(newRevista);
		      initnewRevista();
		}
	   
	   public void modificar(Revista revista) throws Exception {
	          Connection con = mbd.getConexion();
	          String updRevista = "update table revistas set titulo = ?, numero = ? where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(updRevista);
	          pstmt.setString(1, revista.getTitulo());
	          pstmt.setString(2, revista.getNumero());
	          int res = pstmt.executeUpdate();
	          System.out.println("filas actualizadas" + res);
		      pstmt.close();
		      con.close();
		      revistaEventSrc.fire(newRevista);
		      initnewRevista();		   
	   }
	   
	   public void eliminar(int id) throws Exception {
	          Connection con = mbd.getConexion();
	          String delRevista = "delete from revistas where id = ?";
	          PreparedStatement pstmt = con.prepareStatement(delRevista);
	          pstmt.setInt(1, id);
	          int res = pstmt.executeUpdate();
	          System.out.println("filas eliminadas" + res);
		      pstmt.close();
		      con.close();
		      revistaEventSrc.fire(newRevista);
		      initnewRevista();	
	   }

	   @PostConstruct
	   public void initnewRevista() {
		   newRevista = new Revista();
	   }
}
