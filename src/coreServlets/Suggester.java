package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;

/**
 * Servlet implementation class Suggester
 */
@WebServlet("/Suggester")
public class Suggester extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static Gson parser;
	static ProductModel model;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() {
			model= new ProductModel();
			parser = new GsonBuilder().registerTypeAdapter(ProductBean.class, new json.JsonBuilderProd()).create();
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		// recupero il tipo di categoria cercata dai parametri della richiesta
		String search = request.getParameter("srch");

		// recupero i feed corrispondenti dal database (implementato come Java Bean)
		
		List<ProductBean> list;
		try {
			list = model.doRetrieveBySearch(search, true);
			response.getWriter().write(parser.toJson(list));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
