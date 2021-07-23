package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.*;
import javax.servlet.annotation.*;
@WebServlet("/Registrazione")

/**
 * Servlet Implementazione LOGIN
 */
public class RegistrazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String redirectedPage;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

			try{	    
			
						System.out.println("Siamo nella servlet");
						String Username = request.getParameter("Username");
						String Password = request.getParameter("Password");
						String Nome = request.getParameter("Nome");
						String Cognome = request.getParameter("Cognome");
						String Email =request.getParameter("Email");
						String CF = request.getParameter("CF");
						Date x=new Date();
						java.sql.Date DataRegistrazione = new java.sql.Date(x.getTime());
						AccountBean bean = new AccountBean();
						bean.setUsername(Username);
						bean.setPassword(Password);
						bean.setDataRegistrazione(DataRegistrazione);
						bean.setNome(Nome);
						bean.setCognome(Cognome);
						bean.setEmail(Email);
						bean.setCF(CF);
						UserDAO.doSave(bean);
						System.out.println("Caricamento...");
						
							
					  if (bean.isValid())
					     {
					          HttpSession session = request.getSession(true);
					          session.setAttribute("currentSessionUser",bean); 
					          response.sendRedirect("LoginPage.jsp"); //logged-in page
					     }

					     else 
					          response.sendRedirect("LoginErrato.jsp"); //error page 
					
					    		
					
				
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			} 
	}
					
	
			protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		  
			}
		
}
