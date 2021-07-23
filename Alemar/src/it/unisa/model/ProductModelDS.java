package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import it.unisa.model.ImageControl;


public class ProductModelDS implements ProductModel {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/alemarsport");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String PRODOTTO = "prodotto";

	
	public synchronized ProductBean doRetrieveByKey(String x) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductBean bean = new ProductBean();
		
		String selectSQL = "SELECT * FROM " + ProductModelDS.PRODOTTO + " WHERE IDProdotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, x);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getString("IDPRODOTTO"));
				bean.setName(rs.getString("NOME"));
				bean.setDescription(rs.getString("DESCRIZIONEPRODOTTO"));
				bean.setPrice(rs.getFloat("PREZZONOIVA"));
				bean.setSconto(rs.getInt("SCONTO"));
				bean.setQuantity(rs.getInt("DISPONIBILITà"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	
	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDS.PRODOTTO;

		if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getString("IDPRODOTTO"));
				bean.setName(rs.getString("NOME"));
				bean.setDescription(rs.getString("DESCRIZIONEPRODOTTO"));
				bean.setPrice(rs.getFloat("PREZZONOIVA"));
				bean.setSconto(rs.getInt("SCONTO"));
				bean.setQuantity(rs.getInt("DISPONIBILITà"));
				prodotti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return prodotti;
	}
	

	@Override
	public synchronized void doSave(ProductBean prodotto) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PRODOTTO
				+ " (IDPRODOTTO, C_CodiceCategoria, Sconto, Disponibilità, DescrizioneProdotto, PrezzoNoIVA, Nome) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, prodotto.getCode());
			preparedStatement.setString(2, prodotto.getCodiceCat());
			preparedStatement.setInt(3, prodotto.getSconto());
			preparedStatement.setInt(4, prodotto.getQuantity());
			preparedStatement.setString(5, prodotto.getDescription());
			preparedStatement.setFloat(6, prodotto.getPrice());
			preparedStatement.setString(7, prodotto.getName());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	//	Saveimage(prodotto.getCode(), img);
	}
	
	public synchronized void doUpdate(String id,String x, String set) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "UPDATE " + PRODOTTO
				+ " SET "+set+"= ?  WHERE IDProdotto= '"+id+"'";
		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			if ((set.equals("IDProdotto"))||(set.equals("C_CodiceCategoria"))||(set.equals("Nome"))||(set.equals("DescrizioneProdotto"))) 
				preparedStatement.setString(1, x);
			
			if((set.equals("Disponibilità"))||(set.equals("Sconto"))) preparedStatement.setInt(1, Integer.parseInt(x));
			
			if (set.equals("PrezzoNoIVA")) preparedStatement.setFloat(1, Float.parseFloat(x));
		

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

}