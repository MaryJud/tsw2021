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

@WebServlet("/carrello")
/**
 * Servlet implementation class ProductControl
 */
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static boolean isDataSource = true;
	
	static ProductModel model;
	
	static {
			model = new ProductModelDS();
	}

	public CarrelloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		AccountBean user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
		
		
		Cart c = (Cart)request.getSession().getAttribute("cart");
		if(c == null) {
			c = new Cart();
			request.getSession().setAttribute("cart", c);
		}
		
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
					String codice = (request.getParameter("IDProdotto"));
					c.addProduct(new CartProduct(model.doRetrieveByKey(codice)));		
					request.getSession().setAttribute("Cart", c);
					request.setAttribute("Cart", c);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");	
					dispatcher.forward(request, response);
				}  else if (action.equalsIgnoreCase("deleteC")) {
					String codice = (request.getParameter("IDProdotto"));
					c.deleteProduct(new CartProduct(model.doRetrieveByKey(codice)));
					request.getSession().setAttribute("Cart", c);
					request.setAttribute("Cart", c);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
					dispatcher.forward(request, response);
			
				} else if (action.equalsIgnoreCase("viewC")) {
					 user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
		             request.removeAttribute("currentSessionUser");
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
	                dispatcher.forward(request, response);
	                return;
				}else if (action.equalsIgnoreCase("acquista")) {
					if(c.getProducts().size()==0) return;
					System.out.println(c.getProducts().size());
					OrdineDAO.doSave(c,user);
					for (int i = 0; i < c.getProducts().size(); i++) {
						int Pezzi = c.getProducts().get(i).getPezzi();
						c.getProducts().get(i).getProdotto().decrementaDisp(Pezzi);
						OrdineDAO.SetDisponibilità(Pezzi,c.getProducts().get(i).getProdotto().getCode());
					}
					request.setAttribute("Vordini", OrdineDAO.doRetrieveAll(c));
					c.getProducts().removeAll(c.getProducts());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ringraziamenti.jsp");
					dispatcher.forward(request, response);
					return;
				}	
				else if (action.equalsIgnoreCase("Vordini")){
					user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
					String redirect="";
					if(user!=null) {
						if(user.getAdmin()==1) {
							request.setAttribute("Vordini", OrdineDAO.doRetrieveAllOrd());
							redirect= "/Ordini.jsp";
						}
						else {
							request.setAttribute("Vordini", OrdineDAO.doRetrieveByKey2(user.getIDAccount(),null,null));
							redirect= "/Ordini.jsp";
						}
						
					} else redirect="/LoginErrato.jsp";
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect);
					dispatcher.forward(request, response);
					return;
				}
				
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("cart", c);
		request.setAttribute("cart", c);
	

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductV.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
	}

}
