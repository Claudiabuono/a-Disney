package coreServlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.FatturaBean;
import coreModels.model.FatturaModel;
import coreModels.model.DM.FatturaModelDM;
import coreModels.model.RegisteredModel;
import coreModels.model.DM.RegisteredModelDM;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static boolean isDataSource = false;
	static RegisteredModel model;
	static FatturaModel fatt;
	static
	{
	//	if (isDataSource) {
	//		model = new RegisteredModelDS();
	//		fatt = new FatturaModelDS();
	//	} else {
			model = new RegisteredModelDM();
			fatt = new FatturaModelDM();
	//	}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private coreModels.model.Pair<FatturaBean> paginate (java.util.List <coreModels.beans.FatturaBean> list, int pgNumber) {
    	coreModels.model.Paginator<coreModels.beans.FatturaBean> pager = new coreModels.model.Paginator<coreModels.beans.FatturaBean>(10, pgNumber);
		return pager.paginate(list);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * modifica password, modifica dell'email, operazioni su indirizzi, retrieval degli ordini
		 * */
		String op = request.getParameter("op");
		coreModels.beans.Registered bean = (coreModels.beans.Registered) request.getSession().getAttribute("user");
		
		if (bean != null)
			try {
				if("modCred".equalsIgnoreCase(op)) {
					String name = request.getParameter("nome") == null ? bean.getName() :  request.getParameter("nome"); 
					String surname = request.getParameter("cognome") == null ? bean.getCognome() : request.getParameter("cognome"); 
					String login = request.getParameter("login") == null ? bean.getLogin() : request.getParameter("login");
					String pass = request.getParameter("pass") == null ? bean.getPassword() : request.getParameter("pass");
					
					model.doModify(bean, name, surname, login, pass);
				} else if ("viewFatture".equalsIgnoreCase(op)) {
					response.setContentType("text/html");
					String pg = request.getParameter("pg");
					
					if(request.getHeader("x-requested-with") == null) {
						coreModels.model.Pair<FatturaBean> obj = this.paginate(fatt.retrieveInvoices(bean, null, null), pg == null ? 1 : Integer.parseInt(pg));
						request.setAttribute("fatture", obj.pagedList);
						request.setAttribute("maxPg", obj.maxPg);
						
						getServletContext().getRequestDispatcher(response.encodeURL("/OrdiniUtente.jsp")).forward(request, response);
					} else {
						
						SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
						String par1 = request.getParameter("da");
						String par2 = request.getParameter("a");
						java.util.Date da = par1 == null || "".equals(par1) ? null : format.parse(par1);
						java.util.Date a = par2 == null || "".equals(par2)? null : format.parse(par2);
						
						coreModels.model.Pair<FatturaBean> obj = this.paginate(fatt.retrieveInvoices(bean, da, a), pg == null ? 1 : Integer.parseInt(pg));
						
						request.setAttribute("fatture", obj.pagedList);
						request.setAttribute("maxPg", obj.maxPg);
						
						getServletContext().getRequestDispatcher(response.encodeURL("/contentJSP/tableOrdiniUtente.jsp")).forward(request, response);
					}
				}
		
			} catch (java.sql.SQLException | ParseException e) {
				e.printStackTrace();
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
