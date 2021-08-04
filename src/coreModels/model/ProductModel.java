package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coreModels.beans.ProductBean;
import coreModels.connector.DriverManagerConnectionPool;

public class ProductModel {
	
	private void setBean (ResultSet rs, ProductBean item) throws SQLException {
		item.setCode(rs.getInt("codice"));
		item.setName(rs.getString("nome"));
		item.setCategory(rs.getInt("categoria"));
		item.setDescription(rs.getString("descrizione"));
		item.setCharacter(rs.getString("personaggio"));
		item.setPhoto(rs.getString("foto"));
		item.setTipo(rs.getString("tipo"));
		item.setIva(rs.getDouble("iva"));
		item.setDiscount(rs.getDouble("sconto"));
		item.setPrice(rs.getDouble("prezzo"));
		item.setQty(rs.getInt("quantita"));
	}
	
	private void prepareInsertStatement (PreparedStatement preparedStatement, ProductBean item) throws SQLException {
		
		preparedStatement.setString(1, item.getName());
		preparedStatement.setString(2, item.getDescription());
		preparedStatement.setBigDecimal(3, item.getPrice());
		preparedStatement.setInt(4, item.getQty());
		preparedStatement.setString(5, item.getCharacter());
		preparedStatement.setString(6, item.getPhoto());
		preparedStatement.setDouble(7, item.getIva());
		preparedStatement.setDouble(8, item.getDiscount());
		preparedStatement.setString(9, item.getTipo());
		preparedStatement.setInt(10, item.getCategory());
	
	}
	
	private void prepareSQLlist (PreparedStatement statement, int [] codes) throws SQLException {
		
		int size = codes.length;
		
		for (int i = 0; i < size; i++) {
			statement.setInt(i + 1, codes[i]);
		}
	}
	
	private String setSQLlistString (int size) {
		String SQLlist = selectAllSQL + " WHERE codice = ? ";
		
		for (int i = 0; i < size - 1; i++)
			SQLlist = SQLlist + "OR codice = ? ";
		
		return SQLlist;
	}

	public synchronized boolean doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			prepareInsertStatement(preparedStatement, product);

			result =preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();

				}
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);

			}
		}return (result != 0);
	}
	
	public synchronized ProductBean doRetrieveByKey(int code, Boolean acquistabile) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		try {
			String selectSql = acquistabile == null ? selectSQL : selectSQL + " AND acquistabile = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, code);
			if (acquistabile) preparedStatement.setBoolean(2, acquistabile);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
				setBean(rs, bean);
				
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	public synchronized boolean doDelete(int code) throws SQLException {
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
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	

	public synchronized java.util.List<ProductBean> doRetrieveAll(Boolean acquistabile) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		java.util.ArrayList<ProductBean> products = new java.util.ArrayList<ProductBean>();
		String selectSQL = acquistabile == null ? selectAllSQL : selectAllSQL + " WHERE acquistabile = ? ORDER BY nome";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setBoolean(1, acquistabile.booleanValue());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ProductBean bean = new ProductBean();

				setBean(rs, bean);
				products.add(bean);
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
		return products;
	}
	

	public synchronized java.util.List<ProductBean> doRetrieveByCategory(int ctgy) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		java.util.ArrayList<ProductBean> products = new java.util.ArrayList<ProductBean>();

		String selectSQL = categorySQL + order;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, ctgy);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();			
				setBean(rs, bean);
				products.add(bean);

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
		return products;
	}

	public synchronized java.util.List<ProductBean> doRetrieveBySearch(String search, Boolean acquistabile) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		java.util.ArrayList<ProductBean> products = new java.util.ArrayList<ProductBean>();
		String acquist = acquistabile == null ? "" : " AND acquistabile = ?"; 
		String selectSQL = searchSQL +acquist + order;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%"+search+"%");
			preparedStatement.setString(2, "%"+search+"%");
			if (acquistabile) preparedStatement.setBoolean(3, acquistabile);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				setBean(rs, bean);
				products.add(bean);
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
		return products;
	}
	
	public synchronized List<ProductBean> doRetrieveByDiscount(double amount, Boolean acquista) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<ProductBean> list = new ArrayList<ProductBean>();
		String selectSQL = acquista == null ? selectDiscountSQL : selectDiscountSQL + " AND acquistabile = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDouble(1, amount);
			if (acquista) preparedStatement.setBoolean(2, acquista);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next())
			{	
				int qty = result.getInt("quantita");
				
				if (qty > 0)
				{
					ProductBean bean = new ProductBean();
					setBean(result, bean);
					list.add(bean);
				}
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
	
	public synchronized boolean doUpdate(ProductBean item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//prezzo = ?, quantita = ?, iva = ?, sconto = ?
		int result = 0;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setBigDecimal(1, item.getPrice());
			preparedStatement.setInt(2, item.getQty());
			preparedStatement.setDouble(3, item.getIva());
			preparedStatement.setDouble(4, item.getDiscount());
			preparedStatement.setInt(5, item.getCode());
			
			result = preparedStatement.executeUpdate();

		} finally {
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

	public synchronized List<ProductBean> doRetrieveList(int[] codes, Boolean acquista) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String a = acquista == null ? "" :  " AND acquistabile = ?";
		String query = setSQLlistString(codes.length) +a;
		java.util.List <ProductBean> list = new java.util.ArrayList <ProductBean> ();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			prepareSQLlist(preparedStatement, codes);
			if (acquista) preparedStatement.setBoolean(codes.length + 1, acquista);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean ();
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

	
    protected static final String TABLE_NAME = "prodotto";
	protected static final String selectSQL = "SELECT * FROM " +TABLE_NAME + " WHERE codice = ?";
	protected static final String selectAllSQL = "SELECT * FROM " + TABLE_NAME;
	protected static final String selectDiscountSQL = "SELECT * FROM store.prodotto WHERE sconto >= ?";
	protected static final String deleteSQL = "UPDATE "+TABLE_NAME+" SET acquistabile = false WHERE codice = ?";
	protected static final String insertSQL = "INSERT INTO " + TABLE_NAME
			+ "(nome, descrizione, prezzo, quantita, personaggio, foto, iva, sconto, tipo, categoria)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	protected static final String updateSQL = "UPDATE prodotto SET "
			+ "prezzo = ?, quantita = ?, iva = ?, sconto = ?"
			+ " WHERE codice = ?";
	protected static final String searchSQL = "SELECT * FROM "+TABLE_NAME+ " WHERE nome LIKE ? or descrizione LIKE ?";
	protected static final String categorySQL = "SELECT * FROM "+TABLE_NAME+ " WHERE categoria = ? AND acquistabile = 1";
	protected static final String order = " ORDER BY nome";
}
