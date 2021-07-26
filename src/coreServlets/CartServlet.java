package coreServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.Cart;
import coreModels.beans.Order;
import coreModels.model.ProductModel;
import coreModels.model.DM.ProductModelDM;
import coreModels.model.DS.ProductModelDS;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/myCart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static boolean isDataSource = false;
	
	static ProductModel model;
	
	static {
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if (cart == null) {
			request.getSession().setAttribute("cart", new Cart());
			cart = new Cart();
		}
		
			//access DB and update values....
			try {
				java.util.List<coreModels.beans.ProductBean> list = model.doRetrieveList(cart.getCodes(), true);
				
						
				for (coreModels.beans.ProductBean bean : list) {
					Order o = new Order (bean, cart.getOrder(bean.getCode()).getQty());
					cart.addOrder(o);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("cart", cart);
			request.getRequestDispatcher("/contentJSP/cartContent.jsp").forward(request, response);

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
