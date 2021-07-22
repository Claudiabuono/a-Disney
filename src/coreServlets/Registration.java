package coreServlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.Registered;
import coreModels.model.RegisteredModel;
import coreModels.model.DM.RegisteredModelDM;
import coreModels.model.DS.RegisteredModelDS;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = false;
	static RegisteredModel model;
	static
	{
		if (isDataSource) {
			model = new RegisteredModelDS();
		} else {
			model = new RegisteredModelDM();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String page = new String();
			
		Registered user = new Registered();
		user.setCognome(request.getParameter("surname"));
		user.setLogin(request.getParameter("email"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		
		try {
			if (!model.doesExist(user)) {
				model.registration(user);
				page = "index.jsp";
			}
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			// TODO: handle exception
			request.setAttribute("error", true);
			getServletContext().getRequestDispatcher(response.encodeURL("/registration.jsp")).forward(request, response);
			return;
		} 
		catch (SQLException e) {
			page = "error.jsp";
			e.printStackTrace();
		}
		
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void setUserModel(RegisteredModel userDao) {
	}
}
