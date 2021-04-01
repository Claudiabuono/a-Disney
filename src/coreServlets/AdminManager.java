package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.model.Paginator;

/**
 * Servlet implementation class AdminManager
 */
@WebServlet("/admin")
public class AdminManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static coreModels.model.FatturaModel model;
    static boolean isDataSource = false;
    
    static
	{
		if (isDataSource) 
			model = new coreModels.model.FatturaModelDS();
		else 
			model = new coreModels.model.FatturaModelDM();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		
		if (isAdmin != null ? isAdmin : true) {
			try {
				
				String login = request.getParameter("search");
				SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
				String par1 = request.getParameter("da");
				String par2 = request.getParameter("a");
				String pg = request.getParameter("pg");
			
				java.util.Date da = par1 == null || "".equals(par1) ? null : format.parse(par1);
				java.util.Date a = par2 == null || "".equals(par2) ? null : format.parse(par2);
				
				coreModels.model.Paginator<coreModels.beans.FatturaBean> pager = new coreModels.model.Paginator<coreModels.beans.FatturaBean>(10, pg == null ? 1 : Integer.parseInt(pg) );
				Paginator<coreModels.beans.FatturaBean>.Pair obj = pager.paginate(login == null || "".equals(login) ? model.retrieveInvoices(da, a) : model.retrieveInvoices(new coreModels.model.RegisteredModelDM().doRetrieveByKey(login), da, a));
				
				request.setAttribute("maxPg", obj.maxPg);
				request.setAttribute("fatture", obj.pagedList);
				
				if (request.getHeader("x-requested-with") == null)
					getServletContext().getRequestDispatcher(response.encodeURL("/Ordini.jsp")).forward(request, response);
				else getServletContext().getRequestDispatcher(response.encodeURL("/contentJSP/tableOrdersadmin.jsp")).forward(request, response);
			} catch (ParseException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect(response.encodeURL("error.jsp"));
			}
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
