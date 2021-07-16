package coreModels.model.DM;

import coreModels.connector.DriverMaagerConnectionPool;
import coreModels.model.AdminModel;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminModelDM extends AdminModel {

	
	@Override
	protected Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverMaagerConnectionPool.getConnection();
	}

	@Override
	protected void closeConnection(Connection connector) throws SQLException {
		// TODO Auto-generated method stub
		DriverMaagerConnectionPool.releaseConnection(connector);
	}


}
