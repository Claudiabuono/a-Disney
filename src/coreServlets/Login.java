package coreServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.UserBean;
import coreModels.model.AdminModel;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UserModel utente;
	static UserModel admin;
	static
	{
		utente = new RegisteredModel();
		admin = new AdminModel();

	}
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void setUserModel(UserModel userDao) {
		this.utente= userDao;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean paying = session.getAttribute("wasPaying") == null ? false : (Boolean) session.getAttribute("wasPaying");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String page = new String();


		try {
			UserBean user = utente.login(username, password);
			if (user != null){ 
				session.setAttribute("user", user);
				session.setAttribute("isUser", new Boolean(true));
				session.removeAttribute("wasPaying");
				
				if (paying) {
					request.getRequestDispatcher("/Billing").forward(request, response);
					return;
				} else page = "index.jsp";
			} else{
				UserBean administrator = admin.login(username, password);
				if (administrator != null){
					session.setAttribute("isAdmin", true);
					page = "amministratore.jsp";
					session.removeAttribute("cart");
				} else throw new Exception();
			}
			response.sendRedirect(response.encodeURL(page));



		} catch (SQLException e) {
			response.sendRedirect("error.jsp");
			e.printStackTrace();
		} catch (Exception e) {
			request.getRequestDispatcher(response.encodeURL("/Login.jsp")).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
