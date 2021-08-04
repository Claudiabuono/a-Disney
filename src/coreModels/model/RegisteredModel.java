package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coreModels.beans.Registered;
import coreModels.connector.DriverManagerConnectionPool;

public class RegisteredModel extends UserModel {
	/*
	public synchronized boolean hasPurchased (beans.ProductBean prod, UserBean user) throws SQLException {
		
	}*/
	
	protected static void setBean (ResultSet rs, Registered bean) throws SQLException {
		bean.setName(rs.getString("nome"));
		bean.setCognome(rs.getString("cognome"));
		bean.setLogin(rs.getString("loginA"));
		bean.setPassword(rs.getString("pass"));
	}
	
	//TABLE = "registrato";
	public synchronized Registered doRetrieveByKey(String user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Registered bean = null;
		String selectSQL = "SELECT * FROM " +TABLE +" WHERE loginA = ?";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) 
			{
				bean = new Registered();
				
				bean.setName(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
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
	
	public boolean registration(Registered e) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, e.getLogin());
			preparedStatement.setString(2, e.getPassword());
			preparedStatement.setString(3, e.getName());
			preparedStatement.setString(4, e.getCognome());

			result = preparedStatement.executeUpdate();
		} 
		finally 
		{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();

				}
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);

			}
		}
		return (result != 0);
	}
	
	public boolean doModify (Registered bean, String name, String surname, String login, String pass) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, surname);
			preparedStatement.setString(5, bean.getLogin());


			result = preparedStatement.executeUpdate();
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
		return (result != 0);
	}
	
	public synchronized ArrayList<Registered> doRetrieveBySearch(String search) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		java.util.ArrayList<Registered> list = new java.util.ArrayList<Registered>();

		String selectSQL = searchSQL + "ORDER BY nome";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%"+search+"%");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Registered bean = new Registered();

				setBean(rs, bean);
				list.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return list;
	}
		
	
	protected static final String TABLE = "registrato";
	protected static final String insertSQL = "INSERT INTO " + TABLE + "(loginA, pass, nome, cognome) values (?, ?, ?, ?)";
	protected static final String updateSQL = "UPDATE " + TABLE + " set loginA = ?, pass = ?, nome = ?, cognome = ? where loginA = ?";
	protected static final String searchSQL = "SELECT * FROM "+TABLE+ " WHERE loginA LIKE ?";
	
}
