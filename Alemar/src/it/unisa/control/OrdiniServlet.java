package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;

@WebServlet("/ordini")
/**
 * Servlet implementation class ProductControl
 */
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		AccountBean user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
		
	
		String action = request.getParameter("action");

				
			if (action.equalsIgnoreCase("readOrdine")) {
	                String id = (request.getParameter("IDOrdine"));
	                request.removeAttribute("ordine");
	                try {
						request.setAttribute("ordine", OrdineDAO.doRetrieveByKey(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DettagliOrdine.jsp");
	                dispatcher.forward(request, response);
	                return;
				
				}	
				else if (action.equalsIgnoreCase("Vordini")){
					user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
					String redirect="";
					if(user!=null) {
						if(user.getAdmin()==1) {
							try {
								request.setAttribute("Vordini", OrdineDAO.doRetrieveAllOrd());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							redirect= "/Ordini.jsp";
						}
						else {
							try {
								request.setAttribute("Vordini", OrdineDAO.doRetrieveByKey2(user.getIDAccount(),null,null));
							} catch (SQLException | ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							redirect= "/Ordini.jsp";
						}
						
					} else redirect="/LoginErrato.jsp";
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect);
					dispatcher.forward(request, response);
				
				}
				else if(action.equalsIgnoreCase("ricerca")) {
					int sort = Integer.parseInt(request.getParameter("IDAccount"));

					try {
						request.removeAttribute("Vordini");
						request.setAttribute("Vordini", OrdineDAO.doRetrieveByKey2(sort,null,null));
					} catch (SQLException e) {
						System.out.println("Error:" + e.getMessage());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini.jsp");
					dispatcher.forward(request, response);
					return;
				}
				else if(action.equalsIgnoreCase("ricercaData")) {
					int x= 0;
					String d1= request.getParameter("DataOrdine1");
					String d2 = request.getParameter("DataOrdine2");

					try {
						request.removeAttribute("Vordini");
						request.setAttribute("Vordini", OrdineDAO.doRetrieveByKey2(x,d1,d2));
					} catch (SQLException e) {
						System.out.println("Error:" + e.getMessage());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini.jsp");
					dispatcher.forward(request, response);
					return;
				}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
	}

}
