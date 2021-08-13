package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coreModels.beans.Address;
import coreModels.connector.DriverManagerConnectionPool;

public class AddressModel {

	protected void setBean (ResultSet rs, Address bean) throws SQLException {
		bean.setCodice(rs.getInt("codiceIndirizzo"));
		bean.setCAP(rs.getInt("cap"));
		bean.setStreet(rs.getString("via"));
		bean.setnCv(rs.getInt("nCivico"));
		bean.setCitta(rs.getString("citta"));
		bean.setProvince(rs.getString("provincia"));
		bean.setNation(rs.getString("stato"));
	}
	protected void prepareInsertStatement (PreparedStatement preparedStatement, coreModels.beans.Registered e , Address address) throws SQLException {
		preparedStatement.setString(1, e.getLogin());
		preparedStatement.setString(2, address.getStreet());
		preparedStatement.setInt(3,address.getCAP());
		preparedStatement.setInt(4, address.getnCv());
		preparedStatement.setString(5, address.getCitta());
		preparedStatement.setString(6, address.getProvince());
		preparedStatement.setString(7, address.getNation());
		//registrato, via, cap,  nCivico, citta,  provincia, stato
	}
	
	private int retrieveCode (Address address, String nomeUtente, Connection connection) throws SQLException {
		String checkSQL = "SELECT codiceIndirizzo FROM indirizzo WHERE registrato = ? AND via = ? AND cap = ? AND nCivico = ? AND citta = ? AND provincia = ? AND stato = ?";
		PreparedStatement check = connection.prepareStatement(checkSQL);
		//registrato, via, cap,  nCivico, citta, provincia, stato
		check.setString(1, nomeUtente);
		check.setString(2, address.getStreet());
		check.setInt(3, address.getCAP());
		check.setInt(4, address.getnCv());
		check.setString(5, address.getCitta());
		check.setString(6, address.getProvince());
		check.setString(7, address.getNation());
		
		ResultSet rs = check.executeQuery();
		
		return rs.next() ? rs.getInt(1) : -1; 
	}
	
	public int doSave(Address address, coreModels.beans.Registered user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//registrato, via, cap,  nCivico, citta, provincia, stato
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			prepareInsertStatement(preparedStatement, user, address);
			
			preparedStatement.executeUpdate();
			int code = retrieveCode (address, user.getLogin(), connection);
			
			return code;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		
	}

	public boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	public java.util.Map<Integer, Address> doRetrieveAll(String nomeUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		java.util.HashMap<Integer, Address> products = new java.util.HashMap<Integer, Address>();
		System.out.println(nomeUtente);
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectAllSQL);
			preparedStatement.setString(1, nomeUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Address bean = new Address();
				
				setBean(rs, bean);
				products.put(bean.getCodice(), bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;

	}


	public Address doRetrieve(int code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Address address = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				address = new Address();
				setBean(rs, address);
			}
				
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return address;

	}

	protected static final String TABLE_NAME = "indirizzo";
	protected static final String selectAllSQL = "SELECT * FROM " + TABLE_NAME +" WHERE registrato = ?";
	protected static final String deleteSQL = "UPDATE "+TABLE_NAME+" SET registrato = null WHERE codiceIndirizzo = ?";
	protected static final String insertSQL = "INSERT INTO indirizzo " 
			+ "(registrato, via, cap,  nCivico, citta, provincia, stato)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE codiceIndirizzo = ?";
	
}
