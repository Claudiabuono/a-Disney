package coreModels.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import coreModels.beans.FatturaBean;
import coreModels.beans.Order;
import coreModels.beans.ProductBean;
import coreModels.beans.Registered;

public abstract class FatturaModel {
	
	private void setOrder (ResultSet rs, Order bean) throws SQLException{
		//fattura, prodotto, prezzoAp, quantita, ivaAp, scontoAp
		ProductBean item = new ProductBean ();
		
		item.setCode(rs.getInt("codice"));
		item.setName(rs.getString("nome"));
		item.setCategory(rs.getInt("categoria"));
		item.setDescription(rs.getString("descrizione"));
		item.setCharacter(rs.getString("personaggio"));
		item.setPhoto(rs.getString("foto"));
		item.setTipo(rs.getString("tipo"));
		item.setIva(rs.getDouble("ivaAp"));
		item.setDiscount(rs.getDouble("scontoAp"));
		item.setPrice(rs.getInt("prezzoAp"));
		
		bean.setProduct(item);
		bean.setQty(rs.getInt("qty"));
	}
	
	//codiceFattura, registrato, dataFattura, Indirizzo
	//fattura, prodotto, prezzoAp, quantita, ivaAp, scontoAp
	
	public void doSave (FatturaBean f) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null, insert = null;
		java.util.Date da = new java.util.Date();

		try {
			connection = getConnection();
			if(updateQtyProducts (f.getProdotti(), connection)) {
				preparedStatement = connection.prepareStatement(newFattura, java.sql.Statement.RETURN_GENERATED_KEYS);
	
				preparedStatement.setString(1, f.getUser().getLogin());
				preparedStatement.setDate(2, new java.sql.Date(da.getTime()));
				preparedStatement.setInt(3, f.getShipping().getCodice());
				preparedStatement.executeUpdate();
		
				ResultSet rs = preparedStatement.getGeneratedKeys();
			
				if (rs.next()) {
					f.setCod(rs.getInt(1));
					java.util.List<Order> list = f.getProdotti();
				
					for (Order o : list) {
						ProductBean bean = o.getProduct();
						insert = connection.prepareStatement(insertOrder);
						insert.setInt(1, f.getCod());
						insert.setInt(2, bean.getCode());
						insert.setBigDecimal(3, bean.getPrice());
						insert.setInt(4, o.getQty());
						insert.setDouble(5, bean.getIva());
						insert.setDouble(6, bean.getDiscount());
					
						insert.executeUpdate();
					}
					
				}
			}else throw new Exception();
			
		} finally {
			try {
				if (preparedStatement != null || insert != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}
	}
	
	private boolean updateQtyProducts(List<Order> prodotti, Connection connection) throws java.sql.SQLException {
		PreparedStatement preparedStatement = null;
		String updateProd = "UPDATE prodotto SET quantita = quantita - ? where codice = ?";
		int cont = 0;
		try {

			for (Order o : prodotti) {
				
				ProductBean bean = o.getProduct();
				preparedStatement = connection.prepareStatement(updateProd);
				preparedStatement.setInt(1, o.getQty());
				preparedStatement.setInt(2, bean.getCode());
			
				preparedStatement.executeUpdate();
				cont++;
			}
			
		} catch (SQLException e) {
			String updateBack = "UPDATE prodotto SET quantita = quantita + ? where codice = ?";
			for (int i = 0; i < cont; i++) {
				Order o = prodotti.get(i);
				ProductBean bean = o.getProduct();
				preparedStatement = connection.prepareStatement(updateBack);
				preparedStatement.setInt(1, o.getQty());
				preparedStatement.setInt(2, bean.getCode());
				
				preparedStatement.executeUpdate();
			}
			if (preparedStatement != null)
				preparedStatement.close();
			return false;
		}
		return true;
	}

	public boolean hasPurchased (coreModels.beans.ProductBean product, Registered e) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql= "select * from ordine join fattura on fattura = codiceFattura where registrato = ? and prodotto = ?";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, e.getLogin());
			preparedStatement.setInt(2, product.getCode());
			
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}
		return false;
	}

	public java.util.List<Order> retrieveOrders (Registered e, java.util.Date date, java.util.Date date2) throws java.sql.SQLException {
		Connection connection = null;
		SimpleDateFormat s = new SimpleDateFormat ("yyyy-MM-dd");
		String da = date != null ? s.format(date) : null;
		String a = date2 != null ? s.format(date2) : null;
		PreparedStatement preparedStatement = null;
		String selectSQL = da != null && a != null ? retrieveOrders + " AND dataFattura BETWEEN "+da +" AND " +a : da == null && a == null ? retrieveOrders: da == null && a!= null ? retrieveOrders +" AND dataFattura < " +a : retrieveOrders +" AND dataFattura > " +da;

		java.util.ArrayList<Order> products = new java.util.ArrayList<Order>();

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL + " ORDER BY fattura");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Order bean = new Order();
				
				setOrder(rs, bean);
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}
		return products;
	}
	
	//"SELECT FROM * " +ORDER_TABLE +" WHERE fattura = ?";
	private java.util.List<Order> retrieveInvoiceOrders (int fattura, Connection connection) throws java.sql.SQLException {
		PreparedStatement preparedStatement = null;
		java.util.List<Order> list = new java.util.ArrayList<Order>();
		
		preparedStatement = connection.prepareStatement(retrieveInvoiceorders);
		preparedStatement.setInt(1, fattura);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Order o = new Order ();
			
			setOrder(rs, o);
			list.add(o);
		}
		
		return list;
	}
	
	public FatturaBean retrieveInvoice (Registered user, int code) throws java.sql.SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		FatturaBean f = new FatturaBean ();
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(retrieveInvoice);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setInt(2, code);
			
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				java.util.GregorianCalendar cl = new java.util.GregorianCalendar();
				cl.setTime(rs.getDate("dataFattura"));
				
				f.setCod(rs.getInt("codiceFattura"));
				f.setProdotti(this.retrieveInvoiceOrders(f.getCod(), connection));
				f.setUser(new coreModels.model.RegisteredModelDM().doRetrieveByKey(user.getLogin()));
				f.setShipping(new AddressModelDM().doRetrieve(rs.getInt("Indirizzo")));
				f.setDate(cl);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}

		return f;
	}
	
	public java.util.List<FatturaBean> retrieveInvoices (Registered e, java.util.Date date, java.util.Date date2) throws java.sql.SQLException {
		Connection connection = null;

		PreparedStatement preparedStatement = null;
		String selectSQL = retrieveAllInvoices + " WHERE registrato = ?";
		
		java.util.List<FatturaBean> list = new java.util.ArrayList<FatturaBean> ();

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			if (date != null && date2 != null) {
				selectSQL = selectSQL + " AND dataFattura BETWEEN ? AND ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, e.getLogin());
				preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
				preparedStatement.setDate(3, new java.sql.Date(date2.getTime()));
			} else if (date != null && date2 == null) {
				selectSQL = selectSQL + " AND dataFattura > ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, e.getLogin());
				preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
			} else if (date == null && date2 != null) {
				selectSQL = selectSQL + " AND dataFattura < ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, e.getLogin());
				preparedStatement.setDate(2, new java.sql.Date(date2.getTime()));
			} else {
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, e.getLogin());
			}
				
			
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FatturaBean f = new FatturaBean();
				
				java.util.GregorianCalendar cl = new java.util.GregorianCalendar();
				cl.setTime(rs.getDate("dataFattura"));
				
				f.setCod(rs.getInt("codiceFattura"));
				f.setProdotti(this.retrieveInvoiceOrders(f.getCod(), connection));
				f.setShipping(new AddressModelDM().doRetrieve(rs.getInt("Indirizzo")));
				f.setDate(cl);
				f.setUser(e);
				
				list.add(f);
			}
		
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}
		return list;
	}
	
	public List<FatturaBean> retrieveInvoices(java.util.Date date, java.util.Date date2) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;

		PreparedStatement preparedStatement = null; 
		String selectSQL = retrieveAllInvoicesUsers;
		java.util.List<FatturaBean> list = new java.util.ArrayList<FatturaBean> ();

		try {
			connection = getConnection();
			
			if (date != null && date2 != null) {
				selectSQL = selectSQL + " WHERE dataFattura BETWEEN ? AND ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
				preparedStatement.setDate(2, new java.sql.Date(date2.getTime()));
			} else if (date != null && date2 == null) {
				selectSQL = selectSQL + " WHERE dataFattura > ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
			} else if (date == null && date2 != null) {
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setDate(1, new java.sql.Date(date2.getTime()));
			} else 
				preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FatturaBean f = new FatturaBean();
				Registered bean = new Registered ();
				java.util.GregorianCalendar cl = new java.util.GregorianCalendar();
				cl.setTime(rs.getDate("dataFattura"));

				RegisteredModel.setBean(rs, bean);
				f.setCod(rs.getInt("codiceFattura"));
				f.setProdotti(retrieveInvoiceOrders(f.getCod(), connection));
				f.setShipping(new AddressModelDM().doRetrieve(rs.getInt("Indirizzo")));
				f.setDate(cl);
				f.setUser(bean);
				
				list.add(f);
			}
	
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					closeConnection(connection);
			}
		}
		return list;
	}
	
	public abstract void closeConnection(Connection connector) throws SQLException;
	public abstract Connection getConnection () throws SQLException;
	
	private static final String ORDER_TABLE = "ordine";
	private static final String PROD_TABLE = "prodotto";
	private static final String FATT_TABLE = "fattura";
	private static final String newFattura = "INSERT INTO " +FATT_TABLE +" (registrato, dataFattura, Indirizzo) VALUES (?,?,?)";
	private static final String retrieveInvoice = "SELECT * FROM " +FATT_TABLE +" WHERE registrato = ? AND codiceFattura = ?";
	private static final String retrieveInvoiceorders = "SELECT * FROM " +ORDER_TABLE +" JOIN " +PROD_TABLE + " ON codice = prodotto  WHERE fattura = ?";
	private static final String retrieveAllInvoices = "SELECT * FROM " +FATT_TABLE;
	private static final String retrieveAllInvoicesUsers = "SELECT * FROM " +FATT_TABLE +" JOIN " +RegisteredModel.TABLE + " ON loginA = registrato ";
	private static final String insertOrder = "INSERT INTO "+ORDER_TABLE+" VALUES (?, ?, ?, ?, ?, ?)";
	private static final String retrieveOrders = "SELECT * FROM "+ORDER_TABLE +" JOIN " +PROD_TABLE + " ON codice = prodotto WHERE registrato = ?";
	
}
