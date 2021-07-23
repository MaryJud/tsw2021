package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ImageControl;

/**
 * Servlet implementation class GetImmaginiServlet
 */
@WebServlet("/GetImmaginiServlet")
public class GetImmaginiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
		String id = (String) request.getParameter("id");
		if (id != null)
			{
			byte[] bt = null;
			try {
				bt = ImageControl.load(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletOutputStream out = response.getOutputStream();
			if(bt != null)
			{
		
		out.write(bt);
		response.setContentType(" image/jpeg");
			}
		out.close();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
