package it.unisa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ImageControl {
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
public synchronized static byte[] load(String id) throws SQLException {
	PreparedStatement preparedStatement = null;
	byte[] bt = null;
	ImageBean bean= new ImageBean();
	String sql = "SELECT foto FROM immagini WHERE IDProdotto = ?";
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(sql); 
		preparedStatement.setString(1, id);
		rs=preparedStatement.executeQuery();
		
		if (rs.next()) {
			bt=rs.getBytes("foto");
		}
	} catch(SQLException sqlException) {
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return bt;
}

public synchronized static void upload(String idA, String photo)
		throws SQLException {
		
		PreparedStatement preparedStatement = null;

		try {
		connection = ds.getConnection();
		String sql= "INSERT INTO immagini(IDProdotto,foto) VALUES (?,?)";
		preparedStatement = connection.prepareStatement(sql); 
		
		System.out.println(photo);
		File file = new File(photo);
		try {
			FileInputStream fis = new FileInputStream(file);
			preparedStatement.setString(1, idA);
			preparedStatement.setBinaryStream(2, fis, fis.available());
			

			preparedStatement.executeUpdate();
			System.out.println("cia");
		}catch(IOException e){
		}
		}finally{
			try{
				if(preparedStatement!=null)
					preparedStatement.close();
				} catch(SQLException sqlException){
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
	}


}

