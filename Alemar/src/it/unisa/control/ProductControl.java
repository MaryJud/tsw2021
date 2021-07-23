package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Blob;

import it.unisa.model.*;

/**
 * Servlet implementation class ProductControl
 */
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ProductModelDS usa il DataSource
	// ProductModelDM usa il DriverManager	
	static boolean isDataSource = true;
	
	static ProductModel model;
	
	static {
			model = new ProductModelDS();
	}

	public ProductControl() {
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
				} else if (action.equalsIgnoreCase("read")) {
					String id = (request.getParameter("IDProdotto"));
					request.removeAttribute("prodotto");
					request.setAttribute("prodotto", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/details.jsp");
					dispatcher.forward(request, response);
				}else if (action.equalsIgnoreCase("readOrdine")) {
	                String id = (request.getParameter("IDOrdine"));
	                request.removeAttribute("ordine");
	                request.setAttribute("ordine", OrdineDAO.doRetrieveByKey(id));
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DettagliOrdine.jsp");
	                dispatcher.forward(request, response);
	                return;
				} else if (action.equalsIgnoreCase("viewC")) {
					 user= (AccountBean) request.getSession().getAttribute("currentSessionUser");
		             request.removeAttribute("currentSessionUser");
		             request.getSession().setAttribute("cart", c);
				     request.setAttribute("cart", c);
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
				else if (action.equalsIgnoreCase("Vcatalogo")){
					request.removeAttribute("prodotto");
					request.setAttribute("Vcatalogo", model.doRetrieveAll(""));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
					dispatcher.forward(request, response);
					return;
					
					//Inserimento prodotti
				}else if (action.equalsIgnoreCase("inserimento")) {
					String id= request.getParameter("IDProdotto");
					String name = request.getParameter("Nome");
					String codiceCat = request.getParameter("CodiceCategoria");
					String description = request.getParameter("Descrizione");
					float price = Float.parseFloat(request.getParameter("PrezzoNoIVA"));
					int quantity = Integer.parseInt(request.getParameter("Disponibilita"));
					int sconto = Integer.parseInt(request.getParameter("Sconto"));
					String img=request.getParameter("foto");
					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCode(id);
					bean.setSconto(sconto);
					bean.setCodiceCat(codiceCat);
					model.doSave(bean);
					ImageControl.upload("9","C:\\Users\\maryr\\Downloads\\immaginiTsw\\"+img);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
					dispatcher.forward(request, response);
					return;
				}
				
				else if (action.equalsIgnoreCase("modifica")) {
					String id= request.getParameter("IDProdotto");
					String codiceCat = request.getParameter("CodiceCategoria");			
					String sconto = request.getParameter("Sconto");
					String quantity = request.getParameter("Disponibilita");
					String description = request.getParameter("Descrizione");					
					String price =request.getParameter("PrezzoNoIVA");
					String name = request.getParameter("Nome");
					if(codiceCat!=null) {
						model.doUpdate(id,codiceCat,"C_CodiceCategoria");
					}
					if(sconto!=null) {
						model.doUpdate(id,sconto,"Sconto");
					}
					if(quantity!=null) {
						model.doUpdate(id,quantity,"Disponibilità");
					}
					if(description!=null) {
						model.doUpdate(id,description,"DescrizioneProdotto");
					}
					if(price!=null) {
						model.doUpdate(id,price,"PrezzoNoIVA");
					}
					if(name!=null) {
						model.doUpdate(id,name,"Nome");
					}
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
					dispatcher.forward(request, response);
					return;
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
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("cart", c);
        request.setAttribute("cart", c);
		
		String sort = request.getParameter("sort");

		request.removeAttribute("prodotti");
		try {
			request.setAttribute("prodotti", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductV.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
	}

}
