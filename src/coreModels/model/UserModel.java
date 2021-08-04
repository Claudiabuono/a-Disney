package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coreModels.beans.UserBean;
import coreModels.connector.DriverManagerConnectionPool;

public abstract class UserModel {
	
	public boolean doesExist (UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT loginA FROM ((SELECT loginA, pass FROM "+RegisteredModel.TABLE+") UNION (select * from "+AdminModel.TABLE+")) t WHERE loginA = ?;";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user.getLogin());
			ResultSet rs = preparedStatement.executeQuery();
			
			return rs.next() ? true : false;
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
	}
	
	public UserBean login (String username, String password) throws SQLException{
		UserBean user = doRetrieveByKey(username);
		
		if (user == null ? false : user.getPassword().equals(password))
			return user;
		else return null;
	}
	
	protected abstract UserBean doRetrieveByKey (String username) throws SQLException;

}
