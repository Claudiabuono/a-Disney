package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.GsonBuilder;

import coreModels.beans.ProductBean;
import coreModels.model.Pair;
import coreModels.model.ProductModel;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/CatalogServlet")
public abstract class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//= "Catalogo.jsp"  = 20
    protected String URL;
    protected int numEl;
    /**
     * @see HttpServlet#HttpServlet()
     */
	static ProductModel model;
	
	static {
		model = new ProductModel();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private boolean isEmptyString(String s) {
		return s == null || s.trim() == "";
	}
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			if(list!=null && list.size()>0)
			{
				coreModels.model.Paginator<ProductBean> pager = new coreModels.model.Paginator<ProductBean>(numEl, pgNumber);
				Pair<ProductBean> obj = pager.paginate(list);
				list = obj.pagedList;
				request.setAttribute("maxPg", obj.maxPg);
			}

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
			RequestDispatcher rs= request.getRequestDispatcher(response.encodeURL(URL));
			rs.forward(request, response);

		}
		else {
			response.setContentType("application/json");
			response.getWriter().write(new GsonBuilder().registerTypeAdapter(ProductBean.class, new json.JsonBuilderProd()).create().toJson(list));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void setProductModel(ProductModel prodottoDao) {
		this.model= prodottoDao;
	}
}
