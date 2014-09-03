package uy.com.antel.Publicaciones.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.naming.NamingException;

import uy.com.antel.Publicaciones.model.Editorial;

@RequestScoped
public class EditorialListProducer {

   private List<Editorial> editoriales;

   ManejadorBD mbd = new ManejadorBD();
   
   @Produces
   @Named
   public List<Editorial> getEditorial() {
      return editoriales;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Editorial editorial) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, NamingException {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException {
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
  		editoriales.add(e);
  	}
  	rs.close();
  	ps.close();
  	con.close();
   }
}
