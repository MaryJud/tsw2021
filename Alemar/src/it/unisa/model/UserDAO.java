package it.unisa.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class UserDAO 	
{
    
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
   static final String TABLE_NAME = "account";
	
   public static AccountBean doRetrieve(AccountBean bean) { 
	   int id,admin;
	   String Username = bean.getNomeUtente();    
	   String Password = bean.getPassword();   
	   String selectSQL = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE Username='"+Username+""
	   		+ "' AND Password='"+Password+"'";
	   PreparedStatement preparedStatement = null;
   try 
   {
      //connect to DB 
	   Connection connection = ds.getConnection();
	   preparedStatement = connection.prepareStatement(selectSQL);      
	   rs=preparedStatement.executeQuery();
       boolean more = rs.next();
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Nome utente o password sbagliati, effettuare la registrazione per accedere.");
         bean.setValid(false);
      } 
	        
      //if user exists set the isValid variable to true
      else{
         Username = rs.getString("Username");
         id=rs.getInt("IDAccount");
         admin=rs.getInt("admin");
         bean.setIDAccount(id);
         bean.setAdmin(admin);
         bean.setNomeUtente(Username);
         bean.setValid(true);
         
      }
      System.out.println(bean.isValid());
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In failed: An Exception has occurred! " + ex);
   } 
	    
   //some exception handling
   finally 
   {
      if (rs != null)	{
         try {
            rs.close();
         } catch (Exception e) {}
            rs = null;
         }
	 
      if (connection != null) {
         try {
        	 connection.close();
         } catch (Exception e) {
         }

         connection = null;
      }
   }

return bean;
	
   }	
   
   
   public synchronized static void doSave(AccountBean account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserDAO.TABLE_NAME
				+ "(Username, Password,DataRegistrazione, Nome, Cognome ,Email, CF) VALUES (?,?,?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setDate(3, account.getDataRegistrazione());
			preparedStatement.setString(4, account.getNome());
			preparedStatement.setString(5, account.getCognome());
			preparedStatement.setString(6, account.getEmail());
			preparedStatement.setString(7, account.getCF());
			
			int more=preparedStatement.executeUpdate();
			
		    if (more==0) 
		      {
		         System.out.println("Utente già registrato.");
		         account.setValid(false);
		      } 
			    
		     else	        
		         account.setValid(true);		         
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	
   
}}

