package coreServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.model.FatturaModel;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static FatturaModel model;
	
	static {
		model = new FatturaModel();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean isUser = (Boolean) request.getSession().getAttribute("isUser");
		
		if (isUser == null ? false : !isUser)
			response.sendRedirect(response.encodeURL("error.jsp"));
		
			
		
		try {
			@SuppressWarnings("unchecked")
			java.util.Map<Integer,coreModels.beans.Adress> addresses = (java.util.Map<Integer,coreModels.beans.Adress>) request.getSession().getAttribute("addresses");
			coreModels.beans.Adress shipping = addresses.get(Integer.parseInt(request.getParameter("address")));
			coreModels.beans.Cart cart = (coreModels.beans.Cart) request.getSession().getAttribute("cart");
			coreModels.beans.FatturaBean invoice = new coreModels.beans.FatturaBean();
			
			if (!cart.isEmpty()) {
				invoice.setProdotti(cart.getOrders());
				invoice.setUser((coreModels.beans.Registered)request.getSession().getAttribute("user"));
				invoice.setShipping(shipping);
				model.doSave(invoice);
				request.getSession().removeAttribute("cart");
				response.sendRedirect("Thanks.jsp");
			} 
			else 
				response.sendRedirect(response.encodeURL("error.jsp"));
				
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect(response.encodeURL("error.jsp"));
		}
		catch (NumberFormatException w) {
			request.setAttribute("warning", true);
			request.getRequestDispatcher(response.encodeURL("/Billing")).forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("warning", true);
			request.getRequestDispatcher(response.encodeURL("/Billing")).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void setFatturaModel(FatturaModel fatturaDao) {
		this.model=fatturaDao;
	}
}
