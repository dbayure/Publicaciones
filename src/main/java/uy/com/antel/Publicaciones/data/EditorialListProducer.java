package uy.com.antel.Publicaciones.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;

import uy.com.antel.Publicaciones.model.Editorial;

@RequestScoped
public class EditorialListProducer {

   private List<Editorial> editoriales = new ArrayList<Editorial>();

   ManejadorBD mbd = new ManejadorBD();
   
   public List<Editorial> getEditoriales() throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, NamingException {
	   	editoriales.clear();
	    Connection con = mbd.getConexion();
	 	PreparedStatement ps = con.prepareStatement("select e.* from editoriales e order by nombre");
	  	ResultSet rs = ps.executeQuery();
	  	while (rs.next()) 
	  	{
	  		int id = rs.getInt("ideditoriales");
	  		String nombre = rs.getString("nombre");
	  		Editorial e = new Editorial();
	  		e.setId(id);
	  		e.setNombre(nombre);
	  		System.out.println("editorial nombre: " + e.nombre + " identificador: " + e.getId());
	  		editoriales.add(e);
	  	}
	  	rs.close();
	  	ps.close();
	  	con.close();
      return editoriales;
   }

}
