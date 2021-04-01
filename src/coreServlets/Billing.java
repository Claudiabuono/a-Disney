package coreServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Billing
 */
@WebServlet("/Billing")
public class Billing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Billing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		coreModels.beans.Cart cart = request.getSession().getAttribute("cart") == null ? new coreModels.beans.Cart() : (coreModels.beans.Cart) request.getSession().getAttribute("cart");
		
		if (cart.isEmpty()) response.setStatus(204);
		else if(request.getSession().getAttribute("user") != null) {
			request.getRequestDispatcher("AddressOperations?operation=0").include(request, response);
			request.getRequestDispatcher(response.encodeURL("Checkout.jsp")).forward(request, response);
		} else {
			request.getSession().setAttribute("wasPaying", true);
			response.sendRedirect (response.encodeURL("Login.jsp"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
