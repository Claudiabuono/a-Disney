package coreModels.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AddressModelDS extends AdressModel{
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/store");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	
	@Override
	public void closeConnection(java.sql.Connection connector) throws java.sql.SQLException {
		// TODO Auto-generated method stub
		connector.close();
	}

	@Override
	public java.sql.Connection getConnection() throws java.sql.SQLException {
		// TODO Auto-generated method stub
		return ds.getConnection();
	}
}
