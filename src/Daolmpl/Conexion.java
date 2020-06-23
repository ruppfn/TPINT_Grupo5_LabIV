package Daolmpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

public class Conexion {		
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "ROOT";
	private String dbName = "sdg_educativa???profileSQL=true&useSSL=false";
	
	protected Connection connection;
	
	public Connection Open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(host+dbName, user, pass);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return this.connection;
	}
	
	public ResultSet query (String query) {
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public ResultSet sp (String sp) {
		CallableStatement cst;
		ResultSet rs = null;
		try {
			cst = connection.prepareCall(sp);
			rs = cst.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean executeSP (String sp) {
		CallableStatement cst;
		boolean save = true;
		try {
			cst = connection.prepareCall(sp);
			cst.executeUpdate();
		}
		catch(Exception e) {
			save = false;
			e.printStackTrace();
		}		
		return save;
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}
}	

