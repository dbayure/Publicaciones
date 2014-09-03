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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.naming.NamingException;

import uy.com.antel.Publicaciones.model.Revista;

@RequestScoped
public class RevistaListProducer {

   private List<Revista> revistas;

   ManejadorBD mbd = new ManejadorBD();
   
   @Produces
   @Named
   public List<Revista> getRevistas() {
      return revistas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Revista revista) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, NamingException {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
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
   		revistas.add(r);
   	}
   	rs.close();
   	ps.close();
   	con.close();
   }
}
