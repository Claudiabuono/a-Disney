package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.GsonBuilder;

import coreModels.beans.ProductBean;
import coreModels.model.Paginator;
import coreModels.model.ProductModel;
import coreModels.model.ProductModelDM;
import coreModels.model.ProductModelDS;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/CatalogServlet")
public abstract class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected String URL;
    protected int numEl;
    /**
     * @see HttpServlet#HttpServlet()
     */
	static boolean isDataSource = true;
	static ProductModel model;
	
	static {
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private boolean isEmptyString(String s) {
		return s == null || s.trim() == "";
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String category = request.getParameter("ctgy");
		String search = request.getParameter("srch");
		java.util.List<ProductBean> list = null;
		String pg = request.getParameter("pg");
		int pgNumber = pg == null || "".equals(pg) ? 1 : Integer.parseInt(pg);
		 
		int size = 0;
		
		try {
			if (!isEmptyString(category) && isEmptyString(search)) {
				int ctgy = Integer.parseInt(category);
				list = model.doRetrieveByCategory(ctgy);
			}
			else if (isEmptyString(category) && !isEmptyString(search))
				list = model.doRetrieveBySearch(search, true);
			else {
				list = model.doRetrieveAll(true);
			}
			coreModels.model.Paginator<ProductBean> pager = new coreModels.model.Paginator<ProductBean>(numEl, pgNumber);
			Paginator<ProductBean>.Pair obj = pager.paginate(list);
			list = obj.pagedList;
			request.setAttribute("maxPg", obj.maxPg);
		} catch (SQLException e) {
			response.sendRedirect(response.encodeURL("error.jsp"));
			e.printStackTrace();
			return;
		}
		
		if (request.getHeader("x-requested-with") == null) {
			//forward to the jsp
			response.setContentType("text/html");
			request.setAttribute("list", list);
			request.setAttribute("sizeInput", size);
			request.getRequestDispatcher(response.encodeURL(URL)).forward(request, response);
		}
		else {
			response.setContentType("application/json");
			response.getWriter().write(new GsonBuilder().registerTypeAdapter(ProductBean.class, new json.JsonBuilderProd()).create().toJson(list));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
