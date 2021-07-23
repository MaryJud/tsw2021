package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAO 	
{
   private static final String TABLE_NAME = "ordine";
   private static final String TABLE_NAME2 = "prodotto";
   static Date x=new Date(System.currentTimeMillis());
 
   private static DataSource ds;
   static ResultSet rs = null; 
   static Connection connection = null;
   static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/alemarsport");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
   
   
   public static OrdineBean doRetrieveByKey(String x) throws SQLException {   
	   Connection connection = null;
	   PreparedStatement preparedStatement = null;
		
	   OrdineBean bean = new OrdineBean();
	   String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE IDOrdine= ?";
	   try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, x);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIDOrdine(rs.getString("IDORDINE"));
				bean.setDescrizioneProdotti(rs.getString("DESCRIZIONEPRODOTTI"));
				bean.setNumProdotti(rs.getInt("NUMPRODOTTI"));
				bean.setStatoOrdine(rs.getString("STATOORDINE"));
				bean.setDataSpedizione(rs.getDate("DATASPEDIZIONE"));
				bean.setDataConsegna(rs.getDate("DATACONSEGNA"));
				bean.setDataOrdine(rs.getDate("DATAORDINE"));
				bean.setPrezzoFinale(rs.getFloat("PREZZOFINALEORDINE"));
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

   public static Collection<OrdineBean> doRetrieveByKey2(int x, String d1,String d2) throws SQLException, ParseException {   
	   Connection connection = null;
	   PreparedStatement preparedStatement = null;
	   String selectSQL;
	  
	   Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
	   if(x!=0) {
		   selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE IDAccount= ?";
	   }
	   else {
		  
		   selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE DataSpedizione IN (?,?)";
	   }
	   try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			if(x!=0) {
				preparedStatement.setInt(1, x);
			} else {
				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
			}
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {				
				OrdineBean bean = new OrdineBean();
				bean.setIDOrdine(rs.getString("IDORDINE"));
				bean.setDescrizioneProdotti(rs.getString("DESCRIZIONEPRODOTTI"));
				bean.setNumProdotti(rs.getInt("NUMPRODOTTI"));
				bean.setStatoOrdine(rs.getString("STATOORDINE"));
				bean.setDataSpedizione(rs.getDate("DATASPEDIZIONE"));
				bean.setDataConsegna(rs.getDate("DATACONSEGNA"));
				bean.setDataOrdine(rs.getDate("DATAORDINE"));
				bean.setPrezzoFinale(rs.getFloat("PREZZOFINALEORDINE"));
				ordini.add(bean);
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
		return ordini;
	}

   
   public synchronized static void doSave(Cart c,AccountBean user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		   int g=x.getDate();
		   int m=x.getMonth();
		   int y=x.getYear();
		//String x="SELECT MAX(IDOrdine) FROM " + OrdineDAO.TABLE_NAME;
		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ "( DataSpedizione,NumProdotti, DescrizioneProdotti, StatoOrdine ,DataConsegna, DataOrdine, IDAccount,PrezzoFinaleOrdine) VALUES (?,?,?,?,?,?,?,?)";
		
		
		try {
			System.out.println("Giorni->"+g+"\n Mesi->"+m);
			java.sql.Date DataOrdine = new java.sql.Date(y, m, g);
			java.sql.Date DataConsegna = new java.sql.Date(y, m, g+6);
			java.sql.Date DataSpedizione = new java.sql.Date(y, m, g+2);
			connection = ds.getConnection();
				//PRIMA QUERY
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setDate(1, DataSpedizione);
				preparedStatement.setInt(2, c.DammiNumeroTotaleProdotti());
				preparedStatement.setString(3, c.DammiTutteLeDescrizioni());
				preparedStatement.setString(4, "Ordinato");
				preparedStatement.setDate(5,DataConsegna);
				preparedStatement.setDate(6,DataOrdine);
				preparedStatement.setInt(7,user.getIDAccount());
				preparedStatement.setFloat(8,c.DammiCostoTotaleCarrello());
				preparedStatement.executeUpdate();
				preparedStatement.close();				
		    																				
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
   public synchronized static void SetDisponibilità(int x,String S) throws SQLException {
	   Connection connection = null;
	   PreparedStatement preparedStatement = null;
	   String secondSQL="UPDATE " + OrdineDAO.TABLE_NAME2 +" SET DISPONIBILITà=DISPONIBILITà- ? WHERE IDProdotto= "+S;
	   try {
		   connection = ds.getConnection();
		   preparedStatement= connection.prepareStatement(secondSQL);
		   preparedStatement.setInt(1,x);
		   preparedStatement.executeUpdate();
		   preparedStatement.close();
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
   }
   
   public synchronized static void Getparametri(int x,String S) throws SQLException {
	   
   }
   
   
   
   public synchronized static Collection<OrdineBean> doRetrieveAll(Cart c) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setIDOrdine(rs.getString("IDORDINE"));
				bean.setIDAccount(rs.getInt("IDACCOUNT"));
				bean.setNumProdotti(rs.getInt("NUMPRODOTTI"));
				bean.setDescrizioneProdotti(rs.getString("DESCRIZIONEPRODOTTI"));
				bean.setStatoOrdine(rs.getString("STATOORDINE"));
				bean.setDataSpedizione(rs.getDate("DATASPEDIZIONE"));
				bean.setDataConsegna(rs.getDate("DATACONSEGNA"));
				bean.setDataOrdine(rs.getDate("DATAORDINE"));
				bean.setPrezzoFinale(rs.getFloat("PREZZOFINALEORDINE"));
				ordini.add(bean);
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
		return ordini;
	}

   public synchronized static Collection<OrdineBean> doRetrieveAllOrd() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setIDOrdine(rs.getString("IDORDINE"));
				bean.setIDAccount(rs.getInt("IDACCOUNT"));
				bean.setNumProdotti(rs.getInt("NUMPRODOTTI"));
				bean.setDescrizioneProdotti(rs.getString("DESCRIZIONEPRODOTTI"));
				bean.setStatoOrdine(rs.getString("STATOORDINE"));
				bean.setDataSpedizione(rs.getDate("DATASPEDIZIONE"));
				bean.setDataConsegna(rs.getDate("DATACONSEGNA"));
				bean.setDataOrdine(rs.getDate("DATAORDINE"));
				bean.setPrezzoFinale(rs.getFloat("PREZZOFINALEORDINE"));
				ordini.add(bean);
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
		return ordini;
	}


}

