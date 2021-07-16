package coreModels.model.DM;

import coreModels.connector.DriverMaagerConnectionPool;
import coreModels.model.FatturaModel;

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
