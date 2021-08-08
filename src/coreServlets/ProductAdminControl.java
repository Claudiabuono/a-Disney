package coreServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import com.sun.source.tree.SynchronizedTree;
import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;

/**
 * Servlet implementation class ProductAdminControl
 */
@WebServlet("/ProductAdminControl")
public class ProductAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1000L;
    
	static ProductModel model;
	
	static {
		model = new ProductModel();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		String action = request.getParameter("act");
		
		try {
			if (action != null && (isAdmin == null ? false : isAdmin)) {
					if (action.equalsIgnoreCase("delete")) {
						//int id = Integer.parseInt(request.getParameter("id"));
						model.doDelete(Integer.parseInt(request.getParameter("id")));
					} else if (action.equalsIgnoreCase("insert")) {
						ProductBean bean = new ProductBean();
						
						//codice, nome, descrizione, prezzo, quantita, personaggio, foto, iva, sconto, tipo, categoria
						bean.setName(request.getParameter("name"));
						bean.setDescription(request.getParameter("description"));
						bean.setCharacter(request.getParameter("personaggio"));
						bean.setPhoto(request.getParameter("url"));
						bean.setTipo(request.getParameter("tipo")); 
						bean.setCategory(Integer.parseInt(request.getParameter("categoria")));
						bean.setQty(Integer.parseInt(request.getParameter("qty")));
						bean.setPrice(Double.parseDouble(request.getParameter("price")));
						bean.setIva(Double.parseDouble(request.getParameter("iva")));
						bean.setDiscount(Double.parseDouble(request.getParameter("sconto")));
						System.out.println(bean.getDiscount());
						System.out.println(bean.getPrice());
						System.out.println(bean.getIva());
						model.doSave(bean);
					} else if (action.equalsIgnoreCase("modify")) {
					
						ProductBean bean = new ProductBean ();
						bean.setCode(Integer.parseInt(request.getParameter("code")));
						bean.setIva(Double.parseDouble(request.getParameter("iva")));
						bean.setPrice(Double.parseDouble(request.getParameter("price")));
						bean.setDiscount(Double.parseDouble(request.getParameter("discount")));
						bean.setQty(Integer.parseInt(request.getParameter("qty")));
						
						
						model.doUpdate(bean);
						
						if (request.getHeader("x-requested-with")!= null){
							response.setContentType("application/json");
							JsonObject obj = new JsonObject ();
							
							obj.addProperty("newPrice", bean.getPrice());
							obj.addProperty("newQty", bean.getQty());
							obj.addProperty("newSconto", bean.getDiscount());
							obj.addProperty("newIva", bean.getIva());
							
							response.getWriter().write(new com.google.gson.Gson().toJson(obj));
						}
					}
				}
			}
			catch (SQLException e) {
				response.sendRedirect(response.encodeURL("error.jsp"));
			}
			catch (java.lang.NumberFormatException e){
				response.sendError(406);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
