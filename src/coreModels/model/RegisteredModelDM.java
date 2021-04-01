package coreModels.model;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisteredModelDM extends RegisteredModel {

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverMaagerConnectionPool.getConnection();
	}

	@Override
	public void closeConnection(Connection connector) throws SQLException {
		// TODO Auto-generated method stub
		DriverMaagerConnectionPool.releaseConnection(connector);
	}

}
