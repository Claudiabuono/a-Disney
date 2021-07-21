package coreServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCatalog
 */
@WebServlet("/admincat")
public class AdminCatalog extends CatalogServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(javax.servlet.ServletConfig config) throws ServletException {
		super.init(config);

		URL = "catalogoAdm.jsp";
		numEl = 20;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		
		if(isAdmin == null ? false : isAdmin.booleanValue())
			super.doGet(request, response);
		else 
			response.sendRedirect("Login.jsp");
	}
}
