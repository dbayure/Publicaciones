package uy.com.antel.Publicaciones.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManejadorBD {
	
	public Connection getConexion() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, NamingException
	{
		InitialContext initContext = new InitialContext();
		DataSource ds = (DataSource)initContext.lookup("java:jboss/datasources/formmrreeDS");
		Connection con = ds.getConnection();
		return con;
	}

}
