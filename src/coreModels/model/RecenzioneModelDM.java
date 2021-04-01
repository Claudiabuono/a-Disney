package coreModels.model;

public class RecenzioneModelDM extends RecenzioneModel{
	
	@Override
	public java.sql.Connection getConnection() throws java.sql.SQLException {
		// TODO Auto-generated method stub
		return DriverMaagerConnectionPool.getConnection();
	}

	@Override
	public void closeConnection(java.sql.Connection connector) throws java.sql.SQLException {
		// TODO Auto-generated method stub
		DriverMaagerConnectionPool.releaseConnection(connector);
	}
}
