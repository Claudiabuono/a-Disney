package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coreModels.beans.Admin;
import coreModels.beans.UserBean;
import coreModels.connector.DriverManagerConnectionPool;

public class AdminModel extends UserModel{

	@Override
	public UserBean doRetrieveByKey(String user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserBean bean = null;
		String selectSQL = "SELECT * FROM " +TABLE +" WHERE loginA = ?";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) 
			{
				bean = new Admin();
	
				bean.setLogin(user);
				bean.setPassword(rs.getString("pass"));
			}

		} 
		finally 
		{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}
	
	protected static final String TABLE = "amministratore";
	protected static final String selectSQL = "SELECT * FROM " + TABLE + " WHERE loginA = ?";
}
