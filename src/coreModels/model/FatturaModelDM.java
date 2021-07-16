package coreModels.model;

import coreModels.connector.DriverMaagerConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class FatturaModelDM extends FatturaModel {
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
