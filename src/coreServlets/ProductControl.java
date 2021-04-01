package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coreModels.beans.Cart;
import coreModels.beans.Order;
import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreModels.model.ProductModelDM;
import coreModels.model.ProductModelDS;
/**
 * Servlet implementation class ProductControl
 */
@WebServlet("/ProductControl")

/*
 * Il filtro prender�? i prodotti per categoria, nome, prezzo
 * */
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static boolean isDataSource = false;
	Gson parser = new GsonBuilder().registerTypeAdapter(Cart.class, new json.JsonBuilderCart()).create();
	static ProductModel model;
	
	static {
		if (isDataSource) 
			model = new ProductModelDS();
		 else 
			model = new ProductModelDM();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("act");
		
		try {
			if (action != null) {
					Cart cart = (Cart)request.getSession().getAttribute("cart");
					
					if(cart == null) {
						cart = new Cart();
						request.getSession().setAttribute("cart", cart);
					}
					
					if (action.equalsIgnoreCase("addC")) {
						
						synchronized (cart) {
							int id = Integer.parseInt(request.getParameter("id"));
							int qty = Integer.parseInt(request.getParameter("qty"));
							ProductBean bean = model.doRetrieveByKey(id, true);
							Order orderBean = new Order (bean, qty);
						
							cart.addOrder(orderBean);
						
							if (request.getHeader("x-requested-with") == null) {
								response.setContentType("text/html");
								response.sendRedirect(response.encodeURL("carrello.jsp"));
							}
							else {
								response.setContentType("application/json");
								response.getWriter().write(parser.toJson(cart));
							}
								
					}
					} else if (action.equalsIgnoreCase("view")) {
						//visualizzazione del prodotto lato client
						int id = Integer.parseInt(request.getParameter("id"));
						ProductBean bean = 	model.doRetrieveByKey(id, true);
		
						if (bean != null) {
							request.setAttribute("product", bean);
							request.getRequestDispatcher(response.encodeURL("RecenzioneServlet?act=view")).include(request, response);
							request.getRequestDispatcher(response.encodeURL("/productView.jsp")).forward(request, response);
						}
						else response.sendRedirect("/error.jsp");
					} else if (action.equalsIgnoreCase("delete")) {
						//elimina dal carrello
						response.setContentType("application/json");
						int id = Integer.parseInt(request.getParameter("id"));
						cart.deleteProduct(id);
						
						response.getWriter().write(parser.toJson(cart));
					}
			}			
		} catch (SQLException e) {
			response.sendRedirect("error.jsp");
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
