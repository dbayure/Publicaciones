package uy.com.antel.Publicaciones.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class librosListProducer {
	
public void mostrarEditoriales() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NamingException{	

	ManejadorBD mbd = new ManejadorBD();
	Connection con = mbd.getConexion();
	PreparedStatement ps = con.prepareStatement("select nombre from editoriales");
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
		System.out.println(rs.getString("nombre"));
	}
	rs.close();
	ps.close();
	con.close();
	}
}
