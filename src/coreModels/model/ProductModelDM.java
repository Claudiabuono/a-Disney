package coreModels.model;

public class ProductModelDM extends ProductModel {

	@Override
	protected java.sql.Connection getConnection() throws java.sql.SQLException {
		// TODO Auto-generated method stub
		return DriverMaagerConnectionPool.getConnection();
	}

	@Override
	protected void closeConnection(java.sql.Connection connector) throws java.sql.SQLException {
		// TODO Auto-generated method stub
		DriverMaagerConnectionPool.releaseConnection(connector);
	}

}