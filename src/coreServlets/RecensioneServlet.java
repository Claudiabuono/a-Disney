package coreServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.model.DM.FatturaModelDM;
import coreModels.model.RecensioneModel;
import coreModels.model.DM.RecensioneModelDM;
import coreModels.model.DS.RecensioneModelDS;

/**
 * Servlet implementation class RecenzioneServlet
 */
@WebServlet("/RecenzioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static boolean isDataSource = false;
	static RecensioneModel recenzione;
	
	static {
		if (isDataSource) {
			recenzione = new RecensioneModelDS();
		} else {
			recenzione = new RecensioneModelDM();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("act");
		coreModels.beans.Registered user = (coreModels.beans.Registered) request.getSession().getAttribute("user");
		ProductBean bean = (ProductBean) request.getAttribute("product");
		try {
			if ("view".equalsIgnoreCase(action)) {
			
				java.util.List<RecensioneBean> list = recenzione.getComments(bean);
				RecensioneBean userComment = user == null ? null : recenzione.userComment(user, bean);
				boolean payed = userComment == null ? user == null ? false : new FatturaModelDM().hasPurchased(bean, user) :false;
				list.remove(userComment);
				
				request.setAttribute("voto", recenzione.mediumVote(bean));
				request.setAttribute("userComment", userComment);
				request.setAttribute("recenzioni", list);
				request.setAttribute("payed", payed);
			
			}  else if ("insert".equalsIgnoreCase(action)) {
				String comment = request.getParameter("comment");
				int vote = Integer.parseInt(request.getParameter("vote"));
				ProductBean owo = new ProductBean (Integer.parseInt(request.getParameter("id")));
				recenzione.newComment(user, owo, comment, vote);
		}
		} catch (SQLException e) {
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
